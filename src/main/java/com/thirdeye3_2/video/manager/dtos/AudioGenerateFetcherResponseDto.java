package com.thirdeye3_2.video.manager.dtos;

import java.util.UUID;
import java.util.List;

import com.thirdeye3_2.video.manager.enums.NewsMultiMediaType;
import com.thirdeye3_2.video.manager.enums.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AudioGenerateFetcherResponseDto {
    private List<AudioGenerateContent> contents;
}
