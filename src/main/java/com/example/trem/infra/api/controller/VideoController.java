package com.example.trem.infra.api.controller;

import com.example.trem.useCase.video.VideoUseCase;
import com.example.trem.useCase.video.dto.VideoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/videos")
public class VideoController {

  @Autowired
  private VideoUseCase videoUseCase;

  @GetMapping
  @ResponseBody
  public Iterable<VideoDto> getAll() {
    return videoUseCase.getAll();
  }

  @GetMapping("/{id}")
  @ResponseBody
  public VideoDto get(@PathVariable UUID id) {
    return videoUseCase.get(id);
  }

  @GetMapping("/delivery/{deliveryId}")
  @ResponseBody
  public VideoDto getByDelivery(@PathVariable UUID deliveryId) {
    return videoUseCase.getByDelivery(deliveryId);
  }

}
