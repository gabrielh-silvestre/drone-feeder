package com.example.trem.useCase.video.dto;

import java.util.UUID;

public class VideoDto {

  private UUID id;

  private Byte[] data;

  private UUID deliveryId;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Byte[] getData() {
    return data;
  }

  public void setData(Byte[] data) {
    this.data = data;
  }

  public UUID getDeliveryId() {
    return deliveryId;
  }

  public void setDeliveryId(UUID deliveryId) {
    this.deliveryId = deliveryId;
  }
}
