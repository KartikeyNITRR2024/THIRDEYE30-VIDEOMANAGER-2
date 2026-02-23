package com.thirdeye3_2.video.manager.utils;

import java.time.LocalDateTime;
import java.util.UUID;

import com.thirdeye3_2.video.manager.dtos.AudioGenerateDto;
import com.thirdeye3_2.video.manager.dtos.CurrentVideoDto;
import com.thirdeye3_2.video.manager.dtos.NewsDto;
import com.thirdeye3_2.video.manager.dtos.StockDto;
import com.thirdeye3_2.video.manager.dtos.StockGroupDto;
import com.thirdeye3_2.video.manager.dtos.VideoDetailsDto;
import com.thirdeye3_2.video.manager.dtos.VideoDto;
import com.thirdeye3_2.video.manager.entities.AudioGenerate;
import com.thirdeye3_2.video.manager.entities.CurrentVideo;
import com.thirdeye3_2.video.manager.entities.News;
import com.thirdeye3_2.video.manager.entities.Stock;
import com.thirdeye3_2.video.manager.entities.StockGroup;
import com.thirdeye3_2.video.manager.entities.Video;
import com.thirdeye3_2.video.manager.entities.VideoDetails;

public class Mapper {

    public static Video toEntity(VideoDto dto) {
        return Video.builder()
                .id(dto.getId() != null ? dto.getId() : UUID.randomUUID())
                .name(dto.getName())
                .description(dto.getDescription())
                .dateOfUpload(dto.getDateOfUpload())
                .typeOfVideo(dto.getTypeOfVideo())
                .stockGroup(dto.getStockGroup())
                .createdDateTime(
                        dto.getCreatedDateTime() != null 
                        ? dto.getCreatedDateTime() 
                        : LocalDateTime.now())
                .adsPresent(dto.getAdsPresent())
                .adsId(dto.getAdsId())
                .isCompleted(dto.getIsCompleted())
                .build();
    }

    public static VideoDto toDto(Video video) {
        VideoDto dto = new VideoDto();
        dto.setId(video.getId());
        dto.setName(video.getName());
        dto.setDescription(video.getDescription());
        dto.setDateOfUpload(video.getDateOfUpload());
        dto.setTypeOfVideo(video.getTypeOfVideo());
        dto.setStockGroup(video.getStockGroup());
        dto.setCreatedDateTime(video.getCreatedDateTime());
        dto.setAdsPresent(video.getAdsPresent());
        dto.setAdsId(video.getAdsId());
        dto.setIsCompleted(video.getIsCompleted());
        return dto;
    }
    
    public static VideoDetails toEntity(VideoDetailsDto dto) {
        return VideoDetails.builder()
                .id(dto.getId() != null ? dto.getId() : UUID.randomUUID())
                .videoId(dto.getVideoId())
                .introHeader(dto.getIntroHeader())
                .introSubHeader(dto.getIntroSubHeader())
                .header(dto.getHeader())
                .outroHeader(dto.getOutroHeader())
                .outroSubHeader(dto.getOutroSubHeader())
                .isbarGraphJsonMultiMediaKeyUploaded(dto.getIsbarGraphJsonMultiMediaKeyUploaded())
                .barGraphJsonMultiMediaKey(dto.getBarGraphJsonMultiMediaKey())
                .build();
    }

    public static VideoDetailsDto toDto(VideoDetails entity) {
        VideoDetailsDto dto = new VideoDetailsDto();
        dto.setId(entity.getId());
        dto.setVideoId(entity.getVideoId());
        dto.setIntroHeader(entity.getIntroHeader());
        dto.setIntroSubHeader(entity.getIntroSubHeader());
        dto.setHeader(entity.getHeader());
        dto.setOutroHeader(entity.getOutroHeader());
        dto.setOutroSubHeader(entity.getOutroSubHeader());
        dto.setIsbarGraphJsonMultiMediaKeyUploaded(entity.getIsbarGraphJsonMultiMediaKeyUploaded());
        dto.setBarGraphJsonMultiMediaKey(entity.getBarGraphJsonMultiMediaKey());
        return dto;
    }
    
