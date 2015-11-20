package ru.anutakay.fenki.model.color;

import java.awt.Color;

public class ThreadColorSchemaImpl implements ThreadColorSchema {

    ColorSchema colorSchema;

    public ThreadColorSchemaImpl(
            final ColorSchema colorSchema) {
        this.colorSchema = colorSchema;
    }

    @Override
    public Color getColorByThreadID(final Integer threadID) {
        final Color color = this.colorSchema.getColorByID(threadID);
        return color;
    }
}
