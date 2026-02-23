package com.thirdeye3_2.video.manager.services.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.thirdeye3_2.video.manager.exceptions.CorruptedMultiMediaException;
import com.thirdeye3_2.video.manager.exceptions.ResourceNotFoundException;
import com.thirdeye3_2.video.manager.repositories.MultiMediaRepository;
import com.thirdeye3_2.video.manager.dtos.MultiMediaResponseDto;
import com.thirdeye3_2.video.manager.dtos.MultiMediaUploadDto;
import com.thirdeye3_2.video.manager.entities.MultiMedia;
import com.thirdeye3_2.video.manager.enums.NewsMultiMediaType;
import com.thirdeye3_2.video.manager.enums.TableName;
import com.thirdeye3_2.video.manager.services.AudioGenerateService;
import com.thirdeye3_2.video.manager.services.MultiMediaService;
import com.thirdeye3_2.video.manager.services.PropertyService;
import com.thirdeye3_2.video.manager.services.VideoDetailsService;

import io.awspring.cloud.s3.S3Template;

@Service
public class MultiMediaServiceImpl implements MultiMediaService {
	
	private static final Logger log = LoggerFactory.getLogger(MultiMediaServiceImpl.class);
	
	@Autowired
    private S3Template s3Template;

    @Autowired
    private MultiMediaRepository multiMediaRepository;
    
    @Autowired
    private PropertyService propertyService;
    
    @Autowired
    private VideoDetailsService videoDetailsService;
    
    @Autowired
    private NewsServiceImpl newsServiceImpl;
    
    @Autowired
    private AudioGenerateService audioGenerateService;

    @Value("${thirdeye.bucket.name}")
    private String bucketName;
    
    @Value("${thirdeye.multimedia.url.starter}") 
    private String urlStarter;
    

    @Override
    @Cacheable(value = "multimedia", key = "#key")
    public byte[] downloadMultiMediaContent(UUID key) {

        log.info("Downloading file content | key={}", key);

        MultiMedia entity = multiMediaRepository.findById(key)
                .orElseThrow(() -> {
                    log.error("Multimedia not found | key={}", key);
                    return new ResourceNotFoundException("Multimedia not found");
                });

        String s3Key = entity.getKey().toString() + entity.getExtension();
        log.info("Downloading from S3 | bucket={} | s3Key={}", bucketName, s3Key);

        try {
            Resource s3Resource = s3Template.download(bucketName, s3Key);

            byte[] content = StreamUtils.copyToByteArray(s3Resource.getInputStream());

            entity.setLastUsed(LocalDateTime.now());
            multiMediaRepository.save(entity);

            log.info("Download successful | key={} | size={} bytes", key, content.length);

            return content;

        } catch (IOException e) {
            log.error("Error reading file stream | key={}", key, e);
            throw new ResourceNotFoundException("Failed to download file content");
        }
    }

	@Override
	public MultiMediaResponseDto getMultiMediaDetails(UUID key) {

	    log.info("Fetching multimedia details | key={}", key);

	    MultiMedia entity = multiMediaRepository.findById(key)
	            .orElseThrow(() -> new ResourceNotFoundException("Multimedia not found"));

	    entity.setLastUsed(LocalDateTime.now());
	    multiMediaRepository.save(entity);

	    String viewUrl = UriComponentsBuilder.fromHttpUrl(urlStarter)
	            .pathSegment("vm", "multimedia", "view", entity.getKey().toString())
	            .build()
	            .toUriString();

	    return MultiMediaResponseDto.builder()
	            .key(entity.getKey().toString())
	            .folder1(entity.getFolder1())
	            .folder2(entity.getFolder2())
	            .name(entity.getName()+entity.getExtension())
	            .description(entity.getDescription())
	            .url(viewUrl)
	            .size(entity.getSize())
	            .multiMediaType(entity.getMultiMediaType())
	            .timeOfUpload(entity.getTimeOfUpload())
	            .lastUsed(entity.getLastUsed())
	            .autoDelete(entity.getAutoDelete())
	            .build();
	}

