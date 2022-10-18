package com.example.trem.domain.video;

import com.example.trem.domain.delivery.entity.Delivery;
import com.example.trem.domain.delivery.factory.DeliveryFactory;
import com.example.trem.domain.video.entity.Video;
import com.example.trem.domain.video.exception.VideoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Unit tests for domain Video entity")
public class TestVideoEntity {

  @Test
  @DisplayName("1 - should create a Video entity")
  public void shouldCreateVideoEntity() {
    Byte[] fakeFileData = new Byte[0];
    Video video = new Video(UUID.randomUUID(), "Video-1.mp4", fakeFileData);

    assertNotNull(video.getId());
    assertEquals("Video-1.mp4", video.getFileName());
    assertNotNull(video.getData());
    assertNull(video.getDelivery());
  }

  @Test
  @DisplayName("2 - should create a Video entity with delivery")
  public void shouldCreateVideoEntityWithDelivery() {
    Delivery delivery = DeliveryFactory.create();
    Byte[] fakeFileData = new Byte[0];
    Video video = new Video(UUID.randomUUID(), "Video-1.mp4", fakeFileData, delivery);

    assertNotNull(video.getId());
    assertEquals("Video-1.mp4", video.getFileName());
    assertNotNull(video.getData());
    assertNotNull(video.getDelivery());
  }

  @Test
  @DisplayName("3 - should throw an exception when creating a Video entity with invalid data")
  public void shouldThrowExceptionWhenCreatingVideoEntityWithInvalidData() {
    Byte[] fakeFileData = new Byte[0];

    assertThrows(
            NullPointerException.class,
            () -> new Video(null, "Video-1.mp4", fakeFileData)
    );

    assertThrows(
            VideoException.class,
            () -> new Video(UUID.randomUUID(), null, fakeFileData)
    );
  }

}
