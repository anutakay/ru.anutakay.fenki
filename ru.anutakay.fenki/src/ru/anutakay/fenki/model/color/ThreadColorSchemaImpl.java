package ru.anutakay.fenki.model.color;

import java.awt.Color;

public class ThreadColorSchemaImpl implements ThreadColorSchema {

    private static final Color EMPTY_COLOR = Color.WHITE;

    ColorSchema colorSchema;

    public ThreadColorSchemaImpl(
            final ColorSchema colorSchema) {
        this.colorSchema = colorSchema;
    }

    @Override
    public Color getColorByThreadID(final Integer threadID) {
        final Color color = this.colorSchema.getColorByID(threadID);
        //TODO убрать возвращениe нуля
        if (color == null) {
            return EMPTY_COLOR;
        } else {
            return color;
        }
    }
}
