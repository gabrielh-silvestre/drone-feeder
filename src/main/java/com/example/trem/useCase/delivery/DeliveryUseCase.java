package com.example.trem.useCase.delivery;

import com.example.trem.domain.delivery.entity.Delivery;
import com.example.trem.domain.delivery.factory.DeliveryFactory;
import com.example.trem.domain.drone.entity.Drone;
import com.example.trem.infra.repositories.delivery.DeliveryRepository;
import com.example.trem.infra.repositories.drone.DroneRepository;
import com.example.trem.useCase.delivery.dto.DeliveryDto;
import com.example.trem.useCase.delivery.dto.DeliveryDtoMapper;
import com.example.trem.useCase.shared.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeliveryUseCase {

  @Autowired
  private DeliveryRepository deliveryRepository;

  @Autowired
  private DroneRepository droneRepository;

  public DeliveryDto create(UUID droneId) {
    Optional<Drone> optDroneEntity = droneRepository.findById(droneId.toString());

    if (optDroneEntity.isEmpty()) {
      throw new NotFoundException("Drone not found");
    }

    Drone drone = optDroneEntity.get();
    Delivery delivery = DeliveryFactory.createWithDrone(drone);

    deliveryRepository.save(delivery);

    return DeliveryDtoMapper.toDto(delivery);
  }

  public DeliveryDto cancel(UUID id) {
    Optional<Delivery> optDelivery = deliveryRepository.findById(id.toString());

    if (optDelivery.isEmpty()) {
      throw new NotFoundException("Delivery not found");
    }

    Delivery delivery = optDelivery.get();
    delivery.cancel();

    deliveryRepository.save(delivery);

    return DeliveryDtoMapper.toDto(delivery);
  }

  public Iterable<DeliveryDto> getAll() {
    Iterable<Delivery> deliveryEntities = deliveryRepository.findAll();
    ArrayList<DeliveryDto> deliveryDtos = new ArrayList<>();

    deliveryEntities.forEach(deliveryEntity -> {
      deliveryDtos.add(DeliveryDtoMapper.toDto(deliveryEntity));
    });

    return deliveryDtos;
  }

}
