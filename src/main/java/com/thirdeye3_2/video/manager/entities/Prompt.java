package com.thirdeye3_2.video.manager.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.thirdeye3_2.video.manager.enums.PromptType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "prompt")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Prompt {
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(length = 2000, nullable = false)
    private String prompt;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_prompt", nullable = false)
    private PromptType typeOfVideo;
    
    @Column(name = "lastly_used") 
    private LocalDateTime lastlyUsed;
    
    @Column(name = "created_date_time", nullable = false)
    private LocalDateTime createdDateTime;
}
