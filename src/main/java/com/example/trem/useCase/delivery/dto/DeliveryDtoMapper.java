package com.example.trem.useCase.delivery.dto;

import com.example.trem.domain.delivery.entity.Delivery;

import java.time.format.DateTimeFormatter;

public class DeliveryDtoMapper {

  private static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

  public static DeliveryDto toDto(Delivery delivery) {
    DeliveryDto deliveryDto = new DeliveryDto();

    deliveryDto.setId(delivery.getId());
    deliveryDto.setStatus(delivery.getStatus());
    deliveryDto.setCreatedAt(delivery.getCreatedAt().format(DATE_TIME_FORMATTER));

    if (delivery.getOrderDate() != null) {
      deliveryDto.setOrderDate(delivery.getOrderDate().format(DATE_TIME_FORMATTER));
    } else {
      deliveryDto.setOrderDate(null);
    }

    if (delivery.getDeliveryDate() != null) {
      deliveryDto.setDeliveryDate(delivery.getDeliveryDate().format(DATE_TIME_FORMATTER));
    } else {
      deliveryDto.setDeliveryDate(null);
    }

    if (delivery.getCancelDate() != null) {
      deliveryDto.setCancelDate(delivery.getCancelDate().format(DATE_TIME_FORMATTER));
    } else {
      deliveryDto.setCancelDate(null);
    }


    return deliveryDto;
  }

}
