package com.thirdeye3_2.video.manager.services;

import java.util.List;
import java.util.UUID;

import com.thirdeye3_2.video.manager.dtos.AdvertisementDto;

public interface AdvertisementService {

    AdvertisementDto createAdvertisement(AdvertisementDto dto);

    AdvertisementDto updateAdvertisement(UUID id, AdvertisementDto dto);

    AdvertisementDto getAdvertisement(UUID id);

    List<AdvertisementDto> getAllAdvertisements();

    List<AdvertisementDto> getActiveAdvertisements();

    AdvertisementDto activateAdvertisement(UUID id);

    AdvertisementDto deactivateAdvertisement(UUID id);

    void deleteAdvertisement(UUID id);

}