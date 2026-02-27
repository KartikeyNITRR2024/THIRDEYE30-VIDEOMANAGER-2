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
@Table(name = "news_image")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsImage {

	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(length = 2000)
    private String description;
    
    private Boolean active;
    
    @Column(name = "enter_from")
    private String enterFrom;

    @Column(name = "exit_to")
    private String exitTo;

    @Column(name = "transition_t")
    private Double transitionT;

    @Column(name = "margin_scale")
    private Double marginScale;

    @Column(name = "animation_mode")
    private NewsImageAnimationType animationMode;

    @Column(name = "zoom_intensity")
    private Double zoomIntensity;

    @Column(name = "is_spin_enabled")
    private Boolean isSpinEnabled;

    @Column(name = "spin_speed")
    private Integer spinSpeed;
    
    @Column(name = "lastly_used") 
    private LocalDateTime lastlyUsed;
    
    
}
