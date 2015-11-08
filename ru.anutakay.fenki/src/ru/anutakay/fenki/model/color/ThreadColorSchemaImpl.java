package ru.anutakay.fenki.model.color;

import java.awt.Color;

import ru.anutakay.fenki.model.thread.Thread;

public class ThreadColorSchemaImpl implements ThreadColorSchema {

    private static final Color EMPTY_COLOR = Color.WHITE;

    GroupColorSchema groupColorSchema;

    ColorSchema colorSchema;

    public ThreadColorSchemaImpl(final GroupColorSchema groupColorSchema,
            final ColorSchema colorSchema) {
        this.groupColorSchema = groupColorSchema;
        this.colorSchema = colorSchema;
    }

    @Override
    public Color getColorByThreadID(final Thread threadID) {
        final ColorID colorID = this.groupColorSchema.getColorID(threadID);
        final Color color = this.colorSchema.getColorByID(colorID);
        if (color == null) {
            return EMPTY_COLOR;
        } else {
            return color;
        }
    }
}
