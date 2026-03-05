package com.thirdeye3_2.video.manager.repositories;

import com.thirdeye3_2.video.manager.entities.TtsSound;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface TtsSoundRepository extends JpaRepository<TtsSound, UUID> {
    List<TtsSound> findByActiveTrue();
    List<TtsSound> findByActiveTrueOrderByLastlyUsedDesc();
    List<TtsSound> findAllByOrderByLastlyUsedDesc();
}
