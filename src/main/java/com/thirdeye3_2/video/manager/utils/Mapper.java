package com.thirdeye3_2.video.manager.utils;

import java.time.LocalDateTime;
import java.util.UUID;

import com.thirdeye3_2.video.manager.dtos.AudioGenerateDto;
import com.thirdeye3_2.video.manager.dtos.ContentVideoDto;
import com.thirdeye3_2.video.manager.dtos.CurrentVideoDto;
import com.thirdeye3_2.video.manager.dtos.HeaderDto;
import com.thirdeye3_2.video.manager.dtos.IntroVideoDto;
import com.thirdeye3_2.video.manager.dtos.NewsDto;
import com.thirdeye3_2.video.manager.dtos.NewsImageDto;
import com.thirdeye3_2.video.manager.dtos.NewsTextSoundDto;
import com.thirdeye3_2.video.manager.dtos.OutroVideoDto;
import com.thirdeye3_2.video.manager.dtos.StockDto;
import com.thirdeye3_2.video.manager.dtos.StockGroupDto;
import com.thirdeye3_2.video.manager.dtos.StockRaceDto;
import com.thirdeye3_2.video.manager.dtos.VideoDetailsDto;
import com.thirdeye3_2.video.manager.dtos.VideoDto;
import com.thirdeye3_2.video.manager.dtos.VideoSettingDto;
import com.thirdeye3_2.video.manager.entities.AudioGenerate;
import com.thirdeye3_2.video.manager.entities.ContentVideo;
import com.thirdeye3_2.video.manager.entities.CurrentVideo;
import com.thirdeye3_2.video.manager.entities.Header;
import com.thirdeye3_2.video.manager.entities.IntroVideo;
import com.thirdeye3_2.video.manager.entities.News;
import com.thirdeye3_2.video.manager.entities.NewsImage;
import com.thirdeye3_2.video.manager.entities.NewsTextSound;
import com.thirdeye3_2.video.manager.entities.OutroVideo;
import com.thirdeye3_2.video.manager.entities.Stock;
import com.thirdeye3_2.video.manager.entities.StockGroup;
import com.thirdeye3_2.video.manager.entities.StockRace;
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
                .isAudio(dto.getIsAudio())
                .audioMultiMediaKey(dto.getAudioMultiMediaKey())
                .audioVolumne(dto.getAudioVolumne())
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
                .isAudio(entity.getIsAudio())
                .audioMultiMediaKey(entity.getAudioMultiMediaKey())
                .audioVolumne(entity.getAudioVolumne())
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
                .audioMultiMediaKey(dto.getAudioMultiMediaKey())
                .audioVolumne(dto.getAudioVolumne())
                .lastlyUsed(dto.getLastlyUsed())
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
        dto.setIsAudio(entity.getIsAudio());
        dto.setAudioMultiMediaKey(entity.getAudioMultiMediaKey());
        dto.setAudioVolumne(entity.getAudioVolumne());
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
                .isAudio(dto.getIsAudio())
                .audioMultiMediaKey(dto.getAudioMultiMediaKey())
                .audioVolumne(dto.getAudioVolumne())
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
        dto.setIsAudio(entity.getIsAudio());
        dto.setAudioMultiMediaKey(entity.getAudioMultiMediaKey());
        dto.setAudioVolumne(entity.getAudioVolumne());
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
                .audioVolumne(dto.getAudioVolumne())
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
        dto.setAudioVolumne(entity.getAudioVolumne());
        dto.setLastlyUsed(entity.getLastlyUsed());

        return dto;
    }
    
    public static Header toEntity(HeaderDto dto) {
        return Header.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .active(dto.getActive())
                .backgroundColor(dto.getBackgroundColor())
                .accentColor(dto.getAccentColor())
                .badgeColor(dto.getBadgeColor())
                .textColor(dto.getTextColor())
                .isBadgePresent(dto.getIsBadgePresent())
                .badgeWidthPct(dto.getBadgeWidthPct())
                .badgePosition(dto.getBadgePosition())
                .accentHeightPct(dto.getAccentHeightPct())
                .textSize(dto.getTextSize())
                .logoSize(dto.getLogoSize())
                .animCycle(dto.getAnimCycle())
                .animEase(dto.getAnimEase())
                .animBuffer(dto.getAnimBuffer())
                .lastlyUsed(dto.getLastlyUsed())
                .build();
    }

    public static HeaderDto toDto(Header entity) {
        HeaderDto dto = new HeaderDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setActive(entity.getActive());
        dto.setBackgroundColor(entity.getBackgroundColor());
        dto.setAccentColor(entity.getAccentColor());
        dto.setBadgeColor(entity.getBadgeColor());
        dto.setTextColor(entity.getTextColor());
        dto.setIsBadgePresent(entity.getIsBadgePresent());
        dto.setBadgeWidthPct(entity.getBadgeWidthPct());
        dto.setBadgePosition(entity.getBadgePosition());
        dto.setAccentHeightPct(entity.getAccentHeightPct());
        dto.setTextSize(entity.getTextSize());
        dto.setLogoSize(entity.getLogoSize());
        dto.setAnimCycle(entity.getAnimCycle());
        dto.setAnimEase(entity.getAnimEase());
        dto.setAnimBuffer(entity.getAnimBuffer());
        dto.setLastlyUsed(entity.getLastlyUsed());

        return dto;
    }
    
    public static NewsImage toEntity(NewsImageDto dto) {
        return NewsImage.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .active(dto.getActive())
                .enterFrom(dto.getEnterFrom())
                .exitTo(dto.getExitTo())
                .transitionT(dto.getTransitionT())
                .marginScale(dto.getMarginScale())
                .animationMode(dto.getAnimationMode())
                .zoomIntensity(dto.getZoomIntensity())
                .isSpinEnabled(dto.getIsSpinEnabled())
                .spinSpeed(dto.getSpinSpeed())
                .lastlyUsed(dto.getLastlyUsed())
                .build();
    }

    public static NewsImageDto toDto(NewsImage entity) {
        NewsImageDto dto = new NewsImageDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setActive(entity.getActive());
        dto.setEnterFrom(entity.getEnterFrom());
        dto.setExitTo(entity.getExitTo());
        dto.setTransitionT(entity.getTransitionT());
        dto.setMarginScale(entity.getMarginScale());
        dto.setAnimationMode(entity.getAnimationMode());
        dto.setZoomIntensity(entity.getZoomIntensity());
        dto.setIsSpinEnabled(entity.getIsSpinEnabled());
        dto.setSpinSpeed(entity.getSpinSpeed());
        dto.setLastlyUsed(entity.getLastlyUsed());

        return dto;
    }
    
    public static NewsTextSound toEntity(NewsTextSoundDto dto) {
        return NewsTextSound.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .active(dto.getActive())
                .bgTheme(dto.getBgTheme())
                .accentColor(dto.getAccentColor())
                .textColor(dto.getTextColor())
                .boxAlpha(dto.getBoxAlpha())
                .boxPad(dto.getBoxPad())
                .dpi(dto.getDpi())
                .transitionT(dto.getTransitionT())
                .enterFrom(dto.getEnterFrom())
                .exitTo(dto.getExitTo())
                .focusY(dto.getFocusY())
                .gapMain(dto.getGapMain())
                .gapSub(dto.getGapSub())
                .sideScale(dto.getSideScale())
                .headerWrap(dto.getHeaderWrap())
                .contentWrap(dto.getContentWrap())
                .otherWrap(dto.getOtherWrap())
                .baseFontSize(dto.getBaseFontSize())
                .isAudio(dto.getIsAudio())
                .endSilenceTimeInSeconds(dto.getEndSilenceTimeInSeconds())
                .audioVolumne(dto.getAudioVolumne())
                .lastlyUsed(dto.getLastlyUsed())
                .build();
    }

    public static NewsTextSoundDto toDto(NewsTextSound entity) {
        NewsTextSoundDto dto = new NewsTextSoundDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setActive(entity.getActive());
        dto.setBgTheme(entity.getBgTheme());
        dto.setAccentColor(entity.getAccentColor());
        dto.setTextColor(entity.getTextColor());
        dto.setBoxAlpha(entity.getBoxAlpha());
        dto.setBoxPad(entity.getBoxPad());
        dto.setDpi(entity.getDpi());
        dto.setTransitionT(entity.getTransitionT());
        dto.setEnterFrom(entity.getEnterFrom());
        dto.setExitTo(entity.getExitTo());
        dto.setFocusY(entity.getFocusY());
        dto.setGapMain(entity.getGapMain());
        dto.setGapSub(entity.getGapSub());
        dto.setSideScale(entity.getSideScale());
        dto.setHeaderWrap(entity.getHeaderWrap());
        dto.setContentWrap(entity.getContentWrap());
        dto.setOtherWrap(entity.getOtherWrap());
        dto.setBaseFontSize(entity.getBaseFontSize());
        dto.setIsAudio(entity.getIsAudio());
        dto.setEndSilenceTimeInSeconds(entity.getEndSilenceTimeInSeconds());
        dto.setAudioVolumne(entity.getAudioVolumne());
        dto.setLastlyUsed(entity.getLastlyUsed());

        return dto;
    }
    
    public static StockRace toEntity(StockRaceDto dto) {
        return StockRace.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .active(dto.getActive())
                .accentColor(dto.getAccentColor())
                .textColor(dto.getTextColor())
                .bgBadgeColor(dto.getBgBadgeColor())
                .barAlpha(dto.getBarAlpha())
                .glowSize(dto.getGlowSize())
                .barColors(dto.getBarColors())
                .baseFontSize(dto.getBaseFontSize())
                .labelFontSize(dto.getLabelFontSize())
                .valueFontSize(dto.getValueFontSize())
                .clockFontSize(dto.getClockFontSize())
                .barHeight(dto.getBarHeight())
                .topN(dto.getTopN())
                .xLimitMultiplier(dto.getXLimitMultiplier())
                .lastlyUsed(dto.getLastlyUsed())
                .build();
    }

    public static StockRaceDto toDto(StockRace entity) {
        StockRaceDto dto = new StockRaceDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setActive(entity.getActive());
        dto.setAccentColor(entity.getAccentColor());
        dto.setTextColor(entity.getTextColor());
        dto.setBgBadgeColor(entity.getBgBadgeColor());
        dto.setBarAlpha(entity.getBarAlpha());
        dto.setGlowSize(entity.getGlowSize());
        dto.setBarColors(entity.getBarColors());
        dto.setBaseFontSize(entity.getBaseFontSize());
        dto.setLabelFontSize(entity.getLabelFontSize());
        dto.setValueFontSize(entity.getValueFontSize());
        dto.setClockFontSize(entity.getClockFontSize());
        dto.setBarHeight(entity.getBarHeight());
        dto.setTopN(entity.getTopN());
        dto.setXLimitMultiplier(entity.getXLimitMultiplier());
        dto.setLastlyUsed(entity.getLastlyUsed());

        return dto;
    }
}