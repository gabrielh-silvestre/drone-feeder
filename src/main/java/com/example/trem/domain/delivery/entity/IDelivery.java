package com.example.trem.domain.delivery.entity;

import com.example.trem.domain.drone.entity.Drone;
import com.example.trem.domain.video.entity.Video;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IDelivery {

  UUID getId();

  DeliveryStatus getStatus();

  LocalDateTime getCreatedAt();

  LocalDateTime getOrderDate();

  LocalDateTime getDeliveryDate();

  LocalDateTime getCancelDate();

  Drone getDrone();

  Video getVideo();

}
