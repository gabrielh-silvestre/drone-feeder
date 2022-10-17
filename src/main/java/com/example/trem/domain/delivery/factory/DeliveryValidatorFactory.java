package com.example.trem.domain.delivery.factory;

import com.example.trem.domain.delivery.entity.IDelivery;
import com.example.trem.domain.delivery.validator.DeliveryValidator;
import com.example.trem.domain.shared.IValidator;

public class DeliveryValidatorFactory {

  public static IValidator<IDelivery> create() {
    return new DeliveryValidator();
  }

}
