package com.thirdeye3_2.video.manager.services.impl;

import com.thirdeye3_2.video.manager.dtos.TtsCsvUploadDto;
import com.thirdeye3_2.video.manager.dtos.TtsSoundDto;
import com.thirdeye3_2.video.manager.entities.TtsSound;
import com.thirdeye3_2.video.manager.enums.Gender;
import com.thirdeye3_2.video.manager.utils.Mapper;
import com.thirdeye3_2.video.manager.repositories.TtsSoundRepository;
import com.thirdeye3_2.video.manager.services.TtsSoundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirdeye3_2.video.manager.exceptions.CorruptedMultiMediaException;
import com.thirdeye3_2.video.manager.exceptions.ResourceNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TtsSoundServiceImpl implements TtsSoundService {

    @Autowired
    private TtsSoundRepository repository;

    @Override
    public TtsSoundDto create(TtsSoundDto dto) {
        log.info("Creating new TtsSound with name: {}", dto.getName());
        TtsSound entity = Mapper.toEntity(dto);
        entity.setActive(true);
        entity.setLastlyUsed(LocalDateTime.now());
        TtsSound saved = repository.save(entity);
        log.debug("Saved TtsSound with ID: {}", saved.getId());
        return Mapper.toDto(saved);
    }

    @Override
    public TtsSoundDto update(UUID id, TtsSoundDto dto) {
        log.info("Updating TtsSound ID: {}", id);
        TtsSound entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TtsSound not found with id: " + id));
        
        entity.setName(dto.getName());
        entity.setGender(dto.getGender());
        entity.setVoicePersonalities(dto.getVoicePersonalities());
        
        TtsSound updated = repository.save(entity);
        log.debug("Successfully updated TtsSound ID: {}", id);
        return Mapper.toDto(updated);
    }

    @Override
    public void delete(UUID id) {
        log.warn("Deleting TtsSound ID: {}", id);
        repository.deleteById(id);
    }

    @Override
    public List<TtsSoundDto> getAll() {
        log.info("Fetching all TtsSounds ordered by last used (DESC)");
        return repository.findAllByOrderByLastlyUsedDesc().stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TtsSoundDto> getAllActive() {
        log.info("Fetching all active TtsSounds ordered by last used (DESC)");
        return repository.findByActiveTrueOrderByLastlyUsedDesc().stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TtsSoundDto toggleStatus(UUID id, boolean active) {
        log.info("Changing status of TtsSound ID: {} to active={}", id, active);
        TtsSound entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TtsSound not found"));
        
        entity.setActive(active);
        return Mapper.toDto(repository.save(entity));
    }
    
    @Override
    public TtsSoundDto getById(UUID id) {
        log.info("Fetching TtsSound by ID: {}", id);
        TtsSound entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sound not found with ID: " + id));
        return Mapper.toDto(entity);
    }

    @Override
    public void uploadCsv(TtsCsvUploadDto csvDto) {
    	MultipartFile file = csvDto.getFile();
        log.info("Starting CSV upload for file: {}", file.getOriginalFilename());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            List<TtsSound> soundsToSave = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split(",");
                if (data.length >= 3) {
                    TtsSound sound = TtsSound.builder()
                            .name(data[0].trim())
                            .gender(Gender.valueOf(data[1].trim().toUpperCase()))
                            .voicePersonalities(data[2].trim())
                            .active(false)
                            .lastlyUsed(LocalDateTime.now())
                            .build();
                    soundsToSave.add(sound);
                }
            }
            repository.saveAll(soundsToSave);
            log.info("Successfully imported {} sounds from CSV", soundsToSave.size());
        } catch (Exception e) {
            log.error("Error processing CSV file: {}", e.getMessage());
            throw new CorruptedMultiMediaException("Failed to parse CSV file: " + e.getMessage());
        }
    }
}
