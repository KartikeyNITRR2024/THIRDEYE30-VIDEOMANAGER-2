package com.thirdeye3_2.video.manager.services;

import java.util.List;
import java.util.UUID;

import com.thirdeye3_2.video.manager.dtos.PromptDetailDto;
import com.thirdeye3_2.video.manager.dtos.PromptDto;
import com.thirdeye3_2.video.manager.enums.PromptType;

public interface PromptService {

    PromptDetailDto create(PromptDetailDto dto);

    PromptDetailDto update(UUID id, PromptDetailDto dto);

    void delete(UUID id);

    List<PromptDto> getAll(); 

    List<PromptDto> getByType(PromptType type);

    PromptDetailDto getPromptByIdAndUpdateLastUsed(UUID id);
}
