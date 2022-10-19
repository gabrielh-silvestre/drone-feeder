package com.example.trem.domain.delivery.entity;

import com.example.trem.domain.delivery.exception.DeliveryException;
import com.example.trem.domain.delivery.exception.InvalidStatusDeliveryException;
import com.example.trem.domain.delivery.factory.DeliveryValidatorFactory;
import com.example.trem.domain.drone.entity.Drone;
import com.example.trem.domain.video.entity.Video;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "deliveries")
public class Delivery implements IDelivery {

  @Id
  private String id;
  private DeliveryStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime orderDate;
  private LocalDateTime deliveryDate;
  private LocalDateTime cancelDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "drone_id")
  private Drone drone;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "video_id")
  @JsonIgnore
  private Video video;

  public Delivery(
          UUID id,
          DeliveryStatus status,
          LocalDateTime date,
          LocalDateTime orderDate,
          LocalDateTime deliveryDate,
          LocalDateTime cancelDate
  ) {
    this.id = id.toString();
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
    this.id = id.toString();
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
          Video video
  ) {
    this.id = id.toString();
    this.status = status;
    this.createdAt = date;
    this.orderDate = orderDate;
    this.deliveryDate = deliveryDate;
    this.cancelDate = cancelDate;
    this.drone = drone;
    this.video = video;

    this.validate();
  }

  protected Delivery() {}

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
    this.video = deliveryVideo;
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
    return UUID.fromString(this.id);
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
  public Video getVideo() {
    return video;
  }

}
