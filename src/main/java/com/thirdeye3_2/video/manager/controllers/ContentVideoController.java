package com.thirdeye3_2.video.manager.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thirdeye3_2.video.manager.dtos.ContentVideoDto;
import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.services.ContentVideoService;

@RestController
@RequestMapping("/vm2/content-video")
public class ContentVideoController {

    @Autowired
    private ContentVideoService service;

    @PostMapping
    public Response<ContentVideoDto> create(@RequestBody ContentVideoDto dto) {
        return new Response<>(true, 0, null, service.create(dto));
    }

    @PutMapping("/{id}")
    public Response<ContentVideoDto> update(@PathVariable UUID id,
                                            @RequestBody ContentVideoDto dto) {
        return new Response<>(true, 0, null, service.update(id, dto));
    }

    @GetMapping("/{id}")
    public Response<ContentVideoDto> getById(@PathVariable UUID id) {
        return new Response<>(true, 0, null, service.getById(id));
    }

    @GetMapping
    public Response<List<ContentVideoDto>> getAll() {
        return new Response<>(true, 0, null, service.getAll());
    }

    @DeleteMapping("/{id}")
    public Response<String> delete(@PathVariable UUID id) {
        service.delete(id);
        return new Response<>(true, 0, null, "Deleted successfully");
    }

    @PatchMapping("/{id}/activate")
    public Response<ContentVideoDto> makeActive(@PathVariable UUID id) {
        return new Response<>(true, 0, null, service.makeActive(id));
    }

    @GetMapping("/active")
    public Response<ContentVideoDto> getActive() {
        return new Response<>(true, 0, null, service.getActive());
    }
}
