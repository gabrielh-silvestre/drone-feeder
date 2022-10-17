package com.example.trem.domain.video.factory;

import com.example.trem.domain.video.entity.Video;

import java.util.UUID;

public class VideoFactory {

  public static Video create(String fileName, Byte[] fileData) {
    return new Video(UUID.randomUUID(), fileName, fileData);
  }

}
