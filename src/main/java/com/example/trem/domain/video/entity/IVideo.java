package com.example.trem.domain.video.entity;

import java.util.UUID;

public interface IVideo {

  UUID getId();

  String getFileName();

  Byte[] getData();

}
