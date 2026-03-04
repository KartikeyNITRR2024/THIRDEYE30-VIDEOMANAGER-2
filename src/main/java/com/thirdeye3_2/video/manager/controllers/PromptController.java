package com.thirdeye3_2.video.manager.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thirdeye3_2.video.manager.dtos.PromptDetailDto;
import com.thirdeye3_2.video.manager.dtos.PromptDto;
import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.enums.PromptType;
import com.thirdeye3_2.video.manager.services.PromptService;

@RestController
@RequestMapping("/vm2/prompts")
public class PromptController {

    @Autowired
    private PromptService service;

    @PostMapping
    public Response<PromptDetailDto> create(@RequestBody PromptDetailDto dto) {
        return new Response<>(true, 0, null, service.create(dto));
    }

    @PutMapping("/{id}")
    public Response<PromptDetailDto> update(@PathVariable UUID id,
                                            @RequestBody PromptDetailDto dto) {
        return new Response<>(true, 0, null, service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public Response<String> delete(@PathVariable UUID id) {
        service.delete(id);
        return new Response<>(true, 0, null, "Deleted Successfully");
    }

    @GetMapping
    public Response<List<PromptDto>> getAll() {
        return new Response<>(true, 0, null, service.getAll());
    }

    @GetMapping("/type/{type}")
    public Response<List<PromptDto>> getByType(@PathVariable PromptType type) {
        return new Response<>(true, 0, null, service.getByType(type));
    }

    @GetMapping("/{id}/full")
    public Response<PromptDetailDto> getFullPrompt(@PathVariable UUID id) {
        return new Response<>(true, 0, null,
                service.getPromptByIdAndUpdateLastUsed(id));
    }
}
