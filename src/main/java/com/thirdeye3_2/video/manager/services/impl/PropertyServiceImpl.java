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
    private Long sendLogsAndFilesToTelegram = null;  // 0. No.  1. Only Logs. 2. Only Files  3. Both
    private Double varyMinPercentInteger = null;
    private Double varyMaxPercentInteger = null;
    private Double varyMinPercentDouble = null;
    private Double varyMaxPercentDouble = null;
    private Double varyMinPercentColor = null;
    private Double varyMaxPercentColor = null;

    @Override
    public void fetchProperties() {
        Response<Map<String, Object>> response = propertyManager.getProperties();
        if (response.isSuccess()) {
            properties = response.getResponse();
            varyMinPercentInteger = ((Number) properties.getOrDefault("VARY_MIN_PRECENT_INTEGER", -0.10d)).doubleValue();
            varyMaxPercentInteger = ((Number) properties.getOrDefault("VARY_MAX_PRECENT_INTEGER", 0.15d)).doubleValue();
            varyMinPercentDouble = ((Number) properties.getOrDefault("VARY_MIN_PRECENT_DOUBLE", -0.05d)).doubleValue();
            varyMaxPercentDouble = ((Number) properties.getOrDefault("VARY_MAX_PRECENT_DOUBLE", 0.10d)).doubleValue();
            varyMinPercentColor = ((Number) properties.getOrDefault("VARY_MIN_PRECENT_COLOR", -0.15d)).doubleValue();
            varyMaxPercentColor = ((Number) properties.getOrDefault("VARY_MAX_PRECENT_COLOR", 0.20d)).doubleValue();
            maximumTimeForResourcesInDays = ((Number) properties.getOrDefault("MAXIMIUM_TIME_FOR_RESOURCES_IN_DAYS", 7L)).longValue();
            sendLogsAndFilesToTelegram = ((Number) properties.getOrDefault("SEND_LONGS_AND_FILES_TO_TELEGRAM", 3L)).longValue();
            logger.info("Request {}, maximumTimeForResourcesInDays {}, varyMinPercent {}, varyMaxPercent {}",
                    properties, maximumTimeForResourcesInDays, varyMinPercentInteger, varyMinPercentInteger);
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
    
    @Override
    public Long getsendLogsAndFilesToTelegram() {
    	if(sendLogsAndFilesToTelegram == null)
    	{
    		fetchProperties();
    	}
        return sendLogsAndFilesToTelegram;
    }
    
    @Override
    public List<Double> getVaryFieldsInteger() {
    	if(varyMinPercentInteger == null || varyMaxPercentInteger == null)
    	{
    		fetchProperties();
    	}
        return List.of(varyMinPercentInteger, varyMaxPercentInteger);
    }
    
    @Override
    public List<Double> getVaryFieldsDouble() {
    	if(varyMinPercentDouble == null || varyMaxPercentDouble == null)
    	{
    		fetchProperties();
    	}
        return List.of(varyMinPercentDouble, varyMaxPercentDouble);
    }
    
    @Override
    public List<Double> getVaryFieldsColor() {
    	if(varyMinPercentColor == null || varyMaxPercentColor == null)
    	{
    		fetchProperties();
    	}
        return List.of(varyMinPercentColor, varyMaxPercentColor);
    }
}
