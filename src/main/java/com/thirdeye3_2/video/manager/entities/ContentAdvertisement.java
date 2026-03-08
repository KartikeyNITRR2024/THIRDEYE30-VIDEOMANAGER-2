package com.thirdeye3_2.video.manager.entities;

import java.time.LocalDateTime;
import java.util.UUID;
import com.thirdeye3_2.video.manager.enums.ContentAdvertisementPosition;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "content_advertisements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentAdvertisement {
	
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
    
    @Column(name = "starting_second", nullable = false)
    private Integer startingSecond;
    
    @Column(name = "ending_second", nullable = false)
    private Integer endingSecond;
    
    @Column(name = "content_multimedia_key", nullable = false)
    private UUID contentMultimediaKey;
    
    @Column(name = "height", nullable = false)
    private Integer height;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "content_advertisement_position", nullable = false)
    private ContentAdvertisementPosition contentAdvertisementPosition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_id", nullable = false)
    private Advertisement advertisement;
    
    @Column(name = "created_time") 
    private LocalDateTime createdTime;
}