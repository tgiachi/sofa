package com.github.tgiachi.sofa.sofaserver.utils;

import org.reflections.Reflections;

import java.util.Set;

/**
 * Classe utility per prendere le annotazioni
 */
@SuppressWarnings("unchecked")
public class ReflectionUtils {
    /**
     * Restituisce il set delle annotazioni
     *
     * @param type
     * @return
     */
    public static Set<Class<?>> getAnnotation(Class<?> type) {

        return getAnnotation("com.github", type);
    }

    /**
     * Restituisce il set delle annotazioni passando il prefisso del package
     *
     * @param prefix where scan
     * @param type
     * @return
     */
    public static Set<Class<?>> getAnnotation(String prefix, Class type) {
        return new Reflections(prefix).getTypesAnnotatedWith(type);
    }
}

