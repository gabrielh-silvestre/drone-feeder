package com.example.trem.domain.video.factory;

import com.example.trem.domain.delivery.entity.Delivery;
import com.example.trem.domain.video.entity.Video;

import java.util.UUID;

public class VideoFactory {

  public static Video create(String fileName, Byte[] fileData) {
    return new Video(UUID.randomUUID(), fileName, fileData);
  }

  public static Video createWithDelivery(String fileName, Byte[] fileData, Delivery delivery) {
    return new Video(UUID.randomUUID(), fileName, fileData, delivery);
  }

}
