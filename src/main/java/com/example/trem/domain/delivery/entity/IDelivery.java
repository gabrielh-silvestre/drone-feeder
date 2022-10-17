package com.example.trem.domain.delivery.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IDelivery {

  UUID getId();

  DeliveryStatus getStatus();

  LocalDateTime getCreatedAt();

  LocalDateTime getOrderDate();

  LocalDateTime getDeliveryDate();

  LocalDateTime getCancelDate();

}
