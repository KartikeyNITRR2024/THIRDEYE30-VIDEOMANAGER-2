package com.thirdeye3_2.video.manager.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.thirdeye3_2.video.manager.enums.Interval;
import com.thirdeye3_2.video.manager.enums.Language;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "video_details")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(name = "video_id", nullable = false, unique = true)
    private UUID videoId;

    @Column(name = "intro_header", length = 500)
    private String introHeader;

    @Column(name = "intro_sub_header", length = 1000)
    private String introSubHeader;

    @Column(length = 2000)
    private String header;

    @Column(name = "outro_header", length = 500)
    private String outroHeader;

    @Column(name = "outro_sub_header", length = 1000)
    private String outroSubHeader;
    
    @Column(name = "is_bar_graph_json_multimedia_key_uploaded", nullable = false)
    private Boolean isbarGraphJsonMultiMediaKeyUploaded = false;
    
    @Column(name = "bar_graph_json_multimedia_key")
    private UUID barGraphJsonMultiMediaKey;
    
    @Column(name = "is_bar_graph_footer_present", nullable = false)
    private Boolean isBarGraphFooterPresent = false;
    
    @Column(name = "bar_graph_race_footer")
    private String barGraphRaceFooter;
    
    @Column(name = "is_intro_audio_string_present", nullable = false)
    private Boolean isIntroAudioStringPresent;
    
    @Column(name = "is_intro_audio_string_uploaded", nullable = false)
    private Boolean isIntroAudioStringUploaded = false;
    
    @Column(name = "intro_audio_multimedia_key")
    private UUID introAudioMultiMediaKey;
    
    @Column(name = "is_outro_audio_string_present", nullable = false)
    private Boolean isOutroAudioStringPresent;
    
    @Column(name = "is_outro_audio_string_uploaded", nullable = false)
    private Boolean isOutroAudioStringUploaded;
    
    @Column(name = "outro_audio_multimedia_key")
    private UUID outroAudioMultiMediaKey;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "language")
    private Language language;
    
    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;
}