package com.example.trem.domain.drone.entity;

import com.example.trem.domain.delivery.entity.Delivery;

import java.util.Set;
import java.util.UUID;

public interface IDrone {

  UUID getId();

  String getName();

  Double getLatitude();

  Double getLongitude();

  DroneStatus getStatus();

  Set<Delivery> getDeliveries();

}
