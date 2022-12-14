package com.example.trem.infra.api.controller;

import com.example.trem.useCase.delivery.DeliveryUseCase;
import com.example.trem.useCase.delivery.dto.DeliveryDto;
import com.example.trem.useCase.delivery.dto.DeliveryWithVideoDto;
import com.example.trem.useCase.delivery.dto.FinishDeliveryDto;
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

  @PatchMapping("/{id}/finish")
  @ResponseBody
  public DeliveryDto finish(@PathVariable UUID id, @RequestBody FinishDeliveryDto dto) {
    return deliveryUseCase.finish(id, dto);
  }

  @PatchMapping("/{id}/cancel")
  @ResponseBody
  public DeliveryDto cancel(@PathVariable UUID id) {
    return deliveryUseCase.cancel(id);
  }

  @GetMapping
  @ResponseBody
  public Iterable<DeliveryWithVideoDto> getAll() {
    return deliveryUseCase.getAll();
  }

}
