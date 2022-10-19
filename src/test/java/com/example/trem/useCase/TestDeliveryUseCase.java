package com.example.trem.useCase;

import com.example.trem.domain.delivery.entity.Delivery;
import com.example.trem.domain.delivery.entity.DeliveryStatus;
import com.example.trem.domain.delivery.factory.DeliveryFactory;
import com.example.trem.domain.drone.entity.Drone;
import com.example.trem.domain.drone.factory.DroneFactory;
import com.example.trem.infra.repositories.delivery.DeliveryRepository;
import com.example.trem.infra.repositories.drone.DroneRepository;
import com.example.trem.useCase.delivery.DeliveryUseCase;
import com.example.trem.useCase.delivery.dto.DeliveryDto;
import com.example.trem.useCase.shared.exception.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@WebMvcTest(DeliveryUseCase.class)
@DisplayName("Unit tests for Delivery Use Case")
public class TestDeliveryUseCase {

  @Autowired
  private DeliveryUseCase deliveryUseCase;

  @MockBean
  private DeliveryRepository deliveryRepository;

  @MockBean
  private DroneRepository droneRepository;

  private final Iterable<Drone> drones = List.of(new Drone[]{
          DroneFactory.create("Drone 1", 1.0, 1.0),
          DroneFactory.create("Drone 2", 2.0, 2.0),
          DroneFactory.create("Drone 3", 3.0, 3.0)
  });

  private final Iterable<Delivery> deliveries = List.of(new Delivery[]{
          DeliveryFactory.createWithDrone(drones.iterator().next()),
          DeliveryFactory.createWithDrone(drones.iterator().next()),
  });

  @Test
  @DisplayName("1 - should return all deliveries")
  public void testGetAllDeliveries() {
    doReturn(deliveries).when(deliveryRepository).findAll();

    Iterable<DeliveryDto> deliveries = deliveryUseCase.getAll();

    assertEquals(2, ((List<DeliveryDto>) deliveries).size());
  }

  @Test
  @DisplayName("2 - should create a delivery")
  public void testGetDeliveryById() {
    Drone drone = drones.iterator().next();
    Delivery delivery = deliveries.iterator().next();

    doReturn(Optional.of(delivery)).when(deliveryRepository).findById(anyString());
    doReturn(Optional.of(drone)).when(droneRepository).findById(anyString());

    DeliveryDto deliveryDto = deliveryUseCase.create(drone.getId());

    assertNotNull(deliveryDto);
    assertEquals(delivery.getStatus(), DeliveryStatus.PENDING);
  }

  @Test
  @DisplayName("3 - should throw an exception when drone is not found on create delivery")
  public void testCreateDeliveryWithInvalidDrone() {
    doReturn(Optional.empty()).when(droneRepository).findById(anyString());

    assertThrows(NotFoundException.class, () -> deliveryUseCase.create(UUID.randomUUID()));
  }

  @Test
  @DisplayName("4 - should cancel a delivery")
  public void testCancelDelivery() {
    Delivery delivery = deliveries.iterator().next();
    doReturn(Optional.of(delivery)).when(deliveryRepository).findById(anyString());

    DeliveryDto deliveryDto = deliveryUseCase.cancel(delivery.getId());

    assertNotNull(deliveryDto);
    assertEquals(delivery.getStatus(), DeliveryStatus.CANCELLED);
  }

  @Test
  @DisplayName("5 - should throw an exception when delivery is not found on cancel delivery")
  public void testCancelDeliveryWithInvalidDelivery() {
    doReturn(Optional.empty()).when(deliveryRepository).findById(anyString());

    assertThrows(NotFoundException.class, () -> deliveryUseCase.cancel(UUID.randomUUID()));
  }

}
