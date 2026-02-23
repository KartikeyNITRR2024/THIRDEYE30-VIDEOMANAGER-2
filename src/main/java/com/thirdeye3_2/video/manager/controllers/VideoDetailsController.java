package com.thirdeye3_2.video.manager.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.dtos.VideoDetailsDto;
import com.thirdeye3_2.video.manager.services.VideoDetailsService;

@RestController
@RequestMapping("/vm2/video-details")
public class VideoDetailsController {

    @Autowired
    private VideoDetailsService service;

    @PostMapping
    public Response<VideoDetailsDto> create(@RequestBody VideoDetailsDto dto) {
        return new Response<>(true, 0, null, service.create(dto));
    }

    @GetMapping("/{id}")
    public Response<VideoDetailsDto> get(@PathVariable UUID id) {
        return new Response<>(true, 0, null, service.getById(id));
    }

    @GetMapping
    public Response<List<VideoDetailsDto>> getAll() {
        return new Response<>(true, 0, null, service.getAll());
    }

    @PutMapping("/{id}")
    public Response<VideoDetailsDto> update(@PathVariable UUID id,
                                            @RequestBody VideoDetailsDto dto) {
        return new Response<>(true, 0, null, service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public Response<String> delete(@PathVariable UUID id) {
        service.delete(id);
        return new Response<>(true, 0, null, "VideoDetails deleted successfully");
    }
    
    @GetMapping("/by-video/{videoId}")
    public Response<List<VideoDetailsDto>> getByVideoId(
            @PathVariable UUID videoId) {
        return new Response<>(true, 0, null, service.getByVideoId(videoId));
    }
}
