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
@Table(name = "headers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Header {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(length = 2000)
    private String description;
    
    private Boolean active;
    
    @Column(name = "background_color")
    private String backgroundColor;
    
    @Column(name = "accent_color")
    private String accentColor;
    
    @Column(name = "badge_color")
    private String badgeColor;
    
    @Column(name = "text_color")
    private String textColor;
    
    @Column(name = "is_badge_present")
    private Boolean isBadgePresent;

    @Column(name = "badge_width_pct")
    private Double badgeWidthPct;

    @Column(name = "badge_position") // "left" or "right"
    private String badgePosition;

    @Column(name = "accent_height_pct")
    private Double accentHeightPct;

    @Column(name = "text_size")
    private Integer textSize;

    @Column(name = "logo_size")
    private Integer logoSize;

    @Column(name = "anim_cycle")
    private Double animCycle;

    @Column(name = "anim_ease")
    private Integer animEase;

    @Column(name = "anim_buffer")
    private Integer animBuffer;
    
    @Column(name = "lastly_used") 
    private LocalDateTime lastlyUsed;
}
