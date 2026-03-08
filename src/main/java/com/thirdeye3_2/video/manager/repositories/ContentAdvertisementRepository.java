package com.thirdeye3_2.video.manager.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thirdeye3_2.video.manager.entities.ContentAdvertisement;

@Repository
public interface ContentAdvertisementRepository extends JpaRepository<ContentAdvertisement, UUID> {

    List<ContentAdvertisement> findAllByOrderByCreatedTimeDesc();

    List<ContentAdvertisement> findByActiveTrueOrderByCreatedTimeDesc();
}
