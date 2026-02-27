package com.thirdeye3_2.video.manager.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.thirdeye3_2.video.manager.enums.NewsImageAnimationType;

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
@Table(name = "news_text_sound")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsTextSound {
	
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(length = 2000)
    private String description;
    
    private Boolean active;
    
    @Column(name = "bg_theme")
    private String bgTheme; 

    @Column(name = "accent_color")
    private String accentColor;

    @Column(name = "text_color")
    private String textColor;

    @Column(name = "box_alpha")
    private Double boxAlpha;

    @Column(name = "box_pad")
    private Double boxPad;

    @Column(name = "dpi")
    private Integer dpi;

    @Column(name = "transition_t")
    private Double transitionT;

    @Column(name = "enter_from")
    private String enterFrom; 

    @Column(name = "exit_to")
    private String exitTo;

    @Column(name = "focus_y")
    private Double focusY;

    @Column(name = "gap_main")
    private Double gapMain;

    @Column(name = "gap_sub")
    private Double gapSub;

    @Column(name = "side_scale")
    private Double sideScale;

    @Column(name = "header_wrap")
    private Integer headerWrap;

    @Column(name = "content_wrap")
    private Integer contentWrap;

    @Column(name = "other_wrap")
    private Integer otherWrap;

    @Column(name = "base_fontsize")
    private Integer baseFontSize;
    
    @Column(name = "is_audio")
    private Boolean isAudio;
    
    @Column(name = "end_silence_time_in_second")
    private Double endSilenceTimeInSeconds;
    
    @Column(name = "audio_volumne")
    private Integer audioVolumne;
    
    @Column(name = "lastly_used") 
    private LocalDateTime lastlyUsed;

}
