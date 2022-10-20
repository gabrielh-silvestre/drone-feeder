package com.example.trem.infra.api.controller;

import com.example.trem.useCase.drone.DroneUseCase;
import com.example.trem.useCase.drone.dto.CreateDroneDto;
import com.example.trem.useCase.drone.dto.DroneDto;
import com.example.trem.useCase.drone.dto.UpdateDroneDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/drones")
public class DroneController {

  @Autowired
  private DroneUseCase droneUseCase;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public DroneDto createDrone(@RequestBody CreateDroneDto dto) {
    return this.droneUseCase.create(dto);
  }

  @PutMapping("/{id}")
  @ResponseBody
  public DroneDto updateDrone(@PathVariable String id, @RequestBody UpdateDroneDto dto) {
    return this.droneUseCase.update(UUID.fromString(id), dto);
  }

  @PatchMapping("/{id}/start/{deliveryId}")
  @ResponseBody
  public DroneDto startDrone(@PathVariable String id, @PathVariable String deliveryId) {
    return this.droneUseCase.startDelivery(UUID.fromString(id), UUID.fromString(deliveryId));
  }

  @DeleteMapping("/{name}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteDrone(@PathVariable String name) {
    this.droneUseCase.delete(name);
  }

  @GetMapping
  @ResponseBody
  public Iterable<DroneDto> getDrones() {
    return this.droneUseCase.getAll();
  }

  @GetMapping("/{id}")
  @ResponseBody
  public DroneDto getDrone(@PathVariable UUID id) {
    return this.droneUseCase.get(id);
  }

}
