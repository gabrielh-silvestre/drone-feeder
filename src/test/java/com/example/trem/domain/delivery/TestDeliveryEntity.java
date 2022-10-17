package com.example.trem.domain.delivery;

import com.example.trem.domain.delivery.entity.Delivery;
import com.example.trem.domain.delivery.entity.DeliveryStatus;
import com.example.trem.domain.delivery.exception.DeliveryException;
import com.example.trem.domain.drone.entity.Drone;
import com.example.trem.domain.drone.factory.DroneFactory;
import com.example.trem.domain.video.entity.Video;
import com.example.trem.domain.video.factory.VideoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Unit tests for domain Delivery entity")
public class TestDeliveryEntity {

  @Test
  @DisplayName("should create a Delivery entity")
  public void shouldCreateDeliveryEntity() {
    Delivery delivery = new Delivery(
            UUID.randomUUID(),
            DeliveryStatus.PENDING,
            LocalDateTime.now(),
            null,
            null,
            null
    );

    assertNotNull(delivery.getId());
    assertEquals(DeliveryStatus.PENDING, delivery.getStatus());
    assertNotNull(delivery.getCreatedAt());
    assertNull(delivery.getOrderDate());
    assertNull(delivery.getDeliveryDate());
    assertNull(delivery.getCancelDate());
    assertNull(delivery.getDrone());
  }

  @Test
  @DisplayName("should create a Delivery entity with drone")
  public void shouldCreateDeliveryEntityWithDrone() {
    Drone drone = DroneFactory.create("Test-Drone", 1.0, 1.0);
    Delivery delivery = new Delivery(
            UUID.randomUUID(),
            DeliveryStatus.PENDING,
            LocalDateTime.now(),
            null,
            null,
            null,
            drone
    );

    assertNotNull(delivery.getId());
    assertEquals(DeliveryStatus.PENDING, delivery.getStatus());
    assertNotNull(delivery.getCreatedAt());
    assertNull(delivery.getOrderDate());
    assertNull(delivery.getDeliveryDate());
    assertNull(delivery.getCancelDate());
    assertNotNull(delivery.getDrone());
  }

  @Test
  @DisplayName("should process order")
  public void shouldProcessOrder() {
    Delivery delivery = new Delivery(
            UUID.randomUUID(),
            DeliveryStatus.PENDING,
            LocalDateTime.now(),
            null,
            null,
            null
    );

    delivery.proccesOrder();

    assertEquals(DeliveryStatus.IN_PROGRESS, delivery.getStatus());
    assertNotNull(delivery.getOrderDate());
  }

  @Test
  @DisplayName("should complete delivery")
  public void shouldCompleteDelivery() {
    Drone drone = DroneFactory.create("Test-Drone", 1.0, 1.0);
    Video video = VideoFactory.create("teste.mp4", new Byte[0]);
    Delivery delivery = new Delivery(
            UUID.randomUUID(),
            DeliveryStatus.PENDING,
            LocalDateTime.now(),
            null,
            null,
            null,
            drone
    );

    delivery.proccesOrder();
    delivery.complete(video);

    assertEquals(DeliveryStatus.DELIVERED, delivery.getStatus());
    assertNotNull(delivery.getDeliveryDate());
  }

  @Test
  @DisplayName("should cancel delivery")
  public void shouldCancelDelivery() {
    Drone drone = DroneFactory.create("Test-Drone", 1.0, 1.0);
    Delivery delivery = new Delivery(
            UUID.randomUUID(),
            DeliveryStatus.PENDING,
            LocalDateTime.now(),
            null,
            null,
            null,
            drone
    );

    delivery.proccesOrder();
    delivery.cancel();

    assertEquals(DeliveryStatus.CANCELLED, delivery.getStatus());
    assertNotNull(delivery.getCancelDate());
  }

  @Test
  @DisplayName("should throw exception when try to process order with status different from PENDING")
  public void shouldThrowExceptionWhenTryToProcessOrderWithStatusDifferentFromPending() {
    Delivery delivery = new Delivery(
            UUID.randomUUID(),
            DeliveryStatus.IN_PROGRESS,
            LocalDateTime.now(),
            null,
            null,
            null
    );

    assertThrows(DeliveryException.class, delivery::proccesOrder);
  }

  @Test
  @DisplayName("should throw exception when try to complete delivery with status different from IN_PROGRESS")
  public void shouldThrowExceptionWhenTryToCompleteDeliveryWithStatusDifferentFromInProgress() {
    Video video = VideoFactory.create("teste.mp4", new Byte[0]);
    Delivery delivery = new Delivery(
            UUID.randomUUID(),
            DeliveryStatus.PENDING,
            LocalDateTime.now(),
            null,
            null,
            null
    );

    assertThrows(DeliveryException.class, () -> delivery.complete(video));
  }

  @Test
  @DisplayName("should throw exception when try to cancel delivery with status different from PENDING or IN_PROGRESS")
  public void shouldThrowExceptionWhenTryToCancelDeliveryWithStatusDifferentFromPendingOrInProgress() {
    Delivery delivery = new Delivery(
            UUID.randomUUID(),
            DeliveryStatus.DELIVERED,
            LocalDateTime.now(),
            null,
            null,
            null
    );

    assertThrows(DeliveryException.class, delivery::cancel);
  }

}
