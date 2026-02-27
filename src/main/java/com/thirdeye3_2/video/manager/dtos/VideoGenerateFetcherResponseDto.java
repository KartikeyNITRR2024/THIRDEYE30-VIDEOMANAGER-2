package com.thirdeye3_2.video.manager.dtos;


import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VideoGenerateFetcherResponseDto {
    private VideoDto videoDto;
    private VideoDetailsDto videoDetailsDto;
    private VideoSettingDto videoSettingDto;
    private IntroVideoDto introVideoDto;
    private ContentVideoDto contentVideoDto;
    private OutroVideoDto outroVideoDto;
    private List<NewsDto> newsDtos;
    private Map<String, String> stockCodeToNameMap;
    private HeaderDto headerDto;
    private LocalDateTime currentTime;
}
