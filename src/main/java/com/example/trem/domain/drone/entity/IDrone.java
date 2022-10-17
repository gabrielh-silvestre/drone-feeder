package com.example.trem.domain.drone.entity;

import java.util.UUID;

public interface IDrone {

  UUID getId();

  String getName();

  Double getLatitude();

  Double getLongitude();

  DroneStatus getStatus();

}
