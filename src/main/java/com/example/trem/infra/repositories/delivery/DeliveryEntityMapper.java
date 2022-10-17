package com.example.trem.infra.repositories.delivery;

import com.example.trem.domain.delivery.entity.Delivery;

public class DeliveryEntityMapper {

  public static DeliveryEntity toEntity(Delivery delivery) {
    DeliveryEntity entity = new DeliveryEntity();

    entity.setId(delivery.getId());
    entity.setStatus(delivery.getStatus());
    entity.setCreatedAt(delivery.getCreatedAt());
    entity.setOrderDate(delivery.getOrderDate());
    entity.setDeliveryDate(delivery.getDeliveryDate());
    entity.setCancelDate(delivery.getCancelDate());

    return entity;
  }

  public static Delivery toDomain(DeliveryEntity entity) {
    return new Delivery(
            entity.getId(),
            entity.getStatus(),
            entity.getCreatedAt(),
            entity.getOrderDate(),
            entity.getDeliveryDate(),
            entity.getCancelDate()
    );
  }

}
