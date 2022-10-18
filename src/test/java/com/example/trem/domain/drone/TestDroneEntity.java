package com.example.trem.domain.drone;

import com.example.trem.domain.delivery.entity.Delivery;
import com.example.trem.domain.delivery.factory.DeliveryFactory;
import com.example.trem.domain.drone.entity.Drone;
import com.example.trem.domain.drone.entity.DroneStatus;
import com.example.trem.domain.drone.exception.DroneException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Unit tests for domain Drone entity")
public class TestDroneEntity {

  @Test
  @DisplayName("should create a Drone entity")
  public void shouldCreateDroneEntity() {
    Drone drone = new Drone(UUID.randomUUID(), "Drone 1", 0.0, 0.0, DroneStatus.IDLE);

    assertNotNull(drone.getId());
    assertEquals("Drone 1", drone.getName());
    assertEquals(0.0, drone.getLatitude());
    assertEquals(0.0, drone.getLongitude());
    assertEquals(DroneStatus.IDLE, drone.getStatus());
  }

  @Test
  @DisplayName("should rename a Drone entity")
  public void shouldRenameDroneEntity() {
    Drone drone = new Drone(UUID.randomUUID(), "Drone 1", 0.0, 0.0, DroneStatus.IDLE);
    drone.rename("Drone 2");

    assertEquals("Drone 2", drone.getName());
  }

  @Test
  @DisplayName("should update location of a Drone entity")
  public void shouldUpdateLocationDroneEntity() {
    Drone drone = new Drone(UUID.randomUUID(), "Drone 1", 0.0, 0.0, DroneStatus.IDLE);
    drone.updateLocation(1.0, 1.0);

    assertEquals(1.0, drone.getLatitude());
    assertEquals(1.0, drone.getLongitude());
  }

  @Test
  @DisplayName("should update status of a Drone entity")
  public void shouldUpdateStatusDroneEntity() {
    Drone drone = new Drone(UUID.randomUUID(), "Drone 1", 0.0, 0.0, DroneStatus.IDLE);
    Delivery delivery = DeliveryFactory.create();

    drone.deliver(delivery);
    assertEquals(DroneStatus.DELIVERING, drone.getStatus());

    drone.returnToBase();
    assertEquals(DroneStatus.RETURNING, drone.getStatus());

    drone.idle();
    assertEquals(DroneStatus.IDLE, drone.getStatus());
  }

  @Test
  @DisplayName("should throw exception when creating a Drone entity with invalid data")
  public void shouldThrowExceptionWhenCreatingDroneEntityWithInvalidData() {
    assertThrows(
            NullPointerException.class,
            () -> new Drone(null, "Drone 1", 0.0, 0.0, DroneStatus.IDLE),
            "Drone id cannot be null"
    );

    assertThrows(
            DroneException.class,
            () -> new Drone(UUID.randomUUID(), null, 0.0, 0.0, DroneStatus.IDLE),
            "Drone name cannot be null"
    );

    assertThrows(
            DroneException.class,
            () -> new Drone(UUID.randomUUID(), "a", 0.0, 0.0, DroneStatus.IDLE),
            "Drone name must be at least 3 characters long"
    );

    assertThrows(
            DroneException.class,
            () -> new Drone(UUID.randomUUID(), "a".repeat(20), 0.0, 0.0, DroneStatus.IDLE),
            "Drone name must be at most 12 characters long"
    );

    assertThrows(
            DroneException.class,
            () -> new Drone(UUID.randomUUID(), "Drone 1", null, 0.0, DroneStatus.IDLE),
            "Drone latitude cannot be null"
    );

    assertThrows(
            DroneException.class,
            () -> new Drone(UUID.randomUUID(), "Drone 1", 0.0, null, DroneStatus.IDLE),
            "Drone longitude cannot be null"
    );

    assertThrows(
            DroneException.class,
            () -> new Drone(UUID.randomUUID(), "Drone 1", 0.0, 0.0, null),
            "Drone status cannot be null"
    );
  }

  @Test
  @DisplayName("should throw exception when update a Drone entity with invalid data")
  public void shouldThrowExceptionWhenUpdateDroneEntityWithInvalidData() {
    Drone drone = new Drone(UUID.randomUUID(), "Drone 1", 0.0, 0.0, DroneStatus.IDLE);
    Delivery delivery = DeliveryFactory.createWithDrone(drone);

    assertThrows(
            DroneException.class,
            () -> drone.rename(null),
            "Drone name cannot be null"
    );

    assertThrows(
            DroneException.class,
            () -> drone.rename("a"),
            "Drone name must be at least 3 characters long"
    );

    assertThrows(
            DroneException.class,
            () -> drone.rename("a".repeat(20)),
            "Drone name must be at most 12 characters long"
    );

    assertThrows(
            DroneException.class,
            () -> drone.updateLocation(null, 0.0),
            "Drone latitude cannot be null"
    );

    assertThrows(
            DroneException.class,
            () -> drone.updateLocation(0.0, null),
            "Drone longitude cannot be null"
    );
  }

}
