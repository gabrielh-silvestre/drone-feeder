package com.example.trem.domain.delivery.factory;

import com.example.trem.domain.delivery.entity.Delivery;
import com.example.trem.domain.delivery.entity.DeliveryStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class DeliveryFactory {

  public static Delivery create() {
    return new Delivery(
            UUID.randomUUID(),
            DeliveryStatus.PENDING,
            LocalDateTime.now(),
            null,
            null,
            null
    );
  }

}
