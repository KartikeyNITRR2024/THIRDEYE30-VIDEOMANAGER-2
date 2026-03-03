package com.thirdeye3_2.video.manager.dtos;

import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class NewsCsvDto {
    private UUID videoDetailsId;
    private MultipartFile file;
}
