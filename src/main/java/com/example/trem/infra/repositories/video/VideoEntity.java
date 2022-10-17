package com.example.trem.infra.repositories.video;

import com.example.trem.domain.delivery.entity.Delivery;
import com.example.trem.domain.video.entity.IVideo;
import com.example.trem.infra.repositories.delivery.DeliveryEntity;
import com.example.trem.infra.repositories.delivery.DeliveryEntityMapper;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "videos")
public class VideoEntity implements IVideo {

  @Id
  private UUID id;

  @Column(nullable = false)
  private String fileName;

  @Lob
  @Column(nullable = false)
  private Byte[] data;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "video", cascade = CascadeType.ALL)
  private DeliveryEntity delivery;

  @Override
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  @Override
  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public Byte[] getData() {
    return data;
  }

  public void setData(Byte[] data) {
    this.data = data;
  }

  @Override
  public Delivery getDelivery() {
    return DeliveryEntityMapper.toDomain(delivery);
  }

  public void setDelivery(Delivery delivery) {
    this.delivery = DeliveryEntityMapper.toEntity(delivery);
  }
}
