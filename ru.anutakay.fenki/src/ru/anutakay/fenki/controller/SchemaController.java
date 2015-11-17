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
        buildAll(schema);
    }

    private void buildAll(final Schema storage) {
        final Size size = schema.getSize();
        for (int j = 0; j < size.columns(); j++) {
            build_corner(storage, size, j, H.LEFT);
            build_nodes(storage, size, j);
            build_corner(storage, size, j, H.RIGHT);
        }
    }

    private void build_nodes(final Schema storage, final Size size, int j) {
        ColumnTemplate column = size.columnTemplate(j);
        for (int i = 0; i < column.lenght(); i++) {
            build_node(storage, size, new NodeIndex(i, j));
        }
    }

    private void build_node(final Schema storage, final Size dimensions,
            final NodeIndex nodeIndex) {
        Node node = storage.getNode(nodeIndex);

        node.setBegin(getPrev(storage, nodeIndex, H.LEFT),
                getPrev(storage, nodeIndex, H.RIGHT));

        storage.setNextThreadForNode(nodeIndex, H.RIGHT, node.getEnd(H.RIGHT));
        storage.setNextThreadForNode(nodeIndex, H.LEFT, node.getEnd(H.LEFT));

    }

    private Integer getPrev(Schema storage, NodeIndex ni, H h) {
        return storage.getPrevThreadForNode(ni, h);
    }

    private void build_corner(final Schema storage, final Size size,
            final int j, final H hDirection) {
        ColumnTemplate column = size.columnTemplate(j);
        if (column.isShort(H.LEFT) && hDirection == H.LEFT) {
            NodeIndex index = new NodeIndex(-1, j);
            Integer value = storage.getPrevThreadForNode(index, H.RIGHT);
            storage.setNextThreadForNode(index, H.RIGHT, value);
        }
        if ((hDirection == H.RIGHT && column.isShort(H.RIGHT))) {
            NodeIndex index = new NodeIndex(column.lenght(), j);
            Integer value = storage.getPrevThreadForNode(index, H.LEFT);
            storage.setNextThreadForNode(index, H.LEFT, value);
        }
    }

}
