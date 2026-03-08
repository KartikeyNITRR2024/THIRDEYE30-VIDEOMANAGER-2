package com.thirdeye3_2.video.manager.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "advertisements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Advertisement {
	
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(length = 2000)
    private String description;

    @Builder.Default
    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Column(name = "is_intro_advertisment_present", nullable = false)
    private Boolean isIntroAdvertismentPresent;
    
    @Column(name = "intro_advertisment_multimedia_key")
    private UUID introAdvertismentMultimediaKey;
    
    @Column(name = "intro_advertisment_size")
    private Integer introAdvertismentSize;

    @Column(name = "is_badge_advertisment_present", nullable = false)
    private Boolean isBadgeAdvertismentPresent;
    
    @Column(name = "badge_advertisment_multimedia_key")
    private UUID badgeAdvertismentMultimediaKey;
    
    @Column(name = "badge_advertisment_size")
    private Integer badgeAdvertismentSize;
    
    @Column(name = "badge_advertisment_background_color")
    private String badgeAdvertismentBackgroundColor;
    
    @Column(name = "badge_advertisment_background_width_percent")
    private String badgeAdvertismentBackgroundWidthPercent;

    @Builder.Default
    @Column(name = "is_content_advertisment_present", nullable = false)
    private Boolean isContentAdvertismentPresent = false;
    
    @Builder.Default
    @OneToMany(mappedBy = "advertisement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContentAdvertisement> contentAdvertisements = new ArrayList<>();
    
    @Column(name = "created_time") 
    private LocalDateTime createdTime;

    public void addContentAdvertisement(ContentAdvertisement contentAd) {
        contentAdvertisements.add(contentAd);
        contentAd.setAdvertisement(this);
        this.isContentAdvertismentPresent = true;
    }
}