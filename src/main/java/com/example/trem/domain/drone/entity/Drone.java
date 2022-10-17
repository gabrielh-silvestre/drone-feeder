package com.example.trem.domain.drone.entity;

import com.example.trem.domain.drone.exception.DroneException;
import com.example.trem.domain.drone.factory.DroneValidatorFactory;

import java.util.UUID;

public class Drone implements IDrone {

  private final UUID id;
  private String name;
  private Double latitude;
  private Double longitude;
  private DroneStatus status;

  public Drone(UUID id, String name, Double latitude, Double longitude, DroneStatus status) {
    this.id = id;
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
    this.status = status;

    this.validate();
  }

  public void rename(String name) {
    this.name = name;

    this.validate();
  }

  public void updateLocation(Double latitude, Double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;

    this.validate();
  }

  public void idle() {
    this.status = DroneStatus.IDLE;
  }

  public void deliver() {
    this.status = DroneStatus.DELIVERING;
  }

  public void returnToBase() {
    this.status = DroneStatus.RETURNING;
  }

  private void validate() throws DroneException {
    DroneValidatorFactory.create().validate(this);
  }

  @Override
  public UUID getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Double getLatitude() {
    return latitude;
  }

  @Override
  public Double getLongitude() {
    return longitude;
  }

  @Override
  public DroneStatus getStatus() {
    return status;
  }

}
