package com.thirdeye3_2.video.manager.services;

import java.util.UUID;
import java.util.List;
import com.thirdeye3_2.video.manager.dtos.MultiMediaResponseDto;
import com.thirdeye3_2.video.manager.dtos.MultiMediaUploadDto;

public interface MultiMediaService {
	byte[] downloadMultiMediaContent(UUID key);
	MultiMediaResponseDto getMultiMediaDetails(UUID key);
	List<MultiMediaResponseDto> getAllMutliMediaDetails();
	List<MultiMediaResponseDto> getAllMutliMediaUploadToday();
	MultiMediaResponseDto uploadMultiMedia(MultiMediaUploadDto multiMediaUploadDto);
	void autoDeleteUnusedFiles();
	void deleteMultiMedia(UUID key);
}
