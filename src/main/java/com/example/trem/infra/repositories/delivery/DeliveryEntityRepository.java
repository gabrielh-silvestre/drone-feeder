package com.example.trem.infra.repositories.delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeliveryEntityRepository extends JpaRepository<DeliveryEntity, UUID> {
}