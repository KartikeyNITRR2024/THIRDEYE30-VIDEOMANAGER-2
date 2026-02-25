package com.thirdeye3_2.video.manager.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.thirdeye3_2.video.manager.enums.BarRaceType;
import com.thirdeye3_2.video.manager.enums.HeaderType;
import com.thirdeye3_2.video.manager.enums.NewsImageType;
import com.thirdeye3_2.video.manager.enums.NewsTextType;

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
@Table(name = "content_videos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ContentVideo {
	
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(length = 2000)
    private String description;
    
    private Boolean active;
    
    @Column(name = "is_background_image")
    private Boolean isBackgroundImage;
    
    @Column(name = "background_image")
    private UUID backgroundImage;

    @Column(name = "background_color")
    private String backgroundColor;

    @Column(name = "background_opacity")
    private Double backgroundOpacity;
    
    @Column(name = "is_header")
    private Boolean isHeaderPresent;

    @Column(name = "header_type")
    private HeaderType headerType;
    
    @Column(name = "header_height_in_percent")
    private Double headerHeightInPercent;
    
    @Column(name = "header_starting_position")
    private Integer headerStartingPosition;
    
    @Column(name = "is_bar_race")
    private Boolean isBarRace;

    @Column(name = "bar_race_type")
    private BarRaceType barRaceType;
    
    @Column(name = "bar_race_height_in_percent")
    private Double barRaceHeightInPercent;
    
    @Column(name = "bar_race_starting_position")
    private Integer barRaceStartingPosition;
    
    @Column(name = "is_news_image")
    private Boolean isNewsImage;

    @Column(name = "news_image_type")
    private NewsImageType newsImageType;
    
    @Column(name = "news_image_height_in_percent")
    private Double newsImageHeightInPercent;
    
    @Column(name = "news_image_starting_position")
    private Integer newsImageStartingPosition;
    
    @Column(name = "is_news_text")
    private Boolean isNewsText;

    @Column(name = "news_text_type")
    private NewsTextType newsTextType;
    
    @Column(name = "news_text_height_in_percent")
    private Double newsTexteHeightInPercent;
    
    @Column(name = "news_text_starting_position")
    private Integer newsTextStartingPosition;
    
    @Column(name = "is_audio")
    private Boolean isAudio;
    
    @Column(name = "audio_multimedia_key")
    private UUID audioMultiMediaKey;
    
    @Column(name = "lastly_used") 
    private LocalDateTime lastlyUsed;
    
    
}
