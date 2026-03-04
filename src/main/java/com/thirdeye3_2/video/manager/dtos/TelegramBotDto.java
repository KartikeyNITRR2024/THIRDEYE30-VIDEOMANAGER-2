package com.thirdeye3_2.video.manager.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.thirdeye3_2.video.manager.enums.BotType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TelegramBotDto {
	private UUID id;
    private String name;
    private Boolean active;
    private String chatId;
    private String botToken;
    private BotType botType;
    private LocalDateTime createdDateTime;
}
