package com.thirdeye3_2.video.manager.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thirdeye3_2.video.manager.entities.Advertisement;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, UUID> {

    List<Advertisement> findAllByOrderByCreatedTimeDesc();

    List<Advertisement> findByActiveTrueOrderByCreatedTimeDesc();
}
