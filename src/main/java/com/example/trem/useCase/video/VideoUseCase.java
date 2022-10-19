package com.example.trem.useCase.video;

import com.example.trem.domain.video.entity.Video;
import com.example.trem.infra.repositories.video.VideoRepository;
import com.example.trem.useCase.shared.exception.NotFoundException;
import com.example.trem.useCase.video.dto.VideoDto;
import com.example.trem.useCase.video.dto.VideoDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class VideoUseCase {

  @Autowired
  private VideoRepository videoRepository;

  public Iterable<VideoDto> getAll() {
    Iterable<Video> videoEntities = videoRepository.findAll();
    ArrayList<VideoDto> videoDtos = new ArrayList<>();

    videoEntities.forEach(videoEntity -> {
      videoDtos.add(VideoDtoMapper.toDto(videoEntity));
    });

    return videoDtos;
  }

  public VideoDto get(UUID videoId) {
    Optional<Video> optVideoEntity = videoRepository.findById(videoId.toString());

    if (optVideoEntity.isEmpty()) {
      throw new NotFoundException("Video not found");
    }

    return VideoDtoMapper.toDto(optVideoEntity.get());
  }

  public Iterable<VideoDto> getByDelivery(UUID deliveryId) {
    Iterable<Video> videoEntities = videoRepository.findByDeliveryId(deliveryId.toString());
    ArrayList<VideoDto> videoDtos = new ArrayList<>();

    videoEntities.forEach(videoEntity -> {
      videoDtos.add(VideoDtoMapper.toDto(videoEntity));
    });
    
    return videoDtos;
  }

}
