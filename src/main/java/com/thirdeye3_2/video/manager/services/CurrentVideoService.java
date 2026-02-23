package com.thirdeye3_2.video.manager.services;

import java.util.UUID;

import com.thirdeye3_2.video.manager.dtos.CurrentVideoDto;

public interface CurrentVideoService {
    CurrentVideoDto getCurrentVideo();
    CurrentVideoDto updateCurrentVideo(UUID uuid);
}
