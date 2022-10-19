package com.example.trem.infra.repositories.video;

import com.example.trem.domain.video.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {

  Iterable<Video> findByDeliveryId(String deliveryId);

}
