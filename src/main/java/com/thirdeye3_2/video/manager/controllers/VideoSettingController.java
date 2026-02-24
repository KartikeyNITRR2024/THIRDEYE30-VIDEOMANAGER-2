package com.thirdeye3_2.video.manager.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.dtos.VideoSettingDto;
import com.thirdeye3_2.video.manager.services.VideoSettingService;

@RestController
@RequestMapping("/vm2/video-setting")
public class VideoSettingController {

    @Autowired
    private VideoSettingService service;

    @PostMapping
    public Response<VideoSettingDto> create(@RequestBody VideoSettingDto dto) {
        return new Response<>(true, 0, null, service.create(dto));
    }

    @GetMapping("/{id}")
    public Response<VideoSettingDto> get(@PathVariable UUID id) {
        return new Response<>(true, 0, null, service.getById(id));
    }

    @GetMapping
    public Response<List<VideoSettingDto>> getAll() {
        return new Response<>(true, 0, null, service.getAll());
    }

    @PutMapping("/{id}")
    public Response<VideoSettingDto> update(
            @PathVariable UUID id,
            @RequestBody VideoSettingDto dto) {

        return new Response<>(true, 0, null,
                service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public Response<String> delete(@PathVariable UUID id) {
        service.delete(id);
        return new Response<>(true, 0, null,
                "Video setting deleted successfully");
    }

    @PatchMapping("/{id}/activate")
    public Response<VideoSettingDto> activate(@PathVariable UUID id) {
        return new Response<>(true, 0, null,
                service.makeActive(id));
    }
    
    @GetMapping("/active")
    public Response<VideoSettingDto> getActive() {
        return new Response<>(
                true,
                0,
                null,
                service.getActiveSetting()
        );
    }
}
