package com.thirdeye3_2.video.manager.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TtsCsvUploadDto {
    private MultipartFile file;
}