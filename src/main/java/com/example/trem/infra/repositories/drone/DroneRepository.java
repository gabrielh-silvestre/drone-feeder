package com.example.trem.infra.repositories.drone;

import com.example.trem.domain.drone.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {

  void deleteByName(String name);

  Optional<Drone> findByName(String name);

  boolean existsByName(String name);

}
