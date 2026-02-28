package com.thirdeye3_2.video.manager.services;

import java.util.List;

import com.thirdeye3_2.video.manager.dtos.ContentGeneratorDto;
import com.thirdeye3_2.video.manager.enums.GeneratorType;

public interface ContentGeneratorService {
	ContentGeneratorDto updateStatus(GeneratorType type, boolean isRunning);
	ContentGeneratorDto toggleActive(GeneratorType type, boolean active);
	ContentGeneratorDto getByGeneratorType(GeneratorType type);
	List<ContentGeneratorDto> getAll();
}