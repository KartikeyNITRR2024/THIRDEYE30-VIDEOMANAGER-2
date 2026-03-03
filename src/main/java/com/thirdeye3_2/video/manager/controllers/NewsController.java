package com.thirdeye3_2.video.manager.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import com.thirdeye3_2.video.manager.dtos.NewsCsvDto;
import com.thirdeye3_2.video.manager.dtos.NewsDto;
import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.services.NewsService;

@RestController
@RequestMapping("/vm2/news")
public class NewsController {

    @Autowired
    private NewsService service;

    @PostMapping
    public Response<NewsDto> create(@RequestBody NewsDto dto) {
        return new Response<>(true, 0, null, service.create(dto));
    }

    @GetMapping("/{id}")
    public Response<NewsDto> get(@PathVariable UUID id) {
        return new Response<>(true, 0, null, service.getById(id));
    }

    @GetMapping
    public Response<List<NewsDto>> getAll() {
        return new Response<>(true, 0, null, service.getAll());
    }

    @PutMapping("/{id}")
    public Response<NewsDto> update(@PathVariable UUID id,
                                    @RequestBody NewsDto dto) {
        return new Response<>(true, 0, null, service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public Response<String> delete(@PathVariable UUID id) {
        service.delete(id);
        return new Response<>(true, 0, null, "News deleted successfully");
    }

    @GetMapping("/by-video-details/{videoDetailsId}")
    public Response<List<NewsDto>> getByVideoDetailsId(
            @PathVariable UUID videoDetailsId) {

        return new Response<>(true, 0, null,
                service.getByVideoDetailsId(videoDetailsId));
    }
    
    @PostMapping(value = "/upload-csv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response<String> uploadCsv(@ModelAttribute NewsCsvDto csvDto) {
        service.uploadCsv(csvDto);
        return new Response<>(true, 0, null,
                "Csv uploaded successfully");
    }
}