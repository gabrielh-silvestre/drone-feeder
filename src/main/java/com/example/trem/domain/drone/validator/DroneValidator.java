package com.example.trem.domain.drone.validator;

import com.example.trem.domain.drone.entity.DroneStatus;
import com.example.trem.domain.drone.entity.IDrone;
import com.example.trem.domain.drone.exception.DroneException;
import com.example.trem.domain.drone.exception.InvalidIdDroneException;
import com.example.trem.domain.drone.exception.InvalidNameDroneException;
import com.example.trem.domain.drone.exception.InvalidStatusDroneException;
import com.example.trem.domain.shared.IValidator;

import java.util.UUID;

public class DroneValidator implements IValidator<IDrone> {

  private static final int MAX_NAME_LENGTH = 12;
  private static final int MIN_NAME_LENGTH = 3;

  private void validateId(UUID id) throws DroneException {
    if (id == null || id.toString().isEmpty()) {
      throw new InvalidIdDroneException("Drone id is required");
    }
  }

  private void validateName(String name) throws DroneException {
    if (name == null) {
      throw new InvalidNameDroneException("Name is required");
    }

    if (name.isEmpty()) {
      throw new InvalidNameDroneException("Name cannot be empty");
    }

    if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
      String errorMessage = String.format(
              "Name must be between %d and %d characters",
              MIN_NAME_LENGTH, MAX_NAME_LENGTH
      );
      throw new InvalidNameDroneException(errorMessage);
    }
  }

  private void validateStatus(DroneStatus status) throws DroneException {
    if (status == null) {
      throw new InvalidStatusDroneException("Status is required");
    }
  }

  private void validateLocation(Double latitude, Double longitude) throws DroneException {
    if (latitude == null) {
      throw new DroneException("Latitude is required");
    }

    if (longitude == null) {
      throw new DroneException("Longitude is required");
    }
  }

  @Override
  public void validate(IDrone drone) throws RuntimeException {
    this.validateId(drone.getId());
    validateName(drone.getName());
    validateStatus(drone.getStatus());
    validateLocation(drone.getLatitude(), drone.getLongitude());
  }

}
