package com.example.trem.infra.repositories.drone;

import com.example.trem.domain.delivery.entity.Delivery;
import com.example.trem.domain.drone.entity.DroneStatus;
import com.example.trem.domain.drone.entity.IDrone;
import com.example.trem.infra.repositories.delivery.DeliveryEntity;
import com.example.trem.infra.repositories.delivery.DeliveryEntityMapper;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "drones")
public class DroneEntity implements IDrone {

  @Id
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Double latitude;

  @Column(nullable = false)
  private Double longitude;

  @Column(nullable = false)
  private DroneStatus status;

  @OneToMany(
          mappedBy = "drone",
          fetch = FetchType.LAZY,
          cascade = CascadeType.ALL
  )
  private Set<DeliveryEntity> deliveries;

  @Override
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  @Override
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  @Override
  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  @Override
  public DroneStatus getStatus() {
    return status;
  }

  public void setStatus(DroneStatus status) {
    this.status = status;
  }

  @Override
  public Set<Delivery> getDeliveries() {
    return this.deliveries.stream()
            .map(DeliveryEntityMapper::toDomain)
            .collect(Collectors.toSet());
  }

  public void setDeliveries(Set<Delivery> deliveries) {
    this.deliveries = deliveries.stream()
            .map(DeliveryEntityMapper::toEntity)
            .collect(Collectors.toSet());
  }

}
