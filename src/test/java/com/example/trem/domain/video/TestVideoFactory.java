package com.example.trem.domain.video;

import com.example.trem.domain.video.entity.Video;
import com.example.trem.domain.video.factory.VideoFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Unit tests for domain Video factory")
public class TestVideoFactory {

  @Test
  @DisplayName("should create a Video entity")
  public void shouldCreateVideoEntity() {
    Video video = VideoFactory.create("Video-1.mp4", new Byte[0]);

    assertNotNull(video.getId());
    assertEquals("Video-1.mp4", video.getFileName());
    assertNotNull(video.getData());
  }

}
