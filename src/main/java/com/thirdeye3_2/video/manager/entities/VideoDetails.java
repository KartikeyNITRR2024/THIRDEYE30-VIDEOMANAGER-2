package com.thirdeye3_2.video.manager.entities;

import java.util.UUID;

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
}