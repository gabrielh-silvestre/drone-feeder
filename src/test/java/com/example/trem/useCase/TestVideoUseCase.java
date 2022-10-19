package com.example.trem.useCase;


import com.example.trem.domain.delivery.entity.Delivery;
import com.example.trem.domain.delivery.factory.DeliveryFactory;
import com.example.trem.domain.drone.entity.Drone;
import com.example.trem.domain.drone.factory.DroneFactory;
import com.example.trem.domain.video.entity.Video;
import com.example.trem.domain.video.factory.VideoFactory;
import com.example.trem.infra.repositories.video.VideoRepository;
import com.example.trem.useCase.shared.exception.NotFoundException;
import com.example.trem.useCase.video.VideoUseCase;
import com.example.trem.useCase.video.dto.VideoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;


@WebMvcTest(VideoUseCase.class)
@DisplayName("Unit tests for Video Use Case")
public class TestVideoUseCase {

  @Autowired
  private VideoUseCase videoUseCase;


  @MockBean
  private VideoRepository videoRepository;

  private final Iterable<Drone> drones = List.of(
          DroneFactory.create("Drone 1", 1.0, 1.0),
          DroneFactory.create("Drone 2", 2.0, 2.0),
          DroneFactory.create("Drone 3", 3.0, 3.0)
  );

  private final Iterable<Delivery> deliveries = List.of(
          DeliveryFactory.createWithDrone(drones.iterator().next()),
          DeliveryFactory.createWithDrone(drones.iterator().next())
  );

  private final Iterable<Video> videos = List.of(
          VideoFactory.createWithDelivery(new Byte[0], deliveries.iterator().next()),
          VideoFactory.createWithDelivery(new Byte[0], deliveries.iterator().next())
  );

  @Test
  @DisplayName("1 - should return all videos")
  public void testGetAllVideos() {
    doReturn(videos).when(videoRepository).findAll();

    Iterable<VideoDto> returnedVideos = videoUseCase.getAll();

    assertNotNull(returnedVideos, "The returned deliveries should not be null");
    assertEquals(2, ((List<VideoDto>) returnedVideos).size(), "The returned deliveries should have 2 elements");
  }

  @Test
  @DisplayName("2 - should return a video by id")
  public void testGetVideoById() {
    Video video = videos.iterator().next();
    doReturn(Optional.of(video)).when(videoRepository).findById(anyString());

    VideoDto returnedVideo = videoUseCase.get(video.getId());

    assertNotNull(returnedVideo, "The returned video should not be null");
    assertNotNull(returnedVideo.getId(), "The returned video should have an id");
  }

  @Test
  @DisplayName("3 - should throw an exception when the video is not found by id")
  public void testGetVideoByIdNotFound() {
    doReturn(Optional.empty()).when(videoRepository).findById(anyString());

    assertThrows(NotFoundException.class, () -> videoUseCase.get(UUID.randomUUID()));
  }

  @Test
  @DisplayName("4 - should return a video by delivery id")
  public void testGetVideoByDeliveryId() {
    Video video = videos.iterator().next();
    doReturn(Optional.of(video)).when(videoRepository).findByDeliveryId(anyString());

    VideoDto returnedVideo = videoUseCase.getByDelivery(video.getDelivery().getId());

    assertNotNull(returnedVideo, "The returned video should not be null");
    assertNotNull(returnedVideo.getId(), "The returned video should have an id");
  }

  @Test
  @DisplayName("5 - should throw an exception when the video is not found by delivery id")
  public void testGetVideoByDeliveryIdNotFound() {
    doReturn(Optional.empty()).when(videoRepository).findByDeliveryId(anyString());

    assertThrows(NotFoundException.class, () -> videoUseCase.getByDelivery(UUID.randomUUID()));
  }

}
