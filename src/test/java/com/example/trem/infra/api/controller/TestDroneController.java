package com.example.trem.infra.api.controller;

import com.example.trem.domain.delivery.entity.Delivery;
import com.example.trem.domain.delivery.factory.DeliveryFactory;
import com.example.trem.domain.drone.entity.Drone;
import com.example.trem.domain.drone.factory.DroneFactory;
import com.example.trem.infra.repositories.delivery.DeliveryRepository;
import com.example.trem.infra.repositories.drone.DroneRepository;
import com.example.trem.useCase.drone.dto.CreateDroneDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

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

  @Autowired
  private DeliveryRepository deliveryRepository;

  private final Iterable<Drone> drones = List.of(new Drone[]{
          DroneFactory.create("Drone 1", 1.0, 1.0),
          DroneFactory.create("Drone 2", 2.0, 2.0),
          DroneFactory.create("Drone 3", 3.0, 3.0)
  });

  private final Iterable<Delivery> deliveries = List.of(new Delivery[]{
          DeliveryFactory.createWithDrone(drones.iterator().next()),
          DeliveryFactory.createWithDrone(drones.iterator().next()),
  });

  private CreateDroneDto generateCreateDto() {
    Drone drone = drones.iterator().next();

    CreateDroneDto dto = new CreateDroneDto();
    dto.setName(drone.getName());
    dto.setLatitude(drone.getLatitude());
    dto.setLongitude(drone.getLongitude());

    return dto;
  }

  @Test
  @DisplayName("1 - should create a drone (POST /drones)")
  public void testCreateDrone() throws Exception {
    CreateDroneDto newDrone = this.generateCreateDto();

    mockMvc.perform(post("/drones")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(newDrone)))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value("Drone 1"))
            .andExpect(status().isCreated());
  }

  @Test
  @DisplayName("2 - should update a drone (PUT /drones/{id})")
  public void testUpdateDrone() throws Exception {
    Drone drone = drones.iterator().next();
    droneRepository.saveAll(this.drones);

    drone.rename("Drone 2");

    mockMvc.perform(put("/drones/{id}", drone.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(drone)))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value("Drone 2"))
            .andExpect(status().isOk());
  }

  @Test
  @DisplayName("3 - should start a delivery (PATCH /drones/{id}/start/{deliveryId})")
  public void testStartDelivery() throws Exception {
    Drone drone = drones.iterator().next();
    Delivery delivery = deliveries.iterator().next();
    droneRepository.saveAll(this.drones);
    deliveryRepository.saveAll(this.deliveries);

    mockMvc.perform(patch("/drones/{id}/start/{deliveryId}", drone.getId(), delivery.getId())
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value("Drone 1"))
            .andExpect(jsonPath("$.status").value("DELIVERING"))
            .andExpect(status().isOk());
  }

//  @Test
//  @DisplayName("3 - should delete a drone (DELETE /drones/{id})")
//  public void testDeleteDrone() throws Exception {
//    Drone drone = drones.iterator().next();
//    droneRepository.saveAll(this.drones);
//
//    mockMvc.perform(delete("/drones/{id}", drone.getId().toString()))
//            .andExpect(status().isNoContent());
//  }

  @Test
  @DisplayName("4 - should get all drones (GET /drones)")
  public void testGetAllDrones() throws Exception {
    droneRepository.saveAll(this.drones);

    mockMvc.perform(get("/drones")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].name").value("Drone 1"))
            .andExpect(status().isOk());
  }

  @Test
  @DisplayName("5 - should get a drone (GET /drones/{id})")
  public void testGetDrone() throws Exception {
    Drone drone = drones.iterator().next();
    droneRepository.saveAll(this.drones);

    mockMvc.perform(get("/drones/{id}", drone.getId())
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value("Drone 1"))
            .andExpect(status().isOk());
  }

}
