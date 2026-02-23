package com.thirdeye3_2.video.manager.services.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.exceptions.PropertyFetchException;
import com.thirdeye3_2.video.manager.externalcontrollers.PropertyManagerClient;
import com.thirdeye3_2.video.manager.services.PropertyService;

import jakarta.persistence.Column;

@Service
public class PropertyServiceImpl implements PropertyService {
    private static final Logger logger = LoggerFactory.getLogger(PropertyServiceImpl.class);
    
    @Autowired
    private PropertyManagerClient propertyManager;

    private Map<String, Object> properties = null;
    private Long maximumTimeForResourcesInDays = null;

    @Override
    public void fetchProperties() {
        Response<Map<String, Object>> response = propertyManager.getProperties();
        if (response.isSuccess()) {
            properties = response.getResponse();
            maximumTimeForResourcesInDays = ((Number) properties.getOrDefault("MAXIMIUM_TIME_FOR_RESOURCES_IN_DAYS", 7L)).longValue();
            logger.info("Request {}, maximumTimeForResourcesInDays {}",
                    properties, maximumTimeForResourcesInDays);
        } else {
            properties = new HashMap<>();
            logger.error("Failed to fetch properties");
            throw new PropertyFetchException("Unable to fetch properties from Property Manager");
        }
    }

    @Override
    public Long getmaximumTimeForResourcesInDays() {
    	if(maximumTimeForResourcesInDays == null)
    	{
    		fetchProperties();
    	}
        return maximumTimeForResourcesInDays;
    }
}
