package com.thirdeye3_2.video.manager.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thirdeye3_2.video.manager.entities.Stock;

public interface StockRepository extends JpaRepository<Stock, UUID> {
}