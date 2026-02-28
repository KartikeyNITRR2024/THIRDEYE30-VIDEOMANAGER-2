package com.thirdeye3_2.video.manager.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stock_race")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockRace {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(length = 2000)
    private String description;
    
    private Boolean active;

    @Column(name = "accent_color")
    private String accentColor;

    @Column(name = "text_color")
    private String textColor;

    @Column(name = "bg_badge_color")
    private String bgBadgeColor;

    @Column(name = "bar_alpha")
    private Double barAlpha;

    @Column(name = "glow_size")
    private Integer glowSize;

    @ElementCollection
    @CollectionTable(name = "stock_race_colors", joinColumns = @JoinColumn(name = "stock_race_id"))
    @Column(name = "color_hex")
    private List<String> barColors;

    @Column(name = "base_font_size")
    private Integer baseFontSize;

    @Column(name = "label_font_size")
    private Integer labelFontSize;

    @Column(name = "value_font_size")
    private Integer valueFontSize;

    @Column(name = "clock_font_size")
    private Integer clockFontSize;

    @Column(name = "bar_height")
    private Double barHeight;

    @Column(name = "top_n")
    private Integer topN;

    @Column(name = "x_limit_multiplier")
    private Double xLimitMultiplier;
    
    @Column(name = "lastly_used") 
    private LocalDateTime lastlyUsed;
}