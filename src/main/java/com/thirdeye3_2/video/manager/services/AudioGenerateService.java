package com.thirdeye3_2.video.manager.services;

import java.util.List;
import java.util.UUID;

import com.thirdeye3_2.video.manager.dtos.AudioGenerateDto;
import com.thirdeye3_2.video.manager.enums.TableName;

public interface AudioGenerateService {

    AudioGenerateDto create(AudioGenerateDto dto);

    AudioGenerateDto getById(UUID id);

    List<AudioGenerateDto> getAll();

    AudioGenerateDto update(UUID id, AudioGenerateDto dto);

    void delete(UUID id);

    List<AudioGenerateDto> getAllNotGenerated();
    
    AudioGenerateDto getByTableAndForeignKey(TableName tableName, UUID foreignKey);

	void autoDeleteUnusedAudio();

	AudioGenerateDto updateIsAudioGenerated(UUID id, UUID key);
}
