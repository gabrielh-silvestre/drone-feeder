package com.example.trem.domain.drone;

import com.example.trem.domain.drone.entity.Drone;
import com.example.trem.domain.drone.entity.DroneStatus;
import com.example.trem.domain.drone.factory.DroneFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Unit tests for domain Drone factory")
public class TestDroneFactory {

  @Test
  @DisplayName("1 - should create a Drone entity")
  public void shouldCreateDroneEntity() {
    Drone drone = DroneFactory.create("Drone 1", 0.0, 0.0);

    assertNotNull(drone.getId());
    assertEquals("Drone 1", drone.getName());
    assertEquals(0.0, drone.getLatitude());
    assertEquals(0.0, drone.getLongitude());
    assertEquals(DroneStatus.IDLE, drone.getStatus());
  }

}
