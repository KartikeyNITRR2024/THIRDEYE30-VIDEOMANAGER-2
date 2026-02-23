package com.thirdeye3_2.video.manager.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.thirdeye3_2.video.manager.services.AudioGenerateService;
import com.thirdeye3_2.video.manager.services.MultiMediaService;
import com.thirdeye3_2.video.manager.services.NewsService;
import com.thirdeye3_2.video.manager.services.PropertyService;
import com.thirdeye3_2.video.manager.dtos.Response;

import jakarta.annotation.PostConstruct;

@Component
public class Initiatier {

    private static final Logger logger = LoggerFactory.getLogger(Initiatier.class);
    
	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private MultiMediaService multiMediaService;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private AudioGenerateService audioGenerateService;


    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${thirdeye.priority}")
    private Integer priority;

    @Value("${thirdeye.api.key}")
    private String apiKey;

    @PostConstruct
    public void init() throws Exception {
        logger.info("Initializing Initiatier...");
        TimeUnit.SECONDS.sleep(priority * 3);
        propertyService.fetchProperties();
        logger.info("Initiatier initialized.");
    }

    public void refreshMemory() {
        logger.info("Going to refresh memory...");
        multiMediaService.autoDeleteUnusedFiles();
        newsService.autoDeleteUnusedNews();
        audioGenerateService.autoDeleteUnusedAudio();
        logger.info("Memory refreshed.");
    }
}
