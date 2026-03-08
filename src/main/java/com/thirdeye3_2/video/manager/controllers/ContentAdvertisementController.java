package com.thirdeye3_2.video.manager.controllers;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thirdeye3_2.video.manager.dtos.ContentAdvertisementDto;
import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.services.ContentAdvertisementService;

@RestController
@RequestMapping("/vm2/content-advertisements")
public class ContentAdvertisementController {

    @Autowired
    private ContentAdvertisementService contentAdvertisementService;

    @PostMapping
    public Response<ContentAdvertisementDto> create(@RequestBody ContentAdvertisementDto dto) {

        ContentAdvertisementDto response =
                contentAdvertisementService.createContentAdvertisement(dto);

        return new Response<>(true, 0, null, response);
    }

    @GetMapping("/{id}")
    public Response<ContentAdvertisementDto> get(@PathVariable UUID id) {

        ContentAdvertisementDto response =
                contentAdvertisementService.getContentAdvertisement(id);

        return new Response<>(true, 0, null, response);
    }

    @GetMapping
    public Response<List<ContentAdvertisementDto>> getAll() {

        List<ContentAdvertisementDto> response =
                contentAdvertisementService.getAllContentAdvertisements();

        return new Response<>(true, 0, null, response);
    }

    @GetMapping("/active")
    public Response<List<ContentAdvertisementDto>> getActive() {

        List<ContentAdvertisementDto> response =
                contentAdvertisementService.getActiveContentAdvertisements();

        return new Response<>(true, 0, null, response);
    }

    @PutMapping("/{id}")
    public Response<ContentAdvertisementDto> update(
            @PathVariable UUID id,
            @RequestBody ContentAdvertisementDto dto) {

        ContentAdvertisementDto response =
                contentAdvertisementService.updateContentAdvertisement(id, dto);

        return new Response<>(true, 0, null, response);
    }

    @PatchMapping("/{id}/activate")
    public Response<ContentAdvertisementDto> activate(@PathVariable UUID id) {
        return new Response<>(true, 0, null, contentAdvertisementService.activateContentAdvertisement(id));
    }

    @PatchMapping("/{id}/deactivate")
    public Response<ContentAdvertisementDto> deactivate(@PathVariable UUID id) {
        return new Response<>(true, 0, null, contentAdvertisementService.deactivateContentAdvertisement(id));
    }

    @DeleteMapping("/{id}")
    public Response<String> delete(@PathVariable UUID id) {

        contentAdvertisementService.deleteContentAdvertisement(id);

        return new Response<>(true, 0, null, "Content Advertisement Deleted");
    }
}
