package com.example.trem.domain.video.entity;

import com.example.trem.domain.video.exception.VideoException;
import com.example.trem.domain.video.factory.VideoValidatorFactory;

import java.util.UUID;

public class Video implements IVideo {

    private final UUID id;
    private final String fileName;
    private final Byte[] data;

    public Video(UUID id, String fileName, Byte[] data) {
      this.id = id;
      this.fileName = fileName;
      this.data = data;

      this.validate();
    }

    private void validate() throws VideoException {
      VideoValidatorFactory.create().validate(this);
    }

    @Override
    public UUID getId() {
      return id;
    }

    @Override
    public String getFileName() {
      return fileName;
    }

    @Override
    public Byte[] getData() {
      return data;
    }

}
