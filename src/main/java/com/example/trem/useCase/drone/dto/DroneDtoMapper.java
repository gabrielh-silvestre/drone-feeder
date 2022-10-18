package com.example.trem.useCase.drone.dto;

import com.example.trem.domain.drone.entity.Drone;

public class DroneDtoMapper {

  public static DroneDto toDto(Drone drone) {
    DroneDto droneDto = new DroneDto();

    droneDto.setId(drone.getId());
    droneDto.setName(drone.getName());
    droneDto.setLatitude(drone.getLatitude());
    droneDto.setLongitude(drone.getLongitude());
    droneDto.setStatus(drone.getStatus());

    return droneDto;
  }

}
