package com.example.trem.domain.delivery;

import com.example.trem.domain.delivery.entity.Delivery;
import com.example.trem.domain.delivery.entity.DeliveryStatus;
import com.example.trem.domain.delivery.factory.DeliveryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Unit tests for domain Delivery factory")
public class TestDeliveryFactory {

  @Test
  @DisplayName("should create a Delivery entity")
  public void shouldCreateDeliveryEntity() {
    Delivery delivery = DeliveryFactory.create();

    assertNotNull(delivery.getId());
    assertEquals(DeliveryStatus.PENDING, delivery.getStatus());
    assertNotNull(delivery.getCreatedAt());
    assertNull(delivery.getOrderDate());
    assertNull(delivery.getDeliveryDate());
    assertNull(delivery.getCancelDate());
  }

}
