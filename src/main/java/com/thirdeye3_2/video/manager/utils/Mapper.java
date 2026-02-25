package com.thirdeye3_2.video.manager.utils;

import java.time.LocalDateTime;
import java.util.UUID;

import com.thirdeye3_2.video.manager.dtos.AudioGenerateDto;
import com.thirdeye3_2.video.manager.dtos.ContentVideoDto;
import com.thirdeye3_2.video.manager.dtos.CurrentVideoDto;
import com.thirdeye3_2.video.manager.dtos.IntroVideoDto;
import com.thirdeye3_2.video.manager.dtos.NewsDto;
import com.thirdeye3_2.video.manager.dtos.OutroVideoDto;
import com.thirdeye3_2.video.manager.dtos.StockDto;
import com.thirdeye3_2.video.manager.dtos.StockGroupDto;
import com.thirdeye3_2.video.manager.dtos.VideoDetailsDto;
import com.thirdeye3_2.video.manager.dtos.VideoDto;
import com.thirdeye3_2.video.manager.dtos.VideoSettingDto;
import com.thirdeye3_2.video.manager.entities.AudioGenerate;
import com.thirdeye3_2.video.manager.entities.ContentVideo;
import com.thirdeye3_2.video.manager.entities.CurrentVideo;
import com.thirdeye3_2.video.manager.entities.IntroVideo;
import com.thirdeye3_2.video.manager.entities.News;
import com.thirdeye3_2.video.manager.entities.OutroVideo;
import com.thirdeye3_2.video.manager.entities.Stock;
import com.thirdeye3_2.video.manager.entities.StockGroup;
import com.thirdeye3_2.video.manager.entities.Video;
import com.thirdeye3_2.video.manager.entities.VideoDetails;
import com.thirdeye3_2.video.manager.entities.VideoSetting;

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
    
    public static VideoSetting toEntity(VideoSettingDto dto) {
        return VideoSetting.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .active(dto.getActive())
                .fps(dto.getFps())
                .height(dto.getHeight())
                .width(dto.getWidth())
                .introPresent(dto.getIntroPresent())
                .introTime(dto.getIntroTime())
                .mainVideoPresent(dto.getMainVideoPresent())
                .mainVideoTime(dto.getMainVideoTime())
                .outroPresent(dto.getOutroPresent())
                .outroTime(dto.getOutroTime())
                .sequence(dto.getSequence())
                .soundPresent(dto.getSoundPresent())
                .lastlyUsed(dto.getLastlyUsed())
                .build();
    }

    public static VideoSettingDto toDto(VideoSetting entity) {
        return VideoSettingDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .active(entity.getActive())
                .fps(entity.getFps())
                .height(entity.getHeight())
                .width(entity.getWidth())
                .introPresent(entity.getIntroPresent())
                .introTime(entity.getIntroTime())
                .mainVideoPresent(entity.getMainVideoPresent())
                .mainVideoTime(entity.getMainVideoTime())
                .outroPresent(entity.getOutroPresent())
                .outroTime(entity.getOutroTime())
                .sequence(entity.getSequence())
                .soundPresent(entity.getSoundPresent())
                .lastlyUsed(entity.getLastlyUsed())
                .build();
    }
    
    public static IntroVideo toEntity(IntroVideoDto dto) {
        return IntroVideo.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .active(dto.getActive())
                .isBackgroundImage(dto.getIsBackgroundImage())
                .backgroundImage(dto.getBackgroundImage())
                .backgroundColor(dto.getBackgroundColor())
                .backgroundOpacity(dto.getBackgroundOpacity())
                .isHeaderPresent(dto.getIsHeaderPresent())
                .headerFontType(dto.getHeaderFontType())
                .headerFontName(dto.getHeaderFontName())
                .headerSize(dto.getHeaderSize())
                .headerColor(dto.getHeaderColor())
                .isSubHeaderPresent(dto.getIsSubHeaderPresent())
                .subHeaderFontType(dto.getSubHeaderFontType())
                .subHeaderFontName(dto.getSubHeaderFontName())
                .subHeaderSize(dto.getSubHeaderSize())
                .subHeaderColor(dto.getSubHeaderColor())
                .isLinePresent(dto.getIsLinePresent())
                .lineColor(dto.getLineColor())
                .lineWidth(dto.getLineWidth())
                .adImageHeight(dto.getAdImageHeight())
                .adImageWidth(dto.getAdImageWidth())
                .lastlyUsed(dto.getLastlyUsed())
                .build();
    }

    public static IntroVideoDto toDto(IntroVideo entity) {
        IntroVideoDto dto = new IntroVideoDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setActive(entity.getActive());
        dto.setIsBackgroundImage(entity.getIsBackgroundImage());
        dto.setBackgroundImage(entity.getBackgroundImage());
        dto.setBackgroundColor(entity.getBackgroundColor());
        dto.setBackgroundOpacity(entity.getBackgroundOpacity());
        dto.setIsHeaderPresent(entity.getIsHeaderPresent());
        dto.setHeaderFontType(entity.getHeaderFontType());
        dto.setHeaderFontName(entity.getHeaderFontName());
        dto.setHeaderSize(entity.getHeaderSize());
        dto.setHeaderColor(entity.getHeaderColor());
        dto.setIsSubHeaderPresent(entity.getIsSubHeaderPresent());
        dto.setSubHeaderFontType(entity.getSubHeaderFontType());
        dto.setSubHeaderFontName(entity.getSubHeaderFontName());
        dto.setSubHeaderSize(entity.getSubHeaderSize());
        dto.setSubHeaderColor(entity.getSubHeaderColor());
        dto.setIsLinePresent(entity.getIsLinePresent());
        dto.setLineColor(entity.getLineColor());
        dto.setLineWidth(entity.getLineWidth());
        dto.setAdImageHeight(entity.getAdImageHeight());
        dto.setAdImageWidth(entity.getAdImageWidth());
        dto.setLastlyUsed(entity.getLastlyUsed());
        return dto;
    }
    
    public static OutroVideo toEntity(OutroVideoDto dto) {
        return OutroVideo.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .active(dto.getActive())
                .isBackgroundImage(dto.getIsBackgroundImage())
                .backgroundImage(dto.getBackgroundImage())
                .backgroundColor(dto.getBackgroundColor())
                .backgroundOpacity(dto.getBackgroundOpacity())
                .isHeaderPresent(dto.getIsHeaderPresent())
                .headerFontType(dto.getHeaderFontType())
                .headerFontName(dto.getHeaderFontName())
                .headerSize(dto.getHeaderSize())
                .headerColor(dto.getHeaderColor())
                .isSubHeaderPresent(dto.getIsSubHeaderPresent())
                .subHeaderFontType(dto.getSubHeaderFontType())
                .subHeaderFontName(dto.getSubHeaderFontName())
                .subHeaderSize(dto.getSubHeaderSize())
                .subHeaderColor(dto.getSubHeaderColor())
                .lastlyUsed(dto.getLastlyUsed())
                .build();
    }

    public static OutroVideoDto toDto(OutroVideo entity) {
        OutroVideoDto dto = new OutroVideoDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setActive(entity.getActive());
        dto.setIsBackgroundImage(entity.getIsBackgroundImage());
        dto.setBackgroundImage(entity.getBackgroundImage());
        dto.setBackgroundColor(entity.getBackgroundColor());
        dto.setBackgroundOpacity(entity.getBackgroundOpacity());
        dto.setIsHeaderPresent(entity.getIsHeaderPresent());
        dto.setHeaderFontType(entity.getHeaderFontType());
        dto.setHeaderFontName(entity.getHeaderFontName());
        dto.setHeaderSize(entity.getHeaderSize());
        dto.setHeaderColor(entity.getHeaderColor());
        dto.setIsSubHeaderPresent(entity.getIsSubHeaderPresent());
        dto.setSubHeaderFontType(entity.getSubHeaderFontType());
        dto.setSubHeaderFontName(entity.getSubHeaderFontName());
        dto.setSubHeaderSize(entity.getSubHeaderSize());
        dto.setSubHeaderColor(entity.getSubHeaderColor());
        dto.setLastlyUsed(entity.getLastlyUsed());
        return dto;
    }
    
    public static ContentVideo toEntity(ContentVideoDto dto) {
        return ContentVideo.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .active(dto.getActive())
                .isBackgroundImage(dto.getIsBackgroundImage())
                .backgroundImage(dto.getBackgroundImage())
                .backgroundColor(dto.getBackgroundColor())
                .backgroundOpacity(dto.getBackgroundOpacity())
                .isHeaderPresent(dto.getIsHeaderPresent())
                .headerType(dto.getHeaderType())
                .headerHeightInPercent(dto.getHeaderHeightInPercent())
                .headerStartingPosition(dto.getHeaderStartingPosition())
                .isBarRace(dto.getIsBarRace())
                .barRaceType(dto.getBarRaceType())
                .barRaceHeightInPercent(dto.getBarRaceHeightInPercent())
                .barRaceStartingPosition(dto.getBarRaceStartingPosition())
                .isNewsImage(dto.getIsNewsImage())
                .newsImageType(dto.getNewsImageType())
                .newsImageHeightInPercent(dto.getNewsImageHeightInPercent())
                .newsImageStartingPosition(dto.getNewsImageStartingPosition())
                .isNewsText(dto.getIsNewsText())
                .newsTextType(dto.getNewsTextType())
                .newsTexteHeightInPercent(dto.getNewsTexteHeightInPercent())
                .newsTextStartingPosition(dto.getNewsTextStartingPosition())
                .isAudio(dto.getIsAudio())
                .audioMultiMediaKey(dto.getAudioMultiMediaKey())
                .lastlyUsed(dto.getLastlyUsed())
                .build();
    }

    public static ContentVideoDto toDto(ContentVideo entity) {
        ContentVideoDto dto = new ContentVideoDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setActive(entity.getActive());
        dto.setIsBackgroundImage(entity.getIsBackgroundImage());
        dto.setBackgroundImage(entity.getBackgroundImage());
        dto.setBackgroundColor(entity.getBackgroundColor());
        dto.setBackgroundOpacity(entity.getBackgroundOpacity());
        dto.setIsHeaderPresent(entity.getIsHeaderPresent());
        dto.setHeaderType(entity.getHeaderType());
        dto.setHeaderHeightInPercent(entity.getHeaderHeightInPercent());
        dto.setHeaderStartingPosition(entity.getHeaderStartingPosition());
        dto.setIsBarRace(entity.getIsBarRace());
        dto.setBarRaceType(entity.getBarRaceType());
        dto.setBarRaceHeightInPercent(entity.getBarRaceHeightInPercent());
        dto.setBarRaceStartingPosition(entity.getBarRaceStartingPosition());
        dto.setIsNewsImage(entity.getIsNewsImage());
        dto.setNewsImageType(entity.getNewsImageType());
        dto.setNewsImageHeightInPercent(entity.getNewsImageHeightInPercent());
        dto.setNewsImageStartingPosition(entity.getNewsImageStartingPosition());
        dto.setIsNewsText(entity.getIsNewsText());
        dto.setNewsTextType(entity.getNewsTextType());
        dto.setNewsTexteHeightInPercent(entity.getNewsTexteHeightInPercent());
        dto.setNewsTextStartingPosition(entity.getNewsTextStartingPosition());
        dto.setIsAudio(entity.getIsAudio());
        dto.setAudioMultiMediaKey(entity.getAudioMultiMediaKey());
        dto.setLastlyUsed(entity.getLastlyUsed());

        return dto;
    }
}