package com.thirdeye3_2.video.manager.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.dtos.StockDto;
import com.thirdeye3_2.video.manager.services.StockService;

@RestController
@RequestMapping("/vm2/stock")
public class StockController {

    @Autowired
    private StockService service;

    @PostMapping
    public Response<StockDto> create(@RequestBody StockDto dto) {
        return new Response<>(true, 0, null, service.create(dto));
    }

    @GetMapping("/{id}")
    public Response<StockDto> get(@PathVariable UUID id) {
        return new Response<>(true, 0, null, service.getById(id));
    }

    @GetMapping
    public Response<List<StockDto>> getAll() {
        return new Response<>(true, 0, null, service.getAll());
    }

    @PutMapping("/{id}")
    public Response<StockDto> update(
            @PathVariable UUID id,
            @RequestBody StockDto dto) {

        return new Response<>(true, 0, null,
                service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public Response<String> delete(@PathVariable UUID id) {
        service.delete(id);
        return new Response<>(true, 0, null,
                "Stock deleted successfully");
    }

    @PatchMapping("/{id}/active")
    public Response<StockDto> updateActive(
            @PathVariable UUID id,
            @RequestParam Boolean status) {

        return new Response<>(true, 0, null,
                service.updateActive(id, status));
    }
}
