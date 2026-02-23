package com.thirdeye3_2.video.manager.externalcontrollers;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.thirdeye3_2.video.manager.configs.FeignConfig;
import com.thirdeye3_2.video.manager.dtos.Response;

@FeignClient(
		name = "THIRDEYE30-PROPERTYMANAGER",
		configuration = FeignConfig.class
)
public interface PropertyManagerClient {
    @GetMapping("/pm/properties")
    Response<Map<String, Object>> getProperties();
}
