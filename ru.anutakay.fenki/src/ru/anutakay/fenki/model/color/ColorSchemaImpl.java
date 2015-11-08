package ru.anutakay.fenki.model.color;

import java.awt.Color;

public class ColorSchemaImpl implements ColorSchema {

    Color[] colors = { new Color(180, 50, 200), new Color(0, 200, 180) };

    @Override
    public Color getColorByID(final ColorID colorID) {
        if (checkID(colorID)) {
            return colors[colorID.id];
        } else {
            return null;
        }
    }

    private boolean checkID(final ColorID colorID) {
        if (colorID.id < 0 || colorID.id >= colors.length) {
            return false;
        } else {
            return true;
        }
    }

}
