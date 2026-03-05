package com.thirdeye3_2.video.manager.controllers;

import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.dtos.TtsCsvUploadDto;
import com.thirdeye3_2.video.manager.dtos.TtsSoundDto;
import com.thirdeye3_2.video.manager.services.TtsSoundService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vm2/tts-sounds")
@RequiredArgsConstructor
public class TtsSoundController {

    private final TtsSoundService service;

    @PostMapping
    public Response<TtsSoundDto> create(@RequestBody TtsSoundDto dto) {
        TtsSoundDto result = service.create(dto);
        return new Response<>(true, 0, null, result);
    }

    @GetMapping
    public Response<List<TtsSoundDto>> getAll() {
        List<TtsSoundDto> result = service.getAll();
        return new Response<>(true, 0, null, result);
    }

    @GetMapping("/active")
    public Response<List<TtsSoundDto>> getActive() {
        List<TtsSoundDto> result = service.getAllActive();
        return new Response<>(true, 0, null, result);
    }

    @PatchMapping("/{id}/{active}")
    public Response<TtsSoundDto> setStatus(@PathVariable UUID id, @PathVariable boolean active) {
        TtsSoundDto result = service.toggleStatus(id, active);
        return new Response<>(true, 0, null, result);
    }

    @PutMapping("/{id}")
    public Response<TtsSoundDto> update(@PathVariable UUID id, @RequestBody TtsSoundDto dto) {
        TtsSoundDto result = service.update(id, dto);
        return new Response<>(true, 0, null, result);
    }

    @DeleteMapping("/{id}")
    public Response<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return new Response<>(true, 0, null, null);
    }
    
    @GetMapping("/{id}")
    public Response<TtsSoundDto> getById(@PathVariable UUID id) {
        return new Response<>(true, 0, null, service.getById(id));
    }

    @PostMapping(value = "/upload-csv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response<String> uploadCsv(@ModelAttribute TtsCsvUploadDto csvDto) {
        service.uploadCsv(csvDto);
        return new Response<>(true, 0, null, "Csv uploaded successfully");
    }
}
