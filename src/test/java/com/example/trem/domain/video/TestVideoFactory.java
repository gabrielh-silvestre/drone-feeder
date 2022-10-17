package com.example.trem.domain.video;

import com.example.trem.domain.delivery.entity.Delivery;
import com.example.trem.domain.delivery.factory.DeliveryFactory;
import com.example.trem.domain.video.entity.Video;
import com.example.trem.domain.video.factory.VideoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Unit tests for domain Video factory")
public class TestVideoFactory {

  @Test
  @DisplayName("should create a Video entity")
  public void shouldCreateVideoEntity() {
    Video video = VideoFactory.create("Video-1.mp4", new Byte[0]);

    assertNotNull(video.getId());
    assertEquals("Video-1.mp4", video.getFileName());
    assertNotNull(video.getData());
    assertNull(video.getDelivery());
  }

  @Test
  @DisplayName("should create a Video entity with delivery")
  public void shouldCreateVideoEntityWithDelivery() {
    Delivery delivery = DeliveryFactory.create();
    Video video = VideoFactory.createWithDelivery("Video-1.mp4", new Byte[0], delivery);

    assertNotNull(video.getId());
    assertEquals("Video-1.mp4", video.getFileName());
    assertNotNull(video.getData());
    assertNotNull(video.getDelivery());
  }

}
