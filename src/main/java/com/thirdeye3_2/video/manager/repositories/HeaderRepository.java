package com.thirdeye3_2.video.manager.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thirdeye3_2.video.manager.entities.Header;

public interface HeaderRepository extends JpaRepository<Header, UUID> {

    Optional<Header> findByActiveTrue();
}
