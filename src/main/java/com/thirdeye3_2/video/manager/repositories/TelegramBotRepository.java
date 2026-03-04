package com.thirdeye3_2.video.manager.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thirdeye3_2.video.manager.entities.TelegramBot;
import com.thirdeye3_2.video.manager.enums.BotType;

@Repository
public interface TelegramBotRepository extends JpaRepository<TelegramBot, UUID> {

    List<TelegramBot> findByBotTypeAndActive(BotType botType, Boolean active);
    List<TelegramBot> findByActive(Boolean active);

}
