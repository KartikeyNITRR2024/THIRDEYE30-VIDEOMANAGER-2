package com.thirdeye3_2.video.manager.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PropertyService {

    void fetchProperties();

    Long getmaximumTimeForResourcesInDays();

	Long getsendLogsAndFilesToTelegram();

	List<Double> getVaryFieldsInteger();

	List<Double> getVaryFieldsDouble();

	List<Double> getVaryFieldsColor();
}
