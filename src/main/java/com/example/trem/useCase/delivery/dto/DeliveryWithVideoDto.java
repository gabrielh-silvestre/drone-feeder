package com.example.trem.useCase.delivery.dto;

import java.util.UUID;

public class DeliveryWithVideoDto extends DeliveryDto {

  private UUID videoId;

  public UUID getVideoId() {
    return videoId;
  }

  public void setVideoId(UUID videoId) {
    this.videoId = videoId;
  }
}
