package com.thirdeye3_2.video.manager.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.thirdeye3_2.video.manager.enums.GeneratorType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "content_generators")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentGenerator {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "generator_type", nullable = false, unique = true)
    private GeneratorType type;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "url", nullable = false, length = 2000)
    private String url;

    @Column(name = "is_running")
    private Boolean isRunning;

    @Column(name = "lastly_started")
    private LocalDateTime lastlyStarted;

    @Column(name = "lastly_completed")
    private LocalDateTime lastlyCompleted;
}