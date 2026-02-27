package com.thirdeye3_2.video.manager.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thirdeye3_2.video.manager.dtos.NewsTextSoundDto;
import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.services.NewsTextSoundService;

@RestController
@RequestMapping("/vm2/news-text-sound")
public class NewsTextSoundController {

    @Autowired
    private NewsTextSoundService service;

    @PostMapping
    public Response<NewsTextSoundDto> create(@RequestBody NewsTextSoundDto dto) {
        return new Response<>(true, 0, null, service.create(dto));
    }

    @PutMapping("/{id}")
    public Response<NewsTextSoundDto> update(@PathVariable UUID id,
                                             @RequestBody NewsTextSoundDto dto) {
        return new Response<>(true, 0, null, service.update(id, dto));
    }

    @GetMapping("/{id}")
    public Response<NewsTextSoundDto> getById(@PathVariable UUID id) {
        return new Response<>(true, 0, null, service.getById(id));
    }

    @GetMapping
    public Response<List<NewsTextSoundDto>> getAll() {
        return new Response<>(true, 0, null, service.getAll());
    }

    @DeleteMapping("/{id}")
    public Response<String> delete(@PathVariable UUID id) {
        service.delete(id);
        return new Response<>(true, 0, null, "Deleted successfully");
    }

    @PatchMapping("/{id}/activate")
    public Response<NewsTextSoundDto> makeActive(@PathVariable UUID id) {
        return new Response<>(true, 0, null, service.makeActive(id));
    }

    @GetMapping("/active")
    public Response<NewsTextSoundDto> getActive() {
        return new Response<>(true, 0, null, service.getActive());
    }
}
