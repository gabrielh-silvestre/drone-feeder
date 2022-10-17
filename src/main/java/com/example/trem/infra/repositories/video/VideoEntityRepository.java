package com.example.trem.infra.repositories.video;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VideoEntityRepository extends JpaRepository<VideoEntity, UUID> {
}