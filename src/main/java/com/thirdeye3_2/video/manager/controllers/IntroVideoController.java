package com.thirdeye3_2.video.manager.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thirdeye3_2.video.manager.dtos.IntroVideoDto;
import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.services.IntroVideoService;

@RestController
@RequestMapping("/vm2/intro-video")
public class IntroVideoController {

    @Autowired
    private IntroVideoService service;

    @PostMapping
    public Response<IntroVideoDto> create(@RequestBody IntroVideoDto dto) {
        return new Response<>(true, 0, null, service.create(dto));
    }

    @PutMapping("/{id}")
    public Response<IntroVideoDto> update(@PathVariable UUID id,
                                          @RequestBody IntroVideoDto dto) {
        return new Response<>(true, 0, null, service.update(id, dto));
    }

    @GetMapping("/{id}")
    public Response<IntroVideoDto> getById(@PathVariable UUID id) {
        return new Response<>(true, 0, null, service.getById(id));
    }

    @GetMapping
    public Response<List<IntroVideoDto>> getAll() {
        return new Response<>(true, 0, null, service.getAll());
    }

    @DeleteMapping("/{id}")
    public Response<String> delete(@PathVariable UUID id) {
        service.delete(id);
        return new Response<>(true, 0, null, "Deleted successfully");
    }

    @PatchMapping("/{id}/activate")
    public Response<IntroVideoDto> makeActive(@PathVariable UUID id) {
        return new Response<>(true, 0, null, service.makeActive(id));
    }

    @GetMapping("/active")
    public Response<IntroVideoDto> getActive() {
        return new Response<>(true, 0, null, service.getActive());
    }
}
