package com.example.trem.useCase.delivery.dto;

import com.example.trem.domain.delivery.entity.Delivery;
import com.example.trem.domain.video.entity.Video;

import java.time.format.DateTimeFormatter;

public class DeliveryDtoMapper {

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

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

  public static DeliveryWithVideoDto toDtoWithVideo(Delivery delivery) {
    DeliveryDto dto = toDto(delivery);

    DeliveryWithVideoDto deliveryWithVideoDto = new DeliveryWithVideoDto();
    Video video = delivery.getVideo();

    deliveryWithVideoDto.setId(dto.getId());
    deliveryWithVideoDto.setStatus(dto.getStatus());
    deliveryWithVideoDto.setCreatedAt(dto.getCreatedAt());
    deliveryWithVideoDto.setOrderDate(dto.getOrderDate());
    deliveryWithVideoDto.setDeliveryDate(dto.getDeliveryDate());
    deliveryWithVideoDto.setCancelDate(dto.getCancelDate());

    if (video == null) {
      deliveryWithVideoDto.setVideoId(null);
    } else {
      deliveryWithVideoDto.setVideoId(video.getId());
    }

    return deliveryWithVideoDto;
  }

}
