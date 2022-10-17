package com.example.trem.useCase.drone;

import com.example.trem.domain.drone.entity.Drone;
import com.example.trem.domain.drone.factory.DroneFactory;
import com.example.trem.infra.repositories.drone.DroneEntity;
import com.example.trem.infra.repositories.drone.DroneEntityMapper;
import com.example.trem.infra.repositories.drone.DroneRepository;
import com.example.trem.useCase.drone.dto.CreateDroneDto;
import com.example.trem.useCase.drone.dto.DroneDto;
import com.example.trem.useCase.drone.dto.DroneDtoMapper;
import com.example.trem.useCase.drone.dto.UpdateDroneDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class DroneUseCase {

  @Autowired
  private DroneRepository droneRepository;

  public DroneDto create(CreateDroneDto dto) {
    Drone newDrone = DroneFactory.create(dto.getName(), dto.getLatitude(), dto.getLongitude());
    droneRepository.save(DroneEntityMapper.toEntity(newDrone));

    return DroneDtoMapper.toDto(newDrone);
  }

  public DroneDto update(UUID id, UpdateDroneDto dto) {
    Optional<DroneEntity> optDrone = droneRepository.findById(id);

    if (optDrone.isEmpty()) {
      return null;
    }

    Drone foundDrone = DroneEntityMapper.toDomain(optDrone.get());
    foundDrone.rename(dto.getName());
    foundDrone.updateLocation(dto.getLatitude(), dto.getLongitude());

    droneRepository.save(DroneEntityMapper.toEntity(foundDrone));

    return DroneDtoMapper.toDto(foundDrone);
  }

  public void delete(UUID id) {
    droneRepository.deleteById(id);
  }

  public DroneDto get(UUID id) {
    Optional<DroneEntity> optDrone = droneRepository.findById(id);

    if (optDrone.isEmpty()) {
      return null;
    }

    Drone foundDrone = DroneEntityMapper.toDomain(optDrone.get());

    return DroneDtoMapper.toDto(foundDrone);
  }

  public Iterable<DroneDto> getAll() {
    Iterable<DroneEntity> drones = droneRepository.findAll();
    ArrayList<DroneDto> dronesDto = new ArrayList<>();

    drones.forEach(drone -> dronesDto.add(DroneDtoMapper.toDto(DroneEntityMapper.toDomain(drone))));

    return dronesDto;
  }

}