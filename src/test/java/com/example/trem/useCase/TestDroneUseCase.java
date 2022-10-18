package com.example.trem.useCase;

import com.example.trem.domain.delivery.factory.DeliveryFactory;
import com.example.trem.domain.drone.entity.Drone;
import com.example.trem.domain.drone.entity.DroneStatus;
import com.example.trem.domain.drone.factory.DroneFactory;
import com.example.trem.infra.repositories.delivery.DeliveryEntity;
import com.example.trem.infra.repositories.delivery.DeliveryEntityMapper;
import com.example.trem.infra.repositories.delivery.DeliveryRepository;
import com.example.trem.infra.repositories.drone.DroneEntity;
import com.example.trem.infra.repositories.drone.DroneEntityMapper;
import com.example.trem.infra.repositories.drone.DroneRepository;
import com.example.trem.useCase.drone.DroneUseCase;
import com.example.trem.useCase.drone.dto.CreateDroneDto;
import com.example.trem.useCase.drone.dto.DroneDto;
import com.example.trem.useCase.drone.dto.UpdateDroneDto;
import com.example.trem.useCase.shared.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

  private Drone drone;
  private DroneEntity droneEntity;

  @BeforeEach
  void setUp() {
    drone = DroneFactory.create("Drone 1", 0.0, 0.0);
    droneEntity = DroneEntityMapper.toEntity(drone);
  }

  private CreateDroneDto createDroneDto() {
    CreateDroneDto dto = new CreateDroneDto();

    dto.setName(this.drone.getName());
    dto.setLatitude(this.drone.getLatitude());
    dto.setLongitude(this.drone.getLongitude());

    return dto;
  }

  @Test
  @DisplayName("should create a drone with success")
  public void shouldCreateDroneWithSuccess() {
    doReturn(droneEntity).when(droneRepository).save(any(DroneEntity.class));

    CreateDroneDto dto = this.createDroneDto();
    DroneDto createdDrone = droneUseCase.create(dto);

    assertNotNull(createdDrone.getId());
  }

  @Test
  @DisplayName("should update a drone with success")
  public void shouldUpdateDroneWithSuccess() {
    doReturn(droneEntity).when(droneRepository).save(any(DroneEntity.class));
    doReturn(Optional.of(droneEntity)).when(droneRepository).findById(any(UUID.class));

    CreateDroneDto createDto = this.createDroneDto();
    DroneDto createdDrone = droneUseCase.create(createDto);

    UpdateDroneDto updateDto = new UpdateDroneDto();

    updateDto.setName("Drone 2");
    updateDto.setLatitude(10.0);
    updateDto.setLongitude(10.0);

    DroneDto updatedDrone = droneUseCase.update(createdDrone.getId(), updateDto);

    assertEquals(10.0, updatedDrone.getLatitude());
    assertEquals(10.0, updatedDrone.getLongitude());
  }

  @Test
  @DisplayName("should throw exception when drone not found on update")
  public void shouldThrowExceptionWhenDroneNotFound() {
    doReturn(droneEntity).when(droneRepository).save(any(DroneEntity.class));
    doReturn(Optional.empty()).when(droneRepository).findById(any(UUID.class));

    CreateDroneDto createDto = this.createDroneDto();
    droneUseCase.create(createDto);

    UpdateDroneDto updateDto = new UpdateDroneDto();
    assertThrows(NotFoundException.class, () -> droneUseCase.update(UUID.randomUUID(), updateDto));
  }

  @Test
  @DisplayName("should delete a drone with success")
  public void shouldDeleteDroneWithSuccess() {
    doReturn(droneEntity).when(droneRepository).save(any(DroneEntity.class));
    doReturn(Optional.of(droneEntity)).when(droneRepository).findById(any(UUID.class));

    CreateDroneDto createDto = this.createDroneDto();
    DroneDto createdDrone = droneUseCase.create(createDto);

    assertDoesNotThrow(() -> droneUseCase.delete(createdDrone.getId()));
  }

  @Test
  @DisplayName("should throw exception when drone not found on delete")
  public void shouldThrowExceptionWhenDroneNotFoundOnDelete() {
    doReturn(droneEntity).when(droneRepository).save(any(DroneEntity.class));
    doReturn(Optional.empty()).when(droneRepository).findById(any(UUID.class));

    CreateDroneDto createDto = this.createDroneDto();
    droneUseCase.create(createDto);

    assertThrows(NotFoundException.class, () -> droneUseCase.delete(UUID.randomUUID()));
  }

  @Test
  @DisplayName("should get a drone with success")
  public void shouldGetDroneWithSuccess() {
    doReturn(droneEntity).when(droneRepository).save(any(DroneEntity.class));
    doReturn(Optional.of(droneEntity)).when(droneRepository).findById(any(UUID.class));

    CreateDroneDto createDto = this.createDroneDto();
    DroneDto createdDrone = droneUseCase.create(createDto);

    DroneDto drone = droneUseCase.get(createdDrone.getId());

    assertNotNull(drone);
  }

  @Test
  @DisplayName("should throw exception when drone not found on get")
  public void shouldThrowExceptionWhenDroneNotFoundOnGet() {
    doReturn(droneEntity).when(droneRepository).save(any(DroneEntity.class));
    doReturn(Optional.empty()).when(droneRepository).findById(any(UUID.class));

    CreateDroneDto createDto = this.createDroneDto();
    droneUseCase.create(createDto);

    assertThrows(NotFoundException.class, () -> droneUseCase.get(UUID.randomUUID()));
  }

  @Test
  @DisplayName("should get all drones with success")
  public void shouldGetAllDronesWithSuccess() {
    doReturn(droneEntity).when(droneRepository).save(any(DroneEntity.class));
    doReturn(Optional.of(droneEntity)).when(droneRepository).findById(any(UUID.class));

    CreateDroneDto createDto = this.createDroneDto();
    droneUseCase.create(createDto);

    Iterable<DroneDto> drones = droneUseCase.getAll();

    assertNotNull(drones);
  }

  @Test
  @DisplayName("should start a delivery with success")
  public void shouldStartDeliveryWithSuccess() {
    DeliveryEntity deliveryEntity = DeliveryEntityMapper.toEntity(DeliveryFactory.createWithDrone(drone));

    doReturn(droneEntity).when(droneRepository).save(any(DroneEntity.class));
    doReturn(Optional.of(droneEntity)).when(droneRepository).findById(any(UUID.class));

    doReturn(deliveryEntity).when(deliveryRepository).save(any(DeliveryEntity.class));
    doReturn(Optional.of(deliveryEntity)).when(deliveryRepository).findById(any(UUID.class));

    CreateDroneDto createDto = this.createDroneDto();
    DroneDto createdDrone = droneUseCase.create(createDto);
    DroneDto startDto = droneUseCase.startDelivery(createdDrone.getId(), deliveryEntity.getId());
    
    assertEquals(DroneStatus.DELIVERING, startDto.getStatus());
  }

  @Test
  @DisplayName("should throw exception when drone not found on start delivery")
  public void shouldThrowExceptionWhenDroneNotFoundOnStartDelivery() {
    DeliveryEntity deliveryEntity = DeliveryEntityMapper.toEntity(DeliveryFactory.createWithDrone(drone));

    doReturn(droneEntity).when(droneRepository).save(any(DroneEntity.class));
    doReturn(Optional.empty()).when(droneRepository).findById(any(UUID.class));

    doReturn(deliveryEntity).when(deliveryRepository).save(any(DeliveryEntity.class));
    doReturn(Optional.of(deliveryEntity)).when(deliveryRepository).findById(any(UUID.class));

    CreateDroneDto createDto = this.createDroneDto();
    droneUseCase.create(createDto);

    assertThrows(NotFoundException.class, () -> droneUseCase.startDelivery(UUID.randomUUID(), deliveryEntity.getId()));
  }

  @Test
  @DisplayName("should throw exception when delivery not found on start delivery")
  public void shouldThrowExceptionWhenDeliveryNotFoundOnStartDelivery() {
    DeliveryEntity deliveryEntity = DeliveryEntityMapper.toEntity(DeliveryFactory.createWithDrone(drone));

    doReturn(droneEntity).when(droneRepository).save(any(DroneEntity.class));
    doReturn(Optional.of(droneEntity)).when(droneRepository).findById(any(UUID.class));

    doReturn(deliveryEntity).when(deliveryRepository).save(any(DeliveryEntity.class));
    doReturn(Optional.empty()).when(deliveryRepository).findById(any(UUID.class));

    CreateDroneDto createDto = this.createDroneDto();
    DroneDto createdDrone = droneUseCase.create(createDto);

    assertThrows(NotFoundException.class, () -> droneUseCase.startDelivery(createdDrone.getId(), UUID.randomUUID()));
  }

}
