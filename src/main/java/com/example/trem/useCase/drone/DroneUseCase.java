package com.example.trem.useCase.drone;

import com.example.trem.domain.delivery.entity.Delivery;
import com.example.trem.domain.drone.entity.Drone;
import com.example.trem.domain.drone.factory.DroneFactory;
import com.example.trem.infra.repositories.delivery.DeliveryRepository;
import com.example.trem.infra.repositories.drone.DroneRepository;
import com.example.trem.useCase.drone.dto.CreateDroneDto;
import com.example.trem.useCase.drone.dto.DroneDto;
import com.example.trem.useCase.drone.dto.DroneDtoMapper;
import com.example.trem.useCase.drone.dto.UpdateDroneDto;
import com.example.trem.useCase.shared.exception.ConflictException;
import com.example.trem.useCase.shared.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class DroneUseCase {

  @Autowired
  private DroneRepository droneRepository;

  @Autowired
  private DeliveryRepository deliveryRepository;

  public DroneDto create(CreateDroneDto dto) {
    Drone newDrone = DroneFactory.create(dto.getName(), dto.getLatitude(), dto.getLongitude());

    boolean isDroneNameAlreadyUsed = droneRepository.existsByName(newDrone.getName());

    if (isDroneNameAlreadyUsed) {
      throw new ConflictException("Drone name already used");
    }

    droneRepository.save(newDrone);

    return DroneDtoMapper.toDto(newDrone);
  }

  public DroneDto update(UUID id, UpdateDroneDto dto) {
    Optional<Drone> optDrone = droneRepository.findById(id.toString());

    if (optDrone.isEmpty()) {
      throw new NotFoundException("Drone not found");
    }

    Drone foundDrone = optDrone.get();

    foundDrone.rename(dto.getName());
    foundDrone.updateLocation(dto.getLatitude(), dto.getLongitude());

    droneRepository.save(foundDrone);

    return DroneDtoMapper.toDto(foundDrone);
  }

  public DroneDto startDelivery(UUID id, UUID deliveryId) {
    Optional<Drone> optDrone = droneRepository.findById(id.toString());
    if (optDrone.isEmpty()) {
      throw new NotFoundException("Drone not found");
    }

    Optional<Delivery> optDelivery = deliveryRepository.findById(deliveryId.toString());
    if (optDelivery.isEmpty()) {
      throw new NotFoundException("Delivery not found");
    }

    Drone foundDrone = optDrone.get();
    Delivery foundDelivery = optDelivery.get();

    foundDrone.deliver(foundDelivery);
    droneRepository.save(foundDrone);

    return DroneDtoMapper.toDto(foundDrone);
  }

  @Transactional
  public void delete(String name) {
    boolean isDroneExist = droneRepository.existsByName(name);

    if (!isDroneExist) {
      throw new NotFoundException("Drone not found");
    }

    droneRepository.deleteByName(name);
  }

  public DroneDto get(UUID id) {
    Optional<Drone> optDrone = droneRepository.findById(id.toString());

    if (optDrone.isEmpty()) {
      throw new NotFoundException("Drone not found");
    }

    Drone foundDrone = optDrone.get();

    return DroneDtoMapper.toDto(foundDrone);
  }

  public Iterable<DroneDto> getAll() {
    Iterable<Drone> drones = droneRepository.findAll();
    ArrayList<DroneDto> dronesDto = new ArrayList<>();

    drones.forEach(drone -> dronesDto.add(DroneDtoMapper.toDto(drone)));

    return dronesDto;
  }

}
