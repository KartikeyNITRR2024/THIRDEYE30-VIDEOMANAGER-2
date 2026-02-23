package com.thirdeye3_2.video.manager.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thirdeye3_2.video.manager.dtos.AudioGenerateDto;
import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.services.AudioGenerateService;

@RestController
@RequestMapping("/vm2/audio-generate")
public class AudioGenerateController {

    @Autowired
    private AudioGenerateService service;

    @PostMapping
    public Response<AudioGenerateDto> create(@RequestBody AudioGenerateDto dto) {
        return new Response<>(true, 0, null, service.create(dto));
    }

    @GetMapping("/{id}")
    public Response<AudioGenerateDto> get(@PathVariable UUID id) {
        return new Response<>(true, 0, null, service.getById(id));
    }

    @GetMapping
    public Response<List<AudioGenerateDto>> getAll() {
        return new Response<>(true, 0, null, service.getAll());
    }

    @PutMapping("/{id}")
    public Response<AudioGenerateDto> update(@PathVariable UUID id,
                                             @RequestBody AudioGenerateDto dto) {
        return new Response<>(true, 0, null, service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public Response<String> delete(@PathVariable UUID id) {
        service.delete(id);
        return new Response<>(true, 0, null, "Deleted successfully");
    }

    @GetMapping("/pending")
    public Response<List<AudioGenerateDto>> getAllPending() {
        return new Response<>(true, 0, null, service.getAllNotGenerated());
    }
}
