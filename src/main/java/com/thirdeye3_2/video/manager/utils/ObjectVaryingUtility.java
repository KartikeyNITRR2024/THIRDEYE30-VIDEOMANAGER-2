
package com.thirdeye3_2.video.manager.utils;

import java.lang.reflect.Field;
import java.awt.Color;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectVaryingUtility {
	
	private static final Logger logger = LoggerFactory.getLogger(ObjectVaryingUtility.class);
    private static final Random random = new Random();
    
    private double calculateFactor(double minPercent, double maxPercent) {
        double variation = minPercent + (maxPercent - minPercent) * random.nextDouble();
        return 1.0 + variation;
    }

    public <T> T varyFields(T obj, double minPercent, double maxPercent) {
        if (obj == null) return null;

        double variation = minPercent + (maxPercent - minPercent) * random.nextDouble();
        double factor = 1.0 + variation;

        logger.info("Varying {} by factor {}", obj.getClass().getSimpleName(), String.format("%.4f", factor));

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(obj);
                if (value == null) continue;

                Class<?> type = field.getType();
                if (type == Double.class || type == double.class) {
                    field.set(obj, ((Number) value).doubleValue() * factor);
                } 
                else if (type == Integer.class || type == int.class) {
                    int oldVal = ((Number) value).intValue();
                    int newVal = (int) Math.round(oldVal * factor);
                    if (newVal == oldVal) {
                        newVal = (factor >= 1.0) ? oldVal + 1 : oldVal - 1;
                    }
                    field.set(obj, newVal);
                } 
                else if (type == String.class && field.getName().toLowerCase().contains("color")) {
                    String hex = (String) value;
                    if (hex.startsWith("#")) {
                        field.set(obj, shiftHexColor(hex, factor));
                    }
                }
            } catch (Exception e) {
            	logger.error("Error modifying field {}: {}", field.getName(), e.getMessage());
            }
        }
        return obj;
    }

    private String shiftHexColor(String hex, double factor) {
        try {
            Color color = Color.decode(hex);
            double adjustedFactor = factor;
            if (color.getRed() > 240 && color.getGreen() > 240 && color.getBlue() > 240 && factor > 1.0) {
                adjustedFactor = 1.0 - (factor - 1.0);
            }
            int r = (int) Math.min(255, Math.max(0, (color.getRed() + 1) * factor));
            int g = (int) Math.min(255, Math.max(0, (color.getGreen() + 1) * factor));
            int b = (int) Math.min(255, Math.max(0, (color.getBlue() + 1) * factor));
            return String.format("#%02X%02X%02X", r, g, b);
        } catch (Exception e) {
            return hex; 
        }
    }
    
    public List<Double> varyDoubleList(List<Double> list, double minPercent, double maxPercent) {
        double factor = calculateFactor(minPercent, maxPercent);
        return list.stream()
                   .map(val -> val == null ? null : val * factor)
                   .collect(Collectors.toList());
    }

    // --- 2. HANDLE LIST OF INTEGERS ---
    public List<Integer> varyIntegerList(List<Integer> list, double minPercent, double maxPercent) {
        double factor = calculateFactor(minPercent, maxPercent);
        return list.stream()
                   .map(val -> {
                       if (val == null) return null;
                       int newVal = (int) Math.round(val * factor);
                       return (newVal == val) ? (factor >= 1.0 ? val + 1 : val - 1) : newVal;
                   })
                   .collect(Collectors.toList());
    }

    public List<String> varyStringList(List<String> list, double minPercent, double maxPercent) {
        double factor = calculateFactor(minPercent, maxPercent);
        return list.stream()
                   .map(str -> (str != null && str.startsWith("#")) ? shiftHexColor(str, factor) : str)
                   .collect(Collectors.toList());
    }
}