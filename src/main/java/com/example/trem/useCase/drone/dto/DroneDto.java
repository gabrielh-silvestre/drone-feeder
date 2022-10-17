package com.example.trem.useCase.drone.dto;

import com.example.trem.domain.drone.entity.DroneStatus;

import java.util.UUID;

public class DroneDto {
  private UUID id;
  private String name;
  private Double latitude;
  private Double longitude;
  private DroneStatus status;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public DroneStatus getStatus() {
    return status;
  }

  public void setStatus(DroneStatus status) {
    this.status = status;
  }

}
