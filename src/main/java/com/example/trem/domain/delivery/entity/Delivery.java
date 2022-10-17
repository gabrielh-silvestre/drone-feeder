package com.example.trem.domain.delivery.entity;

import com.example.trem.domain.delivery.exception.DeliveryException;
import com.example.trem.domain.delivery.exception.InvalidStatusDeliveryException;
import com.example.trem.domain.delivery.factory.DeliveryValidatorFactory;
import com.example.trem.domain.drone.entity.Drone;
import com.example.trem.domain.video.entity.Video;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Delivery implements IDelivery {

  private final UUID id;
  private DeliveryStatus status;
  private final LocalDateTime createdAt;
  private LocalDateTime orderDate;
  private LocalDateTime deliveryDate;
  private LocalDateTime cancelDate;
  private Drone drone;
  private Set<Video> videos = new HashSet<>();

  public Delivery(
          UUID id,
          DeliveryStatus status,
          LocalDateTime date,
          LocalDateTime orderDate,
          LocalDateTime deliveryDate,
          LocalDateTime cancelDate
  ) {
    this.id = id;
    this.status = status;
    this.createdAt = date;
    this.orderDate = orderDate;
    this.deliveryDate = deliveryDate;
    this.cancelDate = cancelDate;

    this.validate();
  }

  public Delivery(
          UUID id,
          DeliveryStatus status,
          LocalDateTime date,
          LocalDateTime orderDate,
          LocalDateTime deliveryDate,
          LocalDateTime cancelDate,
          Drone drone
  ) {
    this.id = id;
    this.status = status;
    this.createdAt = date;
    this.orderDate = orderDate;
    this.deliveryDate = deliveryDate;
    this.cancelDate = cancelDate;
    this.drone = drone;

    this.validate();
  }

  public Delivery(
          UUID id,
          DeliveryStatus status,
          LocalDateTime date,
          LocalDateTime orderDate,
          LocalDateTime deliveryDate,
          LocalDateTime cancelDate,
          Drone drone,
          Set<Video> videos
  ) {
    this.id = id;
    this.status = status;
    this.createdAt = date;
    this.orderDate = orderDate;
    this.deliveryDate = deliveryDate;
    this.cancelDate = cancelDate;
    this.drone = drone;
    this.videos = videos;

    this.validate();
  }

  public void proccesOrder() throws DeliveryException {
    if (this.status != DeliveryStatus.PENDING) {
      throw new InvalidStatusDeliveryException("Delivery is not pending to be processed");
    }

    this.status = DeliveryStatus.IN_PROGRESS;
    this.orderDate = LocalDateTime.now();

    this.validate();
  }

  public void complete(Video deliveryVideo) throws DeliveryException {
    if (this.status != DeliveryStatus.IN_PROGRESS) {
      throw new InvalidStatusDeliveryException("Delivery is not in progress to be completed");
    }

    this.status = DeliveryStatus.DELIVERED;
    this.deliveryDate = LocalDateTime.now();
    this.videos.add(deliveryVideo);
    this.drone.returnToBase();

    this.validate();
  }

  public void cancel() throws DeliveryException {
    if (this.status != DeliveryStatus.PENDING && this.status != DeliveryStatus.IN_PROGRESS) {
      throw new InvalidStatusDeliveryException("Delivery is not pending or in progress to be canceled");
    }

    this.status = DeliveryStatus.CANCELLED;
    this.cancelDate = LocalDateTime.now();
    this.drone.returnToBase();

    this.validate();
  }

  private void validate() throws DeliveryException {
    DeliveryValidatorFactory.create().validate(this);
  }

  @Override
  public UUID getId() {
    return id;
  }

  @Override
  public DeliveryStatus getStatus() {
    return status;
  }

  @Override
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  @Override
  public LocalDateTime getOrderDate() {
    return orderDate;
  }

  @Override
  public LocalDateTime getDeliveryDate() {
    return deliveryDate;
  }

  @Override
  public LocalDateTime getCancelDate() {
    return cancelDate;
  }

  @Override
  public Drone getDrone() {
    return drone;
  }

  @Override
  public Set<Video> getVideos() {
    return videos;
  }

}
