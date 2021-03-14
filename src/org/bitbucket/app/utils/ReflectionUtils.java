package org.bitbucket.app.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReflectionUtils {

    public static List<Field> fields(Class<?> clz) {
        Field[] fields = clz.getDeclaredFields();
        return Arrays.stream(fields)
                .filter(f -> f.isAnnotationPresent(PersonColumn.class))
                .collect(Collectors.toList());
    }
}
