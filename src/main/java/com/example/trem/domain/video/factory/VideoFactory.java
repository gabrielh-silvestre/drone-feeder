package com.example.trem.domain.video.factory;

import com.example.trem.domain.delivery.entity.Delivery;
import com.example.trem.domain.video.entity.Video;

import java.util.UUID;

public class VideoFactory {

  public static Video create(Byte[] fileData) {
    UUID id = UUID.randomUUID();
    String randomFileName = id.toString() + ".mp4";
    return new Video(id, randomFileName, fileData);
  }

  public static Video createWithDelivery(Byte[] fileData, Delivery delivery) {
    UUID id = UUID.randomUUID();
    String randomFileName = id.toString() + ".mp4";
    return new Video(id, randomFileName, fileData, delivery);
  }

}
