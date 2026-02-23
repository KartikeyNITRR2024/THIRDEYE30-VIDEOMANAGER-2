package com.thirdeye3_2.video.manager.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.dtos.StockGroupDto;
import com.thirdeye3_2.video.manager.services.StockGroupService;

@RestController
@RequestMapping("/vm2/stock-group")
public class StockGroupController {

    @Autowired
    private StockGroupService service;

    @PostMapping
    public Response<StockGroupDto> create(@RequestBody StockGroupDto dto) {
        return new Response<>(true, 0, null, service.create(dto));
    }

    @GetMapping("/{id}")
    public Response<StockGroupDto> get(@PathVariable UUID id) {
        return new Response<>(true, 0, null, service.getById(id));
    }

    @GetMapping
    public Response<List<StockGroupDto>> getAll() {
        return new Response<>(true, 0, null, service.getAll());
    }

    @PutMapping("/{id}")
    public Response<StockGroupDto> update(
            @PathVariable UUID id,
            @RequestBody StockGroupDto dto) {

        return new Response<>(true, 0, null,
                service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public Response<String> delete(@PathVariable UUID id) {
        service.delete(id);
        return new Response<>(true, 0, null,
                "Group deleted successfully");
    }

    @PatchMapping("/{id}/active")
    public Response<StockGroupDto> updateActive(
            @PathVariable UUID id,
            @RequestParam Boolean status) {

        return new Response<>(true, 0, null,
                service.updateActive(id, status));
    }
}
