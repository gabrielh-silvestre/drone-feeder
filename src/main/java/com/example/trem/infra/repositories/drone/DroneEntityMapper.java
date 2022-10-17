package com.example.trem.infra.repositories.drone;

import com.example.trem.domain.drone.entity.Drone;

public class DroneEntityMapper {

  public static DroneEntity toEntity(Drone drone) {
    DroneEntity entity = new DroneEntity();

    entity.setId(drone.getId());
    entity.setName(drone.getName());
    entity.setLatitude(drone.getLatitude());
    entity.setLongitude(drone.getLongitude());
    entity.setStatus(drone.getStatus());
    entity.setDeliveries(drone.getDeliveries());

    return entity;
  }

  public static Drone toDomain(DroneEntity entity) {
    return new Drone(
            entity.getId(),
            entity.getName(),
            entity.getLatitude(),
            entity.getLongitude(),
            entity.getStatus(),
            entity.getDeliveries()
    );
  }

}
