package com.thirdeye3_2.video.manager.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.dtos.VideoDto;
import com.thirdeye3_2.video.manager.services.VideoService;

@RestController
@RequestMapping("/vm2/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping
    public Response<VideoDto> create(@RequestBody VideoDto dto) {
        VideoDto created = videoService.create(dto);
        return new Response<>(true, 0, null, created);
    }

    @GetMapping("/{id}")
    public Response<VideoDto> get(@PathVariable UUID id) {
        VideoDto video = videoService.getById(id);
        return new Response<>(true, 0, null, video);
    }

    @GetMapping
    public Response<List<VideoDto>> getAll() {
        List<VideoDto> list = videoService.getAll();
        return new Response<>(true, 0, null, list);
    }

    @PutMapping("/{id}")
    public Response<VideoDto> update(@PathVariable UUID id,
                                     @RequestBody VideoDto dto) {
        VideoDto updated = videoService.update(id, dto);
        return new Response<>(true, 0, null, updated);
    }

    @DeleteMapping("/{id}")
    public Response<String> delete(@PathVariable UUID id) {
        videoService.delete(id);
        return new Response<>(true, 0, null, "Video deleted successfully");
    }
}
