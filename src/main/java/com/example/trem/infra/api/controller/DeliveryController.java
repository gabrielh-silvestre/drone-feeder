package com.example.trem.infra.api.controller;

import com.example.trem.useCase.delivery.DeliveryUseCase;
import com.example.trem.useCase.delivery.dto.DeliveryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

  @Autowired
  private DeliveryUseCase deliveryUseCase;

  @PostMapping("/{droneId}")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public DeliveryDto create(@PathVariable UUID droneId) {
    return deliveryUseCase.create(droneId);
  }

  @GetMapping
  @ResponseBody
  public Iterable<DeliveryDto> getAll() {
    return deliveryUseCase.getAll();
  }

}
