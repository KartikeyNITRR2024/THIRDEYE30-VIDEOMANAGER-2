package com.thirdeye3_2.video.manager.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.thirdeye3_2.video.manager.enums.VideoType;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "videos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Video {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(length = 2000)
    private String description;

    @Column(name = "date_of_upload")
    private LocalDateTime dateOfUpload;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_video", nullable = false)
    private VideoType typeOfVideo;

    @Column(name = "stock_group")
    private UUID stockGroup;

    @Column(name = "created_date_time", nullable = false)
    private LocalDateTime createdDateTime;

    @Column(name = "ads_present")
    private Boolean adsPresent;

    @Column(name = "ads_id")
    private String adsId;

    @Column(name = "is_completed")
    private Boolean isCompleted;
}