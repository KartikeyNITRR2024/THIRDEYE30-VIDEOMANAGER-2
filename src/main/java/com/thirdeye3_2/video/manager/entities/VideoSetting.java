package com.thirdeye3_2.video.manager.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "video_setting")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoSetting {
	
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(length = 2000)
    private String description;
    
    private Boolean active;
    
    private Integer fps;
    
    @Column(name = "video_height")
    private Long height;
    
    @Column(name = "video_width")
    private Long width;

    @Column(name = "intro_present")
    private Boolean introPresent;

    @Column(name = "intro_time")
    private Integer introTime;

    @Column(name = "main_video_present")
    private Boolean mainVideoPresent;

    @Column(name = "main_video_time")
    private Integer mainVideoTime;

    @Column(name = "outro_present")
    private Boolean outroPresent;

    @Column(name = "outro_time")
    private Integer outroTime;
    
    @Column(name = "sequence")
    private String sequence;

    @Column(name = "sound_present")
    private Boolean soundPresent;
    
    @Column(name = "lastly_used")
    private LocalDateTime lastlyUsed;

}
