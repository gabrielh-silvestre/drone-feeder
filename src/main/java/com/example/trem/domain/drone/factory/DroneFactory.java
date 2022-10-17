package com.example.trem.domain.drone.factory;

import com.example.trem.domain.drone.entity.Drone;
import com.example.trem.domain.drone.entity.DroneStatus;

import java.util.UUID;

public class DroneFactory {

  public static Drone create(String name, Double latitude, Double longitude) {
    return new Drone(UUID.randomUUID(), name, latitude, longitude, DroneStatus.IDLE);
  }

}