	@Override
	public MultiMediaResponseDto uploadMultiMedia(MultiMediaUploadDto uploadDto) {

	    log.info("Multimedia upload request received | name={}", uploadDto.getName());

	    try {
	        MultipartFile file = uploadDto.getFile();

	        if (file == null || file.isEmpty()) {
	            throw new CorruptedMultiMediaException("File must not be empty");
	        }

	        String originalName = file.getOriginalFilename();
	        String extension = "";

	        if (originalName != null && originalName.contains(".")) {
	            extension = originalName.substring(originalName.lastIndexOf("."));
	        }

	        UUID uuidKey = UUID.randomUUID();
	        String s3Key = uuidKey + extension;
	        String contentType = file.getContentType();

	        s3Template.upload(bucketName, s3Key, file.getInputStream());

	        log.info("File uploaded to S3 | bucket={} | key={}", bucketName, s3Key);

	        MultiMedia entity = MultiMedia.builder()
	                .key(uuidKey)
	                .folder1(uploadDto.getFolder1())
	                .folder2(uploadDto.getFolder2())
	                .name(uploadDto.getName())
	                .description(uploadDto.getDescription())
	                .extension(extension)
	                .multiMediaType(contentType)
	                .size(file.getSize())
	                .timeOfUpload(LocalDateTime.now())
	                .lastUsed(LocalDateTime.now())
	                .autoDelete(uploadDto.getAutoDelete())
	                .build();

	        MultiMedia saved = multiMediaRepository.save(entity);

	        String viewUrl = UriComponentsBuilder.fromHttpUrl(urlStarter)
	                .pathSegment("vm", "multimedia", "view", saved.getKey().toString())
	                .build()
	                .toUriString();
	        
	        if(uploadDto.getTableName()!=null && uploadDto.getTableName().equals(TableName.NEWS))
	        {
	        	if(uploadDto.getNewsMultiMediaType()!=null && uploadDto.getNewsMultiMediaType().equals(NewsMultiMediaType.IMAGE))
	        	{
	        		newsServiceImpl.addImageMultiMediaKey(UUID.fromString(saved.getFolder1()), uuidKey);
	        	}
	        	else if(uploadDto.getNewsMultiMediaType()!=null && uploadDto.getNewsMultiMediaType().equals(NewsMultiMediaType.AUDIO))
	        	{
	        		newsServiceImpl.addAudioMultiMediaKey(UUID.fromString(saved.getFolder1()), uuidKey);
	        		audioGenerateService.updateIsAudioGenerated(UUID.fromString(saved.getFolder2()), uuidKey);
	        	}
	        }
	        else if(uploadDto.getTableName()!=null && uploadDto.getTableName().equals(TableName.VIDEODETAILS))
	        {
	        	videoDetailsService.updateBarGraphJsonMultiMediaKey(UUID.fromString(saved.getFolder1()), uuidKey);
	        }
	        else if(uploadDto.getTableName()!=null && uploadDto.getTableName().equals(TableName.AUDIOGENERATE))
	        {
	        	audioGenerateService.updateIsAudioGenerated(UUID.fromString(saved.getFolder2()), uuidKey); 
	        }
	        
	        return MultiMediaResponseDto.builder()
	                .key(saved.getKey().toString())
	                .folder1(saved.getFolder1())
	                .folder2(saved.getFolder2())
	                .name(saved.getName()+saved.getExtension())
	                .description(saved.getDescription())
	                .url(viewUrl)
	                .size(saved.getSize())
	                .multiMediaType(saved.getMultiMediaType())
	                .timeOfUpload(saved.getTimeOfUpload())
	                .lastUsed(saved.getLastUsed())
	                .autoDelete(saved.getAutoDelete())
	                .build();

	    } catch (IOException e) {
	        log.error("File upload failed due to IO error", e);
	        throw new CorruptedMultiMediaException("Failed to upload file");
	    }
	}
	
	@Override
	public void autoDeleteUnusedFiles() {
	    log.info("Starting auto-delete job...");
	    LocalDateTime threshold = LocalDateTime.now().minusDays(propertyService.getmaximumTimeForResourcesInDays() );
	    List<MultiMedia> filesToDelete =
	            multiMediaRepository.findByAutoDeleteTrueAndLastUsedBefore(threshold);
	    log.info("Found {} files eligible for auto-delete", filesToDelete.size());
	    for (MultiMedia file : filesToDelete) {
	        try {
	            s3Template.deleteObject(bucketName, file.getKey()+file.getExtension());
	            log.info("Deleted from S3 | key={}", file.getKey()+file.getExtension());
	            multiMediaRepository.delete(file);
	            log.info("Deleted from DB | id={}", file.getKey());
	        } catch (Exception e) {
	            log.error("Failed to delete file | id={} | error={}",
	                    file.getKey(), e.getMessage());
	        }
	    }

	    log.info("Auto-delete job completed.");
	}
	
	@Override
	public void deleteMultiMedia(UUID key) {

	    log.info("Manual delete request received | key={}", key);

	    MultiMedia entity = multiMediaRepository.findById(key)
	            .orElseThrow(() -> {
	                log.error("Multimedia not found | key={}", key);
	                return new ResourceNotFoundException("Multimedia not found");
	            });

	    String s3Key = entity.getKey().toString() + entity.getExtension();

	    try {
	        s3Template.deleteObject(bucketName, s3Key);
	        log.info("Deleted from S3 | bucket={} | key={}", bucketName, s3Key);

	        multiMediaRepository.delete(entity);
	        log.info("Deleted from DB | id={}", entity.getKey());

	    } catch (Exception e) {
	        log.error("Failed to delete multimedia | key={}", key, e);
	        throw new RuntimeException("Failed to delete multimedia");
	    }
	}

}
