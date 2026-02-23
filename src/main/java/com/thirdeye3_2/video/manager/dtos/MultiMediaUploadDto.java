package com.thirdeye3_2.video.manager.dtos;

import org.springframework.web.multipart.MultipartFile;

import com.thirdeye3_2.video.manager.enums.NewsMultiMediaType;
import com.thirdeye3_2.video.manager.enums.TableName;

import lombok.Data;

@Data
public class MultiMediaUploadDto {
    private String folder1;
    private String folder2;
    private String name;
    private String description;
    private MultipartFile file;
    private Boolean autoDelete;
    private TableName tableName;
    private NewsMultiMediaType newsMultiMediaType;
}
