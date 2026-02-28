package com.thirdeye3_2.video.manager.controllers;

import com.thirdeye3_2.video.manager.dtos.ContentGeneratorDto;
import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.enums.GeneratorType;
import com.thirdeye3_2.video.manager.services.ContentGeneratorService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vm2/content-generater")
public class ContentGeneratorController {

	@Autowired
    private ContentGeneratorService service;

    @PatchMapping("/{type}/{active}")
    public Response<ContentGeneratorDto> toggleActive(@PathVariable GeneratorType type,@PathVariable boolean active) {
        return new Response<>(true, 0, null, service.toggleActive(type, active));
    }
    
    @GetMapping("/{type}")
    public Response<ContentGeneratorDto> getByGeneratorType(@PathVariable GeneratorType type) {
        return new Response<>(true, 0, null, service.getByGeneratorType(type));
    }
    
    @GetMapping()
    public Response<List<ContentGeneratorDto>> getByGeneratorType() {
        return new Response<>(true, 0, null, service.getAll());
    }
}