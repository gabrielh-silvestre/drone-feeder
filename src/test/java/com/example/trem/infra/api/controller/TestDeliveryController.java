package com.example.trem.infra.api.controller;

import com.example.trem.domain.delivery.entity.Delivery;
import com.example.trem.domain.delivery.factory.DeliveryFactory;
import com.example.trem.domain.drone.entity.Drone;
import com.example.trem.domain.drone.factory.DroneFactory;
import com.example.trem.infra.repositories.delivery.DeliveryRepository;
import com.example.trem.infra.repositories.drone.DroneRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Integration tests for api DeliveryController")
public class TestDeliveryController {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private DeliveryRepository deliveryRepository;

  @Autowired
  private DroneRepository droneRepository;

  private final List<Drone> drones = List.of(
          DroneFactory.create("Drone 1", 1.0, 1.0),
          DroneFactory.create("Drone 2", 2.0, 2.0),
          DroneFactory.create("Drone 3", 3.0, 3.0)
  );

  private final List<Delivery> deliveries = List.of(
          DeliveryFactory.createWithDrone(drones.get(0)),
          DeliveryFactory.createWithDrone(drones.get(2))
  );

  @Test
  @DisplayName("1 - should return all deliveries (GET /deliveries)")
  public void testGetAllDeliveries() throws Exception {
    mockMvc.perform(get("/deliveries")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").isArray())
            .andExpect(status().isOk());
  }

  @Test
  @DisplayName("2 - should create a delivery (POST /deliveries/{droneId})")
  public void testCreateDelivery() throws Exception {
    Drone drone = drones.get(0);

    droneRepository.saveAll(this.drones);
    deliveryRepository.saveAll(this.deliveries);

    mockMvc.perform(post("/deliveries/{droneId}", drone.getId())
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").isNotEmpty())
            .andExpect(status().isCreated());
  }

//  @Test
//  @DisplayName("3 - should cancel a delivery (PATCH /deliveries/{id}/cancel)")
//  public void testCancelDelivery() throws Exception {
//    Delivery delivery = deliveries.get(0);
//    deliveryRepository.saveAll(this.deliveries);
//    droneRepository.saveAll(this.drones);
//
//    mockMvc.perform(patch("/deliveries/{id}/cancel", delivery.getId())
//                    .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk());
//  }

}
