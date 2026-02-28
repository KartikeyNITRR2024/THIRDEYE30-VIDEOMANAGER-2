package com.thirdeye3_2.video.manager.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.dtos.StockRaceDto;
import com.thirdeye3_2.video.manager.services.StockRaceService;

@RestController
@RequestMapping("/vm2/stock-race")
public class StockRaceController {

    @Autowired
    private StockRaceService service;

    @PostMapping
    public Response<StockRaceDto> create(@RequestBody StockRaceDto dto) {
        return new Response<>(true, 0, null, service.create(dto));
    }

    @PutMapping("/{id}")
    public Response<StockRaceDto> update(@PathVariable UUID id,
                                         @RequestBody StockRaceDto dto) {
        return new Response<>(true, 0, null, service.update(id, dto));
    }

    @GetMapping("/{id}")
    public Response<StockRaceDto> getById(@PathVariable UUID id) {
        return new Response<>(true, 0, null, service.getById(id));
    }

    @GetMapping
    public Response<List<StockRaceDto>> getAll() {
        return new Response<>(true, 0, null, service.getAll());
    }

    @DeleteMapping("/{id}")
    public Response<String> delete(@PathVariable UUID id) {
        service.delete(id);
        return new Response<>(true, 0, null, "Deleted successfully");
    }

    @PatchMapping("/{id}/activate")
    public Response<StockRaceDto> makeActive(@PathVariable UUID id) {
        return new Response<>(true, 0, null, service.makeActive(id));
    }

    @GetMapping("/active")
    public Response<StockRaceDto> getActive() {
        return new Response<>(true, 0, null, service.getActive());
    }
}