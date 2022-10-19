package com.example.trem.infra.api.controller;

import com.example.trem.domain.delivery.entity.Delivery;
import com.example.trem.domain.delivery.factory.DeliveryFactory;
import com.example.trem.domain.drone.entity.Drone;
import com.example.trem.domain.drone.factory.DroneFactory;
import com.example.trem.domain.video.entity.Video;
import com.example.trem.domain.video.factory.VideoFactory;
import com.example.trem.infra.repositories.delivery.DeliveryRepository;
import com.example.trem.infra.repositories.drone.DroneRepository;
import com.example.trem.infra.repositories.video.VideoRepository;
import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Integration tests for api VideoController")
public class TestVideoController {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private DeliveryRepository deliveryRepository;

  @Autowired
  private DroneRepository droneRepository;

  @Autowired
  private VideoRepository videoRepository;

  private final List<Drone> drones = List.of(
          DroneFactory.create("Drone 1", 1.0, 1.0),
          DroneFactory.create("Drone 2", 2.0, 2.0),
          DroneFactory.create("Drone 3", 3.0, 3.0)
  );

  private final List<Delivery> deliveries = List.of(
          DeliveryFactory.createWithDrone(drones.get(0)),
          DeliveryFactory.createWithDrone(drones.get(2))
  );

  private final List<Video> videos = List.of(
          VideoFactory.createWithDelivery(new Byte[0], deliveries.get(0)),
          VideoFactory.createWithDelivery(new Byte[0], deliveries.get(1))
  );

//  @Test
//  @DisplayName("1 - should return all videos (GET /videos)")
//  public void testGetAllVideos() throws Exception {
//    mockMvc.perform(get("/videos")
//                    .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$").isArray())
//            .andExpect(status().isOk());
//  }

//  @Test
//  @DisplayName("2 - should return a video by id (GET /videos/{id})")
//  public void testGetVideoById() throws Exception {
//    Video video = videos.get(0);
//
//    droneRepository.saveAll(drones);
//    deliveryRepository.saveAll(deliveries);
//    videoRepository.saveAll(videos);
//
//    mockMvc.perform(get("/videos/" + video.getId())
//                    .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$.id").value(video.getId().toString()))
//            .andExpect(status().isOk());
//  }

//  @Test
//  @DisplayName("3 - should return a video by delivery id (GET /videos/delivery/{deliveryId})")
//  public void testGetVideoByDeliveryId() throws Exception {
//    Video video = videos.get(0);
//
//    droneRepository.saveAll(drones);
//    deliveryRepository.saveAll(deliveries);
//    videoRepository.saveAll(videos);

//    mockMvc.perform(get("/videos/delivery/" + video.getDelivery().getId())
//                    .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$.id").value(video.getId().toString()))
//            .andExpect(status().isOk());
//  }

}
