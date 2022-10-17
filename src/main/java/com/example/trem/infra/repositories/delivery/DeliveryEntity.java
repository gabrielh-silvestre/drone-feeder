package com.example.trem.infra.repositories.delivery;

import com.example.trem.domain.delivery.entity.DeliveryStatus;
import com.example.trem.domain.delivery.entity.IDelivery;
import com.example.trem.domain.drone.entity.Drone;
import com.example.trem.domain.video.entity.Video;
import com.example.trem.infra.repositories.drone.DroneEntity;
import com.example.trem.infra.repositories.drone.DroneEntityMapper;
import com.example.trem.infra.repositories.video.VideoEntity;
import com.example.trem.infra.repositories.video.VideoEntityMapper;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "deliveries")
public class DeliveryEntity implements IDelivery {

  @Id
  private UUID id;

  @Column(nullable = false)
  private DeliveryStatus status;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  private LocalDateTime orderDate;

  private LocalDateTime deliveryDate;

  private LocalDateTime cancelDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "drone_id")
  private DroneEntity drone;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "delivery", cascade = CascadeType.ALL)
  private VideoEntity video;

  @Override
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  @Override
  public DeliveryStatus getStatus() {
    return status;
  }

  public void setStatus(DeliveryStatus status) {
    this.status = status;
  }

  @Override
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public LocalDateTime getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(LocalDateTime orderDate) {
    this.orderDate = orderDate;
  }

  @Override
  public LocalDateTime getDeliveryDate() {
    return deliveryDate;
  }

  public void setDeliveryDate(LocalDateTime deliveryDate) {
    this.deliveryDate = deliveryDate;
  }

  @Override
  public LocalDateTime getCancelDate() {
    return cancelDate;
  }

  public void setCancelDate(LocalDateTime cancelDate) {
    this.cancelDate = cancelDate;
  }

  @Override
  public Drone getDrone() {
    return DroneEntityMapper.toDomain(drone);
  }

  public void setDrone(Drone drone) {
    this.drone = DroneEntityMapper.toEntity(drone);
  }

  @Override
  public Video getVideo() {
    return VideoEntityMapper.toDomain(video);
  }

  public void setVideo(Video video) {
    this.video = VideoEntityMapper.toEntity(video);
  }

}
