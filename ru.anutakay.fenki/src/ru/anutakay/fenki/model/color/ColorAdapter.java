package ru.anutakay.fenki.model.color;

import ru.anutakay.fenki.controller.SchemaController;
import ru.anutakay.fenki.model.FieldIterator;
import ru.anutakay.fenki.model.Schema;
import ru.anutakay.fenki.model.size.Size;
import ru.anutakay.fenki.model.thread.Thread;
import ru.anutakay.fenki.view.Adapter;
import ru.anutakay.fenki.view.CornerIndex;
import ru.anutakay.fenki.view.Iterator2D;

@SuppressWarnings({ "rawtypes" })
public class ColorAdapter<T extends Iterator2D> implements Adapter {

    private Size size;

    private SchemaController schemaController;

    public ColorAdapter(final SchemaController schemaController) {
        this.schemaController = schemaController;
        size = this.schemaController.getSchema().getSize();
    }

    public Thread getObject(final Iterator2D it1) {

        FieldIterator it = (FieldIterator) it1;
        Schema schema = this.schemaController.getSchema();

        if (it.isThread()) {
            Thread a = schema.getThreadFragment(it.getThreadIndex())
                    .getThread();
            //return getColorForNum(a);
            return a;
        }

        if (it.isNode()) {
            Thread a = schema.getNode(it.getNodeIndex()).getFirst();
            return a;
        }

        if (it.isCorner()) {
            CornerIndex cornerIndex = it.getCornerIndex();
            Thread a = schema.getCorner(cornerIndex);
            return a;
        }

        return null;
    }

    @Override
    public FieldIterator getIterator() {
        return new FieldIterator(this.size);
    }
}
