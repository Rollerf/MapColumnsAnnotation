package com.rollerf.mapcolumns;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ObjectToMapConverter {
    public Map<String, String> getColumnsMapped(Object object) throws MapColumnsException {
        try {
            checkIfMappable(object);
            initializeObject(object);

            return getFieldsMapped(object);

        } catch (Exception e) {
            throw new MapColumnsException(e.getMessage());
        }
    }

    private void checkIfMappable(Object object) {
        if (Objects.isNull(object)) {
            throw new MapColumnsException("Can't map a null object");
        }

        Class<?> clazz = object.getClass();
        if (!clazz.isAnnotationPresent(MapColumns.class)) {
            throw new MapColumnsException("The class " + clazz.getSimpleName() + " is not annotated with MapColumns");
        }
    }

    private void initializeObject(Object object) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Init.class)) {
                method.setAccessible(true);
                method.invoke(object);
            }
        }
    }

    private Map<String, String> getFieldsMapped(Object object) throws IllegalArgumentException, IllegalAccessException {
        Class<?> clazz = object.getClass();
        Map<String, String> columnsMap = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                columnsMap.put(column.name(), (String) field.get(object));
            }
        }

        return columnsMap;
    }
}