    public static CurrentVideo toEntity(CurrentVideoDto dto) {
        return CurrentVideo.builder()
                .videoId(dto.getVideoId())
                .build();
    }

    public static CurrentVideoDto toDto(CurrentVideo entity) {
    	CurrentVideoDto dto = new CurrentVideoDto();
        dto.setVideoId(entity.getVideoId());
        return dto;
    }
    
    public static AudioGenerate toEntity(AudioGenerateDto dto) {
        return AudioGenerate.builder()
                .id(dto.getId())
                .tableName(dto.getTableName())
                .foreignKey(dto.getForeignKey())
                .createdTime(dto.getCreatedTime())
                .audioGeneratedTime(dto.getAudioGeneratedTime())
                .isAudioGenerated(dto.getIsAudioGenerated())
                .audioMultiMediaKey(dto.getAudioMultiMediaKey())
                .autoDelete(dto.getAutoDelete())
                .content(dto.getContent())
                .build();
    }

    public static AudioGenerateDto toDto(AudioGenerate entity) {
        return AudioGenerateDto.builder()
                .id(entity.getId())
                .tableName(entity.getTableName())
                .foreignKey(entity.getForeignKey())
                .createdTime(entity.getCreatedTime())
                .audioGeneratedTime(entity.getAudioGeneratedTime())
                .isAudioGenerated(entity.getIsAudioGenerated())
                .audioMultiMediaKey(entity.getAudioMultiMediaKey())
                .autoDelete(entity.getAutoDelete())
                .content(entity.getContent())
                .build();
    }
    
    public static News toEntity(NewsDto dto) {
        return News.builder()
                .id(dto.getId())
                .videoDetailsId(dto.getVideoDetailsId())
                .header(dto.getHeader())
                .content(dto.getContent())
                .newsWarningColor(dto.getNewsWarningColor())
                .createdTime(dto.getCreatedTime())
                .isImageMultiMediaKeyUploaded(dto.getIsImageMultiMediaKeyUploaded())
                .imageMultiMediaKey(dto.getImageMultiMediaKey())
                .isAudioMultiMediaKeyUploaded(dto.getIsAudioMultiMediaKeyUploaded())
                .audioMultiMediaKey(dto.getAudioMultiMediaKey())
                .autoDelete(dto.getAutoDelete())
                .build();
    }

    public static NewsDto toDto(News entity, String audioContent) {
        return NewsDto.builder()
                .id(entity.getId())
                .videoDetailsId(entity.getVideoDetailsId())
                .header(entity.getHeader())
                .content(entity.getContent())
                .audioContent(audioContent)
                .newsWarningColor(entity.getNewsWarningColor())
                .createdTime(entity.getCreatedTime())
                .isImageMultiMediaKeyUploaded(entity.getIsImageMultiMediaKeyUploaded())
                .imageMultiMediaKey(entity.getImageMultiMediaKey())
                .isAudioMultiMediaKeyUploaded(entity.getIsAudioMultiMediaKeyUploaded())
                .audioMultiMediaKey(entity.getAudioMultiMediaKey())
                .autoDelete(entity.getAutoDelete())
                .build();
    }
    
    public static StockGroup toEntity(StockGroupDto dto) {
        return StockGroup.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .createdDate(dto.getCreatedDate())
                .lastUsed(dto.getLastUsed())
                .active(dto.getActive())
                .build();
    }

    public static StockGroupDto toDto(StockGroup entity) {
        return StockGroupDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdDate(entity.getCreatedDate())
                .lastUsed(entity.getLastUsed())
                .active(entity.getActive())
                .stocks(
                        entity.getListOfStock()
                                .stream()
                                .map(Mapper::toDto)
                                .toList()
                 )
                .build();
    }
    
    public static Stock toEntity(StockDto dto, StockGroup group) {
        return Stock.builder()
                .id(dto.getId())
                .name(dto.getName())
                .marketCode(dto.getMarketCode())
                .stockName(dto.getStockName())
                .active(dto.getActive())
                .group(group)
                .build();
    }

    public static StockDto toDto(Stock entity) {
        return StockDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .marketCode(entity.getMarketCode())
                .stockName(entity.getStockName())
                .active(entity.getActive())
                .groupId(entity.getGroup().getId())
                .build();
    }
}