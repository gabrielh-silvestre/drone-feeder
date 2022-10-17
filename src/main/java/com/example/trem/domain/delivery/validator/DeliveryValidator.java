package com.example.trem.domain.delivery.validator;

import com.example.trem.domain.delivery.entity.IDelivery;
import com.example.trem.domain.delivery.exception.DeliveryException;
import com.example.trem.domain.shared.IValidator;

import java.time.LocalDateTime;

public class DeliveryValidator implements IValidator<IDelivery> {

  private void validateId(IDelivery delivery) throws DeliveryException {
    if (delivery.getId() == null || delivery.getId().toString().isEmpty()) {
      throw new DeliveryException("Delivery id is required");
    }
  }

  private void validateStatus(IDelivery delivery) throws DeliveryException {
    if (delivery.getStatus() == null) {
      throw new DeliveryException("Delivery status is required");
    }
  }

  private void validateDate(IDelivery delivery) throws DeliveryException {
    if (delivery.getCreatedAt() == null) {
      throw new DeliveryException("Delivery creation date is required");
    }
  }

  @Override
  public void validate(IDelivery delivery) throws RuntimeException {
    validateId(delivery);
    validateStatus(delivery);
    validateDate(delivery);
  }

}
