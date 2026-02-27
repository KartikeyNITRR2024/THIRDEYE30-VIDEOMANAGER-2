package com.thirdeye3_2.video.manager.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thirdeye3_2.video.manager.dtos.NewsImageDto;
import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.services.NewsImageService;

@RestController
@RequestMapping("/vm2/news-image")
public class NewsImageController {

    @Autowired
    private NewsImageService service;

    @PostMapping
    public Response<NewsImageDto> create(@RequestBody NewsImageDto dto) {
        return new Response<>(true, 0, null, service.create(dto));
    }

    @PutMapping("/{id}")
    public Response<NewsImageDto> update(@PathVariable UUID id,
                                         @RequestBody NewsImageDto dto) {
        return new Response<>(true, 0, null, service.update(id, dto));
    }

    @GetMapping("/{id}")
    public Response<NewsImageDto> getById(@PathVariable UUID id) {
        return new Response<>(true, 0, null, service.getById(id));
    }

    @GetMapping
    public Response<List<NewsImageDto>> getAll() {
        return new Response<>(true, 0, null, service.getAll());
    }

    @DeleteMapping("/{id}")
    public Response<String> delete(@PathVariable UUID id) {
        service.delete(id);
        return new Response<>(true, 0, null, "Deleted successfully");
    }

    @PatchMapping("/{id}/activate")
    public Response<NewsImageDto> makeActive(@PathVariable UUID id) {
        return new Response<>(true, 0, null, service.makeActive(id));
    }

    @GetMapping("/active")
    public Response<NewsImageDto> getActive() {
        return new Response<>(true, 0, null, service.getActive());
    }
}