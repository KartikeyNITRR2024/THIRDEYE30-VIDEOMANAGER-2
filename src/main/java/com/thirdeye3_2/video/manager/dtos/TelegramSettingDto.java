package com.thirdeye3_2.video.manager.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.thirdeye3_2.video.manager.enums.BotType;
import com.thirdeye3_2.video.manager.enums.VideoType;

import lombok.Data;

@Data
public class TelegramSettingDto {
   Long sendLogsAndFilesToTelegram;
   Map<BotType, List<TelegramBotDto>> botMap;
}
