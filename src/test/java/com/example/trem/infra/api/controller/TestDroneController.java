package com.example.trem.infra.api.controller;

import com.example.trem.domain.drone.entity.Drone;
import com.example.trem.domain.drone.factory.DroneFactory;
import com.example.trem.infra.repositories.drone.DroneEntityMapper;
import com.example.trem.infra.repositories.drone.DroneRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Integration tests for api DroneController")
public class TestDroneController {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private DroneRepository droneRepository;

  @Test
  @DisplayName("should create a drone (POST /drones)")
  public void testCreateDrone() throws Exception {
    Drone newDrone = DroneFactory.create("Drone 1", 1.0, 1.0);
    mockMvc.perform(post("/drones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(newDrone)))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value("Drone 1"))
            .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("should update a drone (PUT /drones/{id})")
  public void testUpdateDrone() throws Exception {
    Drone newDrone = DroneFactory.create("Drone 1", 1.0, 1.0);

    droneRepository.save(DroneEntityMapper.toEntity(newDrone));
    newDrone.rename("Drone 2");

    mockMvc.perform(put("/drones/{id}", newDrone.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(newDrone)))
            .andExpect(status().isOk());
  }

  @Test
  @DisplayName("should delete a drone (DELETE /drones/{id})")
  public void testDeleteDrone() throws Exception {
//    Drone newDrone = DroneFactory.create("Drone 1", 1.0, 1.0);
//    var saved = droneRepository.save(DroneEntityMapper.toEntity(newDrone));
//
//    mockMvc.perform(delete("/drones/{id}", saved.getId())
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(new ObjectMapper().writeValueAsString(newDrone)))
//            .andExpect(status().isNoContent());
  }

  @Test
  @DisplayName("should get all drones (GET /drones)")
  public void testGetAllDrones() throws Exception {
    mockMvc.perform(get("/drones")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }

  @Test
  @DisplayName("should get a drone (GET /drones/{id})")
  public void testGetDrone() throws Exception {
    Drone newDrone = DroneFactory.create("Drone 1", 1.0, 1.0);

    mockMvc.perform(post("/drones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(newDrone)));

    mockMvc.perform(get("/drones/{id}", newDrone.getId())
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }

}
