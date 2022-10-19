package com.example.trem.domain.video.entity;

import com.example.trem.domain.delivery.entity.Delivery;
import com.example.trem.domain.video.exception.VideoException;
import com.example.trem.domain.video.factory.VideoValidatorFactory;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Video implements IVideo {

  @Id
  private String id;
  private String fileName;
  private Byte[] data;

  @OneToOne(mappedBy = "video", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Delivery delivery;

  public Video(UUID id, String fileName, Byte[] data) {
    this.id = id.toString();
    this.fileName = fileName;
    this.data = data;

    this.validate();
  }

  public Video(UUID id, String fileName, Byte[] data, Delivery delivery) {
    this.id = id.toString();
    this.fileName = fileName;
    this.data = data;
    this.delivery = delivery;

    this.validate();
  }

  protected Video() {
  }

  private void validate() throws VideoException {
    VideoValidatorFactory.create().validate(this);
  }

  @Override
  public UUID getId() {
    return UUID.fromString(this.id);
  }

  @Override
  public String getFileName() {
    return fileName;
  }

  @Override
  public Byte[] getData() {
    return data;
  }

  @Override
  public Delivery getDelivery() {
    return delivery;
  }

}
