package com.example.trem.useCase.delivery;

import com.example.trem.domain.delivery.entity.Delivery;
import com.example.trem.domain.delivery.factory.DeliveryFactory;
import com.example.trem.domain.drone.entity.Drone;
import com.example.trem.infra.repositories.delivery.DeliveryEntity;
import com.example.trem.infra.repositories.delivery.DeliveryEntityMapper;
import com.example.trem.infra.repositories.delivery.DeliveryRepository;
import com.example.trem.infra.repositories.drone.DroneEntity;
import com.example.trem.infra.repositories.drone.DroneEntityMapper;
import com.example.trem.infra.repositories.drone.DroneRepository;
import com.example.trem.useCase.delivery.dto.DeliveryDto;
import com.example.trem.useCase.delivery.dto.DeliveryDtoMapper;
import com.example.trem.useCase.shared.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeliveryUseCase {

  @Autowired
  private DeliveryRepository deliveryRepository;

  @Autowired
  private DroneRepository droneRepository;

  public DeliveryDto create(UUID droneId) {
    Optional<DroneEntity> optDroneEntity = droneRepository.findById(droneId);

    if (optDroneEntity.isEmpty()) {
      throw new NotFoundException("Drone not found");
    }

    Drone drone = DroneEntityMapper.toDomain(optDroneEntity.get());
    Delivery delivery = DeliveryFactory.createWithDrone(drone);

    deliveryRepository.save(DeliveryEntityMapper.toEntity(delivery));

    return DeliveryDtoMapper.toDto(delivery);
  }

  public DeliveryDto process(UUID id) {
    Optional<DeliveryEntity> optDelivery = deliveryRepository.findById(id);

    if (optDelivery.isEmpty()) {
      throw new NotFoundException("Delivery not found");
    }

    Delivery delivery = DeliveryEntityMapper.toDomain(optDelivery.get());
    delivery.proccesOrder();

    deliveryRepository.save(DeliveryEntityMapper.toEntity(delivery));

    return DeliveryDtoMapper.toDto(delivery);
  }

  public DeliveryDto cancel(UUID id) {
    Optional<DeliveryEntity> optDelivery = deliveryRepository.findById(id);

    if (optDelivery.isEmpty()) {
      throw new NotFoundException("Delivery not found");
    }

    Delivery delivery = DeliveryEntityMapper.toDomain(optDelivery.get());
    delivery.cancel();

    deliveryRepository.save(DeliveryEntityMapper.toEntity(delivery));

    return DeliveryDtoMapper.toDto(delivery);
  }

}
