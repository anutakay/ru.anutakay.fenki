package ru.anutakay.fenki.view;

import java.awt.Color;

import ru.anutakay.fenki.model.color.ThreadColorSchema;
import ru.anutakay.fenki.model.thread.Thread;

@SuppressWarnings("rawtypes")
public class SchemaFigureFactory<T extends Iterator2D> extends FigureFactory {

    private PointAdapter pointAdapter;

    private ThreadColorSchema threadColorSchema;

    @SuppressWarnings("unchecked")
    public SchemaFigureFactory(
            final Adapter<? super Iterator2D, ? super Object> adapter) {
        super(adapter);
        this.pointAdapter = new PointAdapter(adapter.getIterator());
    }

    public SchemaFigureFactory(
            final Adapter<? super Iterator2D, ? super Object> adapter,
            final ThreadColorSchema threadColorSchema) {
        this(adapter);
        this.threadColorSchema = threadColorSchema;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Figure makeFigure(final Iterator2D it) {

        final Figure1 figure = new Figure1(pointAdapter.getObject(it));

        final Thread threadID = (Thread) mAdapter.getObject(it);

        if (threadID == null) {
            return null;
        }

        Color color = Color.WHITE;

        if (threadColorSchema != null) {
            color = threadColorSchema.getColorByThreadID(threadID);
        }

        figure.setColor(color);

        return figure;
    }

}
