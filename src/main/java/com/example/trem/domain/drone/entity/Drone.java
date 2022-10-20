package com.example.trem.domain.drone.entity;

import com.example.trem.domain.delivery.entity.Delivery;
import com.example.trem.domain.drone.exception.DroneException;
import com.example.trem.domain.drone.factory.DroneValidatorFactory;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "drones")
public class Drone implements IDrone {

  @Id
  private String id;
  private String name;
  private Double latitude;
  private Double longitude;
  private DroneStatus status;

  @OneToMany(mappedBy = "drone", fetch = FetchType.LAZY)
  @JsonIgnore
  private Set<Delivery> deliveries = new HashSet<>();

  public Drone(UUID id, String name, Double latitude, Double longitude, DroneStatus status) {
    this.id = id.toString();
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
    this.id = id.toString();
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
    this.status = status;
    this.deliveries = deliveries;

    this.validate();
  }

  protected Drone() {}

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
    delivery.proccesOrder();
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
    return UUID.fromString(this.id);
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
