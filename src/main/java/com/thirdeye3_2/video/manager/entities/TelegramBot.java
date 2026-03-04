package com.thirdeye3_2.video.manager.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.thirdeye3_2.video.manager.enums.BotType;

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
@Table(name = "telegram_bots")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class TelegramBot {

	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
    
    @Column(nullable = false)
    private String name;
    
    private Boolean active;
    
    @Column(name = "chat_id")
    private String chatId;
    
    @Column(name = "bot_token")
    private String botToken;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_bot", nullable = false)
    private BotType botType;
    
    @Column(name = "created_date_time", nullable = false)
    private LocalDateTime createdDateTime;
    
}
