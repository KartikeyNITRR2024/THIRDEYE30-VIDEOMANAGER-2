package com.thirdeye3_2.video.manager.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.thirdeye3_2.video.manager.enums.TableName;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
	    name = "audio_generate",
	    indexes = {
	        @Index(name = "idx_table_foreignkey", columnList = "table_name, foreign_key"),
	        @Index(name = "idx_audio_cleanup", columnList = "created_time, auto_delete")
	    }
	)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AudioGenerate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "table_name", nullable = false)
    private TableName tableName;

    @Column(name = "foreign_key")
    private UUID foreignKey;

    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;

    @Column(name = "audio_generated_time")
    private LocalDateTime audioGeneratedTime;

    @Column(name = "is_audio_generated", nullable = false)
    private Boolean isAudioGenerated = false;
    
    @Column(name = "audio_multimedia_key")
    private UUID audioMultiMediaKey;

    @Column(name = "auto_delete", nullable = false)
    private Boolean autoDelete;
    
    @Lob
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
}
