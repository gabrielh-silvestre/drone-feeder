package com.example.trem.domain.drone.entity;

import com.example.trem.domain.delivery.entity.Delivery;
import com.example.trem.domain.drone.exception.DroneException;
import com.example.trem.domain.drone.factory.DroneValidatorFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Drone implements IDrone {

  private final UUID id;
  private String name;
  private Double latitude;
  private Double longitude;
  private DroneStatus status;
  private Set<Delivery> deliveries = new HashSet<>();

  public Drone(UUID id, String name, Double latitude, Double longitude, DroneStatus status) {
    this.id = id;
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
    this.status = status;

    this.validate();
  }

  public Drone(
          UUID id,
          String name,
          Double latitude,
          Double longitude,
          DroneStatus status,
          Set<Delivery> deliveries
  ) {
    this.id = id;
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
    this.status = status;
    this.deliveries = deliveries;

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

  public void deliver(Delivery delivery) {
    this.deliveries.stream()
            .filter(d -> d.getId().equals(delivery.getId()))
            .findFirst()
            .ifPresent(d -> {
              throw new DroneException("Delivery already assigned to this drone");
            });

    delivery.proccesOrder();
    this.deliveries.add(delivery);

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

  @Override
  public Set<Delivery> getDeliveries() {
    return deliveries;
  }

}
