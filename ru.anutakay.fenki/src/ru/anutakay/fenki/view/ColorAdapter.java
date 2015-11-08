package ru.anutakay.fenki.view;

import ru.anutakay.fenki.controller.SchemaController;
import ru.anutakay.fenki.model.FieldIterator;
import ru.anutakay.fenki.model.Schema;
import ru.anutakay.fenki.model.size.Size;
import ru.anutakay.fenki.model.thread.Thread;

@SuppressWarnings({ "rawtypes" })
public class ColorAdapter<T extends Iterator2D> implements Adapter {

    private Size size;

    private SchemaController schemaController;

    public ColorAdapter(final SchemaController schemaController) {
        this.schemaController = schemaController;
        size = this.schemaController.getSchema().getSize();
    }

    public Integer getObject(final Iterator2D it1) {

        FieldIterator it = (FieldIterator) it1;
        Schema schema = this.schemaController.getSchema();

        if (it.isThread()) {
            Thread a = schema.getThreadFragment(it.getThreadIndex())
                    .getThread();
            return getColorForNum(a);
        }

        if (it.isNode()) {
            Thread a = schema.getNode(it.getNodeIndex()).getFirst();
            return getColorForNum(a);
        }

        if (it.isCorner()) {
            CornerIndex cornerIndex = it.getCornerIndex();
            Thread a = schema.getCorner(cornerIndex);
            return getColorForNum(a);
        }

        return null;
    }

    @Override
    public FieldIterator getIterator() {
        return new FieldIterator(this.size);
    }

    private Integer getColorForNum(Thread threadID) {
        return Integer.valueOf(threadID.getID());
    }

}
