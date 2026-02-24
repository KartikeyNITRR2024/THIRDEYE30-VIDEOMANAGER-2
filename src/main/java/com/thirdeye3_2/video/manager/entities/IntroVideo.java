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
@Table(name = "intro_videos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class IntroVideo {
	
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
    private String backgroundImage;

    @Column(name = "background_color")
    private String backgroundColor;

    @Column(name = "background_opacity")
    private Double backgroundOpacity;
    
    @Column(name = "is_header")
    private Boolean isHeaderPresent;

    @Column(name = "header_font_type")
    private String headerFontType;

    @Column(name = "header_font_name")
    private String headerFontName;

    @Column(name = "header_size")
    private Integer headerSize;

    @Column(name = "header_color")
    private String headerColor;
    
    @Column(name = "is_subheader")
    private Boolean isSubHeaderPresent;

    @Column(name = "subheader_font_type")
    private String subHeaderFontType;

    @Column(name = "subheader_font_name")
    private String subHeaderFontName;

    @Column(name = "subheader_size")
    private Integer subHeaderSize;

    @Column(name = "subheader_color")
    private String subHeaderColor;
    
    @Column(name = "is_line")
    private Boolean isLinePresent;

    @Column(name = "line_color")
    private String lineColor;

    @Column(name = "line_width")
    private Integer lineWidth;
    
    @Column(name = "ad_image_height")
    private Integer adImageHeight;

    @Column(name = "ad_image_width")
    private Integer adImageWidth; 
    
    @Column(name = "lastly_used") 
    private LocalDateTime lastlyUsed;

}
