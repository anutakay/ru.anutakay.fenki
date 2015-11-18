package ru.anutakay.fenki.model.color;

import java.awt.Color;

public class ColorSchemaImpl implements ColorSchema {

    Color[] colors = { new Color(180, 50, 200), new Color(0, 200, 180), new Color(192, 64, 0) };

    @Override
    public Color getColorByID( Integer colorID) {
        return colors[Math.abs(colorID%colors.length)];
    }

}
