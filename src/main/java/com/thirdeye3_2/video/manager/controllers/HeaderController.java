package com.thirdeye3_2.video.manager.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thirdeye3_2.video.manager.dtos.HeaderDto;
import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.services.HeaderService;

@RestController
@RequestMapping("/vm2/header")
public class HeaderController {

    @Autowired
    private HeaderService service;

    @PostMapping
    public Response<HeaderDto> create(@RequestBody HeaderDto dto) {
        return new Response<>(true, 0, null, service.create(dto));
    }

    @PutMapping("/{id}")
    public Response<HeaderDto> update(@PathVariable UUID id,
                                      @RequestBody HeaderDto dto) {
        return new Response<>(true, 0, null, service.update(id, dto));
    }

    @GetMapping("/{id}")
    public Response<HeaderDto> getById(@PathVariable UUID id) {
        return new Response<>(true, 0, null, service.getById(id));
    }

    @GetMapping
    public Response<List<HeaderDto>> getAll() {
        return new Response<>(true, 0, null, service.getAll());
    }

    @DeleteMapping("/{id}")
    public Response<String> delete(@PathVariable UUID id) {
        service.delete(id);
        return new Response<>(true, 0, null, "Deleted successfully");
    }

    @PatchMapping("/{id}/activate")
    public Response<HeaderDto> makeActive(@PathVariable UUID id) {
        return new Response<>(true, 0, null, service.makeActive(id));
    }

    @GetMapping("/active")
    public Response<HeaderDto> getActive() {
        return new Response<>(true, 0, null, service.getActive());
    }
}
