package com.example.trem.useCase.video.dto;

import com.example.trem.domain.video.entity.IVideo;

public class VideoDtoMapper {

    public static VideoDto toDto(IVideo video) {
      VideoDto dto = new VideoDto();

      dto.setId(video.getId());
      dto.setData(video.getData());
      dto.setDeliveryId(video.getDelivery().getId());

      return dto;
    }

}
