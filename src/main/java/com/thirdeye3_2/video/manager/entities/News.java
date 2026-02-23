package com.thirdeye3_2.video.manager.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "news",
	   indexes = {
		             @Index(name = "idx_news_video_details_id", columnList = "video_details_id"),
		             @Index(name = "idx_news_cleanup", columnList = "created_time, auto_delete")
	             }
      )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "video_details_id", nullable = false)
    private UUID videoDetailsId;

    @Column(name = "header", nullable = false)
    private String header;

    @Lob
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "news_warning_color")
    private String newsWarningColor;

    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;

    @Column(name = "is_image_multimedia_key_uploaded", nullable = false)
    private Boolean isImageMultiMediaKeyUploaded = false;

    @Column(name = "image_multimedia_key")
    private UUID imageMultiMediaKey;

    @Column(name = "is_audio_multimedia_key_uploaded", nullable = false)
    private Boolean isAudioMultiMediaKeyUploaded = false;

    @Column(name = "audio_multimedia_key")
    private UUID audioMultiMediaKey;

    @Column(name = "auto_delete", nullable = false)
    private Boolean autoDelete;
}
