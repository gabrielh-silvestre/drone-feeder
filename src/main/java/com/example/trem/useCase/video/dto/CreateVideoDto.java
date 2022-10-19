package com.example.trem.useCase.video.dto;

import com.example.trem.domain.delivery.entity.Delivery;

public class CreateVideoDto {

  private String fileName;

  private Byte[] data;

  private Delivery delivery;

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public Byte[] getData() {
    return data;
  }

  public void setData(Byte[] data) {
    this.data = data;
  }

  public Delivery getDelivery() {
    return delivery;
  }

  public void setDelivery(Delivery delivery) {
    this.delivery = delivery;
  }
}
