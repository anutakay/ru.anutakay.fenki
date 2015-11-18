package ru.anutakay.fenki.controller;

import ru.anutakay.fenki.model.Node;
import ru.anutakay.fenki.model.NodeIndex;
import ru.anutakay.fenki.model.Schema;
import ru.anutakay.fenki.model.H;
import ru.anutakay.fenki.model.size.ColumnTemplate;
import ru.anutakay.fenki.model.size.Size;

public class SchemaController {

    Schema schema;

    public SchemaController(final Schema schema) {
        this.schema = schema;
    }

    public Schema getSchema() {
        return this.schema;
    }

    public void fill(final Filler filler) {
        filler.fill(schema);
    }

    public void build() {
        final Size size = schema.getSize();
        for (int j = 0; j < size.columns(); j++) {
            buildCorner(size, j, H.LEFT);
            buildColumn(size, j);
            buildCorner(size, j, H.RIGHT);
        }
    }
    
    private void buildCorner(final Size size, final int j, final H h) {
        ColumnTemplate column = size.columnTemplate(j);
        H reverse = H.reverse(h);
        if (column.hasCorner(h)) {
            NodeIndex index = column.getCornerIndex(h);
            Integer value = schema.getPrevThreadForNode(index, reverse);
            schema.setNextThreadForNode(index, reverse, value);
        }
    }

    private void buildColumn(final Size size, int j) {
        ColumnTemplate column = size.columnTemplate(j);
        for (int i = 0; i < column.lenght(); i++) {
            buildNode(size, new NodeIndex(i, j));
        }
    }

    private void buildNode(final Size dimensions, final NodeIndex nodeIndex) {
        Node node = schema.getNode(nodeIndex);

        Integer left = schema.getPrevThreadForNode(nodeIndex, H.LEFT);
        Integer right = schema.getPrevThreadForNode(nodeIndex, H.RIGHT);
        node.setBegin(left, right);

        schema.setNextThreadForNode(nodeIndex, H.RIGHT, node.getEnd(H.RIGHT));
        schema.setNextThreadForNode(nodeIndex, H.LEFT, node.getEnd(H.LEFT));

    }
}
