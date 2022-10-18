package com.example.trem.useCase;

import com.example.trem.domain.delivery.entity.Delivery;
import com.example.trem.domain.delivery.factory.DeliveryFactory;
import com.example.trem.domain.drone.entity.Drone;
import com.example.trem.domain.drone.entity.DroneStatus;
import com.example.trem.domain.drone.factory.DroneFactory;
import com.example.trem.infra.repositories.delivery.DeliveryRepository;
import com.example.trem.infra.repositories.drone.DroneRepository;
import com.example.trem.useCase.drone.DroneUseCase;
import com.example.trem.useCase.drone.dto.CreateDroneDto;
import com.example.trem.useCase.drone.dto.DroneDto;
import com.example.trem.useCase.drone.dto.UpdateDroneDto;
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

@WebMvcTest(DroneUseCase.class)
@DisplayName("Unit tests for Drone Use Case")
public class TestDroneUseCase {

  @Autowired
  private DroneUseCase droneUseCase;

  @MockBean
  private DroneRepository droneRepository;

  @MockBean
  private DeliveryRepository deliveryRepository;

  private final Iterable<Drone> drones = List.of(new Drone[]{
          DroneFactory.create("Drone 1", 1.0, 1.0),
          DroneFactory.create("Drone 2", 2.0, 2.0),
          DroneFactory.create("Drone 3", 3.0, 3.0)
  });

  private CreateDroneDto generateCreateDto() {
    Drone drone = drones.iterator().next();

    CreateDroneDto dto = new CreateDroneDto();
    dto.setName(drone.getName());
    dto.setLatitude(drone.getLatitude());
    dto.setLongitude(drone.getLongitude());

    return dto;
  }

  private UpdateDroneDto generateUpdateDto() {
    Drone drone = drones.iterator().next();

    UpdateDroneDto dto = new UpdateDroneDto();
    dto.setName(drone.getName());
    dto.setLatitude(drone.getLatitude());
    dto.setLongitude(drone.getLongitude());

    return dto;
  }

  @Test
  @DisplayName("1 - should return a list of drones")
  public void shouldReturnAListOfDrones() {
    doReturn(drones).when(droneRepository).findAll();

    Iterable<DroneDto> drones = droneUseCase.getAll();

    assertNotNull(drones);
    assertEquals(3, ((List<DroneDto>) drones).size());
  }

  @Test
  @DisplayName("2 - should return a drone by id")
  public void shouldReturnADroneById() {
    Drone droneMock = drones.iterator().next();
    doReturn(Optional.of(droneMock)).when(droneRepository).findById(anyString());

    DroneDto drone = droneUseCase.get(droneMock.getId());

    assertNotNull(drone);
  }

  @Test
  @DisplayName("3 - should throw an exception when drone not found by id")
  public void shouldThrowAnExceptionWhenDroneNotFound() {
    doReturn(Optional.empty()).when(droneRepository).findById(anyString());

    assertThrows(
            NotFoundException.class,
            () -> droneUseCase.get(UUID.randomUUID()),
            "Drone shoud not been found"
    );
  }

  @Test
  @DisplayName("4 - should create a drone")
  public void shouldCreateADrone() {
    Drone droneMock = drones.iterator().next();
    doReturn(droneMock).when(droneRepository).save(droneMock);

    CreateDroneDto createDroneDto = this.generateCreateDto();

    DroneDto drone = droneUseCase.create(createDroneDto);

    assertNotNull(drone);
  }

  @Test
  @DisplayName("5 - should update a drone")
  public void shouldUpdateADrone() {
    Drone droneMock = drones.iterator().next();
    doReturn(Optional.of(droneMock)).when(droneRepository).findById(anyString());

    UpdateDroneDto updateDroneDto = this.generateUpdateDto();

    DroneDto drone = droneUseCase.update(droneMock.getId(), updateDroneDto);

    assertNotNull(drone);
  }

  @Test
  @DisplayName("6 - should thrown an exception when drone not found to update")
  public void shouldThrownAnExceptionWhenDroneNotFoundToUpdate() {
    doReturn(Optional.empty()).when(droneRepository).findById(anyString());

    UpdateDroneDto updateDroneDto = this.generateUpdateDto();

    assertThrows(
            NotFoundException.class,
            () -> droneUseCase.update(UUID.randomUUID(), updateDroneDto),
            "Drone shoud not been found"
    );
  }

  @Test
  @DisplayName("7 - should delete a drone")
  public void shouldDeleteADrone() {
    Drone droneMock = drones.iterator().next();
    doReturn(Optional.of(droneMock)).when(droneRepository).findById(anyString());

    assertDoesNotThrow(() -> droneUseCase.delete(droneMock.getId()));
  }

  @Test
  @DisplayName("8 - should thrown an exception when drone not found to delete")
  public void shouldThrownAnExceptionWhenDroneNotFoundToDelete() {
    doReturn(Optional.empty()).when(droneRepository).findById(anyString());

    assertThrows(
            NotFoundException.class,
            () -> droneUseCase.delete(UUID.randomUUID()),
            "Drone shoud not been found"
    );
  }

  @Test
  @DisplayName("9 - should start a delivery")
  public void shouldStartADelivery() {
    Drone droneMock = drones.iterator().next();
    Delivery deliveryMock = DeliveryFactory.createWithDrone(droneMock);

    doReturn(Optional.of(droneMock)).when(droneRepository).findById(anyString());
    doReturn(Optional.of(deliveryMock)).when(deliveryRepository).findById(anyString());

    DroneDto drone = droneUseCase.startDelivery(droneMock.getId(), deliveryMock.getId());

    assertNotNull(drone);
    assertEquals(drone.getStatus(), DroneStatus.DELIVERING);
  }

  @Test
  @DisplayName("10 - should thrown an exception when drone not found to start delivery")
  public void shouldThrownAnExceptionWhenDroneNotFoundToStartDelivery() {
    doReturn(Optional.empty()).when(droneRepository).findById(anyString());

    assertThrows(
            NotFoundException.class,
            () -> droneUseCase.startDelivery(UUID.randomUUID(), UUID.randomUUID()),
            "Drone shoud not been found"
    );
  }

  @Test
  @DisplayName("11 - should thrown an exception when delivery not exists to start delivery")
  public void shouldThrownAnExceptionWhenDeliveryNotExistsToStartDelivery() {
    Drone droneMock = drones.iterator().next();
    doReturn(Optional.of(droneMock)).when(droneRepository).findById(anyString());
    doReturn(Optional.empty()).when(deliveryRepository).findById(anyString());

    assertThrows(
            NotFoundException.class,
            () -> droneUseCase.startDelivery(droneMock.getId(), UUID.randomUUID()),
            "Delivery shoud not been found"
    );
  }

}
