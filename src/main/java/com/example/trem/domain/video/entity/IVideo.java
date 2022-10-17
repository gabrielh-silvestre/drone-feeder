package com.example.trem.domain.video.entity;

import com.example.trem.domain.delivery.entity.Delivery;

import java.util.UUID;

public interface IVideo {

  UUID getId();

  String getFileName();

  Byte[] getData();

  Delivery getDelivery();

}
