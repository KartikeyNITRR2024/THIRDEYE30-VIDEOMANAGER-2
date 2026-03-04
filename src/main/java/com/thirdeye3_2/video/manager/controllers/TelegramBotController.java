package com.thirdeye3_2.video.manager.controllers;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.dtos.TelegramBotDto;
import com.thirdeye3_2.video.manager.enums.BotType;
import com.thirdeye3_2.video.manager.services.TelegramBotService;

@RestController
@RequestMapping("/vm2/telegram-bots")
public class TelegramBotController {

    @Autowired
    private TelegramBotService service;

    @PostMapping
    public Response<TelegramBotDto> create(@RequestBody TelegramBotDto dto) {
        return new Response<>(true, 0, null, service.create(dto));
    }

    @PutMapping("/{id}")
    public Response<TelegramBotDto> update(@PathVariable UUID id,
                                           @RequestBody TelegramBotDto dto) {
        return new Response<>(true, 0, null, service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public Response<String> delete(@PathVariable UUID id) {
        service.delete(id);
        return new Response<>(true, 0, null, "Deleted Successfully");
    }

    @GetMapping("/{id}")
    public Response<TelegramBotDto> getById(@PathVariable UUID id) {
        return new Response<>(true, 0, null, service.getById(id));
    }

    @GetMapping
    public Response<List<TelegramBotDto>> getAll() {
        return new Response<>(true, 0, null, service.getAll());
    }

    @PatchMapping("/{id}/activate")
    public Response<TelegramBotDto> activate(@PathVariable UUID id) {
        return new Response<>(true, 0, null, service.activate(id));
    }

    @PatchMapping("/{id}/deactivate")
    public Response<TelegramBotDto> deactivate(@PathVariable UUID id) {
        return new Response<>(true, 0, null, service.deactivate(id));
    }

    @GetMapping("/filter/{botType}/{active}")
    public Response<List<TelegramBotDto>> getByTypeAndActive(
    		@PathVariable BotType botType,
    		@PathVariable Boolean active) {

        return new Response<>(true, 0, null,
                service.getByTypeAndActive(botType, active));
    }
    
    @GetMapping("/active/grouped")
    public Response<Map<BotType, List<TelegramBotDto>>> getAllActiveGrouped() {

        return new Response<>(
                true,
                0,
                null,
                service.getAllActiveGroupedByType()
        );
    }
}
