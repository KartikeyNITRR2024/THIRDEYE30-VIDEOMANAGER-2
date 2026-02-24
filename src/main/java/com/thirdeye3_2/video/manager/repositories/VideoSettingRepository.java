package com.thirdeye3_2.video.manager.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.thirdeye3_2.video.manager.entities.VideoSetting;

public interface VideoSettingRepository extends JpaRepository<VideoSetting, UUID> {

    VideoSetting findByActiveTrue();

    @Modifying
    @Transactional
    void deleteById(UUID id);
}
