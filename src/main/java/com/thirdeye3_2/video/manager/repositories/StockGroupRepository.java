package com.thirdeye3_2.video.manager.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.thirdeye3_2.video.manager.entities.StockGroup;

public interface StockGroupRepository extends JpaRepository<StockGroup, UUID> {
	
	@Query("""
		       SELECT DISTINCT sg
		       FROM StockGroup sg
		       LEFT JOIN FETCH sg.listOfStock
		       WHERE sg.id = :id
		       """)
	Optional<StockGroup> findByIdWithStocks(UUID id);
}
