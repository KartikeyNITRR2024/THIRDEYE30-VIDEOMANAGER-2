package com.thirdeye3_2.video.manager.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "current_video")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrentVideo {

	@Id
    @Column(name = "id")
    private Long id; 
    
	@Column(name = "video_id")
    private UUID videoId;
}
