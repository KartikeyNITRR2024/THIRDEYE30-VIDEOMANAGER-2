package com.thirdeye3_2.video.manager.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thirdeye3_2.video.manager.dtos.OutroVideoDto;
import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.services.OutroVideoService;

@RestController
@RequestMapping("/vm2/outro-video")
public class OutroVideoController {

    @Autowired
    private OutroVideoService service;

    @PostMapping
    public Response<OutroVideoDto> create(@RequestBody OutroVideoDto dto) {
        return new Response<>(true, 0, null, service.create(dto));
    }

    @PutMapping("/{id}")
    public Response<OutroVideoDto> update(@PathVariable UUID id,
                                          @RequestBody OutroVideoDto dto) {
        return new Response<>(true, 0, null, service.update(id, dto));
    }

    @GetMapping("/{id}")
    public Response<OutroVideoDto> getById(@PathVariable UUID id) {
        return new Response<>(true, 0, null, service.getById(id));
    }

    @GetMapping
    public Response<List<OutroVideoDto>> getAll() {
        return new Response<>(true, 0, null, service.getAll());
    }

    @DeleteMapping("/{id}")
    public Response<String> delete(@PathVariable UUID id) {
        service.delete(id);
        return new Response<>(true, 0, null, "Deleted successfully");
    }

    @PatchMapping("/{id}/activate")
    public Response<OutroVideoDto> makeActive(@PathVariable UUID id) {
        
        return new Response<>(true, 0, null, service.makeActive(id));
    }

    @GetMapping("/active")
    public Response<OutroVideoDto> getActive() {
        return new Response<>(true, 0, null, service.getActive());
    }
}
