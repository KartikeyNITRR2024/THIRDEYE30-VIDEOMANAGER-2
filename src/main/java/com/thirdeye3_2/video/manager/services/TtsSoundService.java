package com.thirdeye3_2.video.manager.services;

import com.thirdeye3_2.video.manager.dtos.TtsCsvUploadDto;
import com.thirdeye3_2.video.manager.dtos.TtsSoundDto;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public interface TtsSoundService {
    TtsSoundDto create(TtsSoundDto dto);
    TtsSoundDto update(UUID id, TtsSoundDto dto);
    void delete(UUID id);
    List<TtsSoundDto> getAll();
    List<TtsSoundDto> getAllActive();
    TtsSoundDto toggleStatus(UUID id, boolean active);
    TtsSoundDto getById(UUID id);
    void uploadCsv(TtsCsvUploadDto csvDto);
}
