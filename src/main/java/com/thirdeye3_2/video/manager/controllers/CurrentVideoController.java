package com.thirdeye3_2.video.manager.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thirdeye3_2.video.manager.dtos.CurrentVideoDto;
import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.services.CurrentVideoService;

@RestController
@RequestMapping("/vm2/current-video")
public class CurrentVideoController {

    @Autowired
    private CurrentVideoService currentVideoService;

    @GetMapping
    public Response<CurrentVideoDto> getCurrentVideo() {
        return new Response<>(true, 0, null,
                currentVideoService.getCurrentVideo());
    }

    @PutMapping("/{videoId}")
    public Response<CurrentVideoDto> updateCurrentVideo(
            @PathVariable UUID videoId) {

        return new Response<>(true, 0, null,
                currentVideoService.updateCurrentVideo(videoId));
    }
}