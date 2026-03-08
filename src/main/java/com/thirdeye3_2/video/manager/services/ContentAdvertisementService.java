package com.thirdeye3_2.video.manager.services;

import java.util.List;
import java.util.UUID;

import com.thirdeye3_2.video.manager.dtos.ContentAdvertisementDto;

public interface ContentAdvertisementService {

    ContentAdvertisementDto createContentAdvertisement(ContentAdvertisementDto dto);

    ContentAdvertisementDto updateContentAdvertisement(UUID id, ContentAdvertisementDto dto);

    ContentAdvertisementDto getContentAdvertisement(UUID id);

    List<ContentAdvertisementDto> getAllContentAdvertisements();

    List<ContentAdvertisementDto> getActiveContentAdvertisements();

    ContentAdvertisementDto activateContentAdvertisement(UUID id);

    ContentAdvertisementDto deactivateContentAdvertisement(UUID id);

    void deleteContentAdvertisement(UUID id);

}
