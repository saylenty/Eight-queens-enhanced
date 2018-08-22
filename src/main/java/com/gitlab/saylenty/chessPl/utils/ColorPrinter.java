/*
  Saylenty on 11-Apr-17.
  Copyright (c) 2017
 */
package com.gitlab.saylenty.chessPl.utils;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ColorPrinter {
    private final Map<Integer, String> map;

    public ColorPrinter() {
        map = new HashMap<>();
        for (Field f : Color.class.getFields()) {
            if (f.getType() == Color.class) {
                try {
                    Color c = (Color) f.get(null);
                    map.put(c.getRGB(), f.getName());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getColorName(Integer color) {
        return map.get(color);
    }
}