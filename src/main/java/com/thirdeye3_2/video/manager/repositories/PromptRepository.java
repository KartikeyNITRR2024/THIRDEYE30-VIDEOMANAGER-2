package com.thirdeye3_2.video.manager.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thirdeye3_2.video.manager.entities.Prompt;
import com.thirdeye3_2.video.manager.enums.PromptType;

@Repository
public interface PromptRepository extends JpaRepository<Prompt, UUID> {

    List<Prompt> findAllByOrderByLastlyUsedDesc();

    List<Prompt> findByTypeOfVideoOrderByLastlyUsedDesc(PromptType type);

}
