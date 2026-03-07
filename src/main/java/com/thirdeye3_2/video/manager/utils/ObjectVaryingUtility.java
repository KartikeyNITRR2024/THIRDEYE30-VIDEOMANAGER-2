package com.thirdeye3_2.video.manager.utils;

import java.lang.reflect.Field;
import java.awt.Color;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thirdeye3_2.video.manager.services.PropertyService;

@Component
public class ObjectVaryingUtility {

    @Autowired
    private PropertyService propertyService;

    private static final Logger logger = LoggerFactory.getLogger(ObjectVaryingUtility.class);
    private static final Random random = new Random();

    // --- FACTOR CALCULATORS ---

    private double getAutoFactor(Class<?> type, boolean isColor) {
        double min = 0, max = 0;
        if (type == Double.class || type == double.class) {
            min = propertyService.getVaryFieldsDouble().get(0);
            max = propertyService.getVaryFieldsDouble().get(1);
        } else if (type == Integer.class || type == int.class) {
            min = propertyService.getVaryFieldsInteger().get(0);
            max = propertyService.getVaryFieldsInteger().get(1);
        } else if (isColor) {
            min = propertyService.getVaryFieldsColor().get(0);
            max = propertyService.getVaryFieldsColor().get(1);
        }
        return getRawFactor(min, max);
    }

    private double getRawFactor(double minPercent, double maxPercent) {
        double variation = minPercent + (maxPercent - minPercent) * random.nextDouble();
        return 1.0 + variation;
    }

    // --- OBJECT HANDLING (NEW INSTANCE LOGIC) ---

    @SuppressWarnings("unchecked")
    public <T> T varyFields(T sourceObj) {
        if (sourceObj == null) return null;

        try {
            // Create a NEW instance of the same class
            T targetObj = (T) sourceObj.getClass().getDeclaredConstructor().newInstance();

            Field[] fields = sourceObj.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object originalValue = field.get(sourceObj);

                if (originalValue == null) continue;

                boolean isColorField = field.getName().toLowerCase().contains("color");
                double factor = getAutoFactor(field.getType(), isColorField);

                // Set the varied value onto the NEW target object
                applyVariation(targetObj, field, originalValue, factor, isColorField);
            }
            return targetObj;

        } catch (Exception e) {
            logger.error("Failed to create or vary new instance of {}: {}", 
                sourceObj.getClass().getSimpleName(), e);
            return null;
        }
    }

    private void applyVariation(Object targetObj, Field field, Object value, double factor, boolean isColor) throws IllegalAccessException {
        Class<?> type = field.getType();
        
        if (type == Double.class || type == double.class) {
            field.set(targetObj, ((Number) value).doubleValue() * factor);
        } 
        else if (type == Integer.class || type == int.class) {
            int oldVal = ((Number) value).intValue();
            int newVal = (int) Math.round(oldVal * factor);
            if (newVal == oldVal) newVal = (factor >= 1.0) ? oldVal + 1 : oldVal - 1;
            field.set(targetObj, newVal);
        } 
        else if (isColor && value instanceof String) {
            String hex = (String) value;
            if (hex.startsWith("#")) {
                field.set(targetObj, shiftHexColor(hex, factor));
            } else {
                field.set(targetObj, value); // Copy non-hex string as is
            }
        } 
        else {
            // For all other types (Strings not named color, Booleans, etc.) 
            // we copy the value from the source to the target
            field.set(targetObj, value);
        }
    }

    // --- LIST HANDLERS ---

    public List<Double> varyDoubleList(List<Double> list) {
        if (list == null || list.isEmpty()) return list;
        double factor = getAutoFactor(Double.class, false);
        return list.stream()
                .map(val -> val == null ? null : val * factor)
                .collect(Collectors.toList());
    }

    public List<Integer> varyIntegerList(List<Integer> list) {
        if (list == null || list.isEmpty()) return list;
        double factor = getAutoFactor(Integer.class, false);
        return list.stream()
                .map(val -> {
                    if (val == null) return null;
                    int newVal = (int) Math.round(val * factor);
                    if (newVal == val) newVal = (factor >= 1.0) ? val + 1 : val - 1;
                    return newVal;
                })
                .collect(Collectors.toList());
    }

    public List<String> varyColorStringList(List<String> list) {
        if (list == null || list.isEmpty()) return list;
        double factor = getAutoFactor(String.class, true);
        return list.stream()
                .map(str -> (str != null && str.startsWith("#")) ? shiftHexColor(str, factor) : str)
                .collect(Collectors.toList());
    }

    private String shiftHexColor(String hex, double factor) {
        try {
            Color color = Color.decode(hex);
            int r = (int) Math.min(255, Math.max(0, (color.getRed() + 1) * factor));
            int g = (int) Math.min(255, Math.max(0, (color.getGreen() + 1) * factor));
            int b = (int) Math.min(255, Math.max(0, (color.getBlue() + 1) * factor));
            return String.format("#%02X%02X%02X", r, g, b);
        } catch (Exception e) {
            return hex;
        }
    }
}