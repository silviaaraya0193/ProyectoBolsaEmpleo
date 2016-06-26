/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import models.utils.Anotacion;

/**
 *
 * @author viccr
 */
public class Inspector {
    public String obtener_nombre_clase(Object o){
        return o.getClass().getName();
    }
    
    public HashMap<String, Object> obtener_campos(Object o) throws IllegalArgumentException, IllegalAccessException {
        Class<?> clazz = o.getClass();
        HashMap<String, Object> dev = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Anotacion.class)) {
                for (Annotation anotacion : field.getDeclaredAnnotationsByType(Anotacion.class)) {
                    Anotacion acampo = (Anotacion) anotacion;
                    if (acampo.guardar_xml()) {
                        field.setAccessible(true);
                        dev.put( field.getName(), field.get(o));
                    }
                }
            }
        }
        return dev;
    }
    
}
