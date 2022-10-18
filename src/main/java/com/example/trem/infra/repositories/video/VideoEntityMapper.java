package com.example.trem.infra.repositories.video;

import com.example.trem.domain.video.entity.Video;

public class VideoEntityMapper {

  public static VideoEntity toEntity(Video video) {
    VideoEntity entity = new VideoEntity();

    entity.setId(video.getId());
    entity.setFileName(video.getFileName());
    entity.setData(video.getData());
    entity.setDelivery(video.getDelivery());

    return entity;
  }

  public static Video toDomain(VideoEntity entity) {
    return new Video(
            entity.getId(),
            entity.getFileName(),
            entity.getData(),
            entity.getDelivery()
    );
  }

}
