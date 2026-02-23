package com.thirdeye3_2.video.manager.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "multimedia")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultiMedia {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID key;

    private String folder1;
    private String folder2;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    private String extension;
    private String multiMediaType;
    private Long size;

    private LocalDateTime lastUsed;
    private LocalDateTime timeOfUpload;

    private Boolean autoDelete;
}