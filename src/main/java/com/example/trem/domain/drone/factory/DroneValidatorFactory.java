package com.example.trem.domain.drone.factory;

import com.example.trem.domain.drone.entity.IDrone;
import com.example.trem.domain.drone.validator.DroneValidator;
import com.example.trem.domain.shared.IValidator;

public class DroneValidatorFactory {

  public static IValidator<IDrone> create() {
    return new DroneValidator();
  }

}
