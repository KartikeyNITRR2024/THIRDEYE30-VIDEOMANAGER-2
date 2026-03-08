package com.thirdeye3_2.video.manager.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thirdeye3_2.video.manager.dtos.AdvertisementDto;
import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.services.AdvertisementService;

@RestController
@RequestMapping("/vm2/advertisements")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @PostMapping
    public Response<AdvertisementDto> create(@RequestBody AdvertisementDto dto) {

        AdvertisementDto response = advertisementService.createAdvertisement(dto);

        return new Response<>(true, 0, null, response);
    }

    @GetMapping("/{id}")
    public Response<AdvertisementDto> get(@PathVariable UUID id) {

        AdvertisementDto response = advertisementService.getAdvertisement(id);

        return new Response<>(true, 0, null, response);
    }

    @GetMapping
    public Response<List<AdvertisementDto>> getAll() {

        List<AdvertisementDto> response = advertisementService.getAllAdvertisements();

        return new Response<>(true, 0, null, response);
    }

    @GetMapping("/active")
    public Response<List<AdvertisementDto>> getActive() {

        List<AdvertisementDto> response = advertisementService.getActiveAdvertisements();

        return new Response<>(true, 0, null, response);
    }

    @PutMapping("/{id}")
    public Response<AdvertisementDto> update(@PathVariable UUID id,
                                             @RequestBody AdvertisementDto dto) {

        AdvertisementDto response = advertisementService.updateAdvertisement(id, dto);

        return new Response<>(true, 0, null, response);
    }

    @PatchMapping("/{id}/activate")
    public Response<AdvertisementDto> activate(@PathVariable UUID id) {
        return new Response<>(true, 0, null, advertisementService.activateAdvertisement(id));
    }

    @PatchMapping("/{id}/deactivate")
    public Response<AdvertisementDto> deactivate(@PathVariable UUID id) {
        return new Response<>(true, 0, null, advertisementService.deactivateAdvertisement(id));
    }

    @DeleteMapping("/{id}")
    public Response<String> delete(@PathVariable UUID id) {

        advertisementService.deleteAdvertisement(id);

        return new Response<>(true, 0, null, "Advertisement Deleted");
    }
}