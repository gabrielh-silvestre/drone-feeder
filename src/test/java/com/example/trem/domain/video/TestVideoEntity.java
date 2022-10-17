package com.example.trem.domain.video;

import com.example.trem.domain.video.entity.Video;
import com.example.trem.domain.video.exception.VideoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Unit tests for domain Video entity")
public class TestVideoEntity {

  @Test
  @DisplayName("should create a Video entity")
  public void shouldCreateVideoEntity() {
    Byte[] fakeFileData = new Byte[0];
    Video video = new Video(UUID.randomUUID(), "Video-1.mp4", fakeFileData);

    assertNotNull(video.getId());
    assertEquals("Video-1.mp4", video.getFileName());
    assertNotNull(video.getData());
  }

  @Test
  @DisplayName("should throw an exception when creating a Video entity with invalid data")
  public void shouldThrowExceptionWhenCreatingVideoEntityWithInvalidData() {
    Byte[] fakeFileData = new Byte[0];

    assertThrows(
            VideoException.class,
            () -> new Video(null, "Video-1.mp4", fakeFileData)
    );

    assertThrows(
            VideoException.class,
            () -> new Video(UUID.randomUUID(), null, fakeFileData)
    );
  }

}
