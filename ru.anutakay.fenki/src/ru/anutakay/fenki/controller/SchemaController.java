package ru.anutakay.fenki.controller;

import ru.anutakay.fenki.model.Node;
import ru.anutakay.fenki.model.NodeIndex;
import ru.anutakay.fenki.model.SimpleSchema;
import ru.anutakay.fenki.model.Schema;
import ru.anutakay.fenki.model.H;
import ru.anutakay.fenki.model.Thread;
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

    public void fillSchema(final Filler filler) {
        filler.fill(schema);
    }

    public void buildSchema() {
        Size dimensions = schema.getDimensions();
        build(schema, dimensions);
    }

    private void build(final SimpleSchema storage, final Size size) {
        for (int j = 0; j < size.columns(); j++) {
            build_corner(storage, size, j, H.LEFT);
            ColumnTemplate column = size.columnTemplate(j);
            for (int i = 0; i < column.lenght(); i++) {
                build_node(storage, size, new NodeIndex(i, j));
            }
            build_corner(storage, size, j, H.RIGHT);
        }
    }

    private void build_node(final SimpleSchema storage, final Size dimensions,
            final NodeIndex nodeIndex) {
        Node node = storage.getNode(nodeIndex);

        node.setBegin(getPrev(storage, nodeIndex, H.LEFT),
                getPrev(storage, nodeIndex, H.RIGHT));

        storage.setNextThreadForNode(nodeIndex, H.RIGHT, node.getEnd(H.RIGHT));
        storage.setNextThreadForNode(nodeIndex, H.LEFT, node.getEnd(H.LEFT));

    }

    private Thread getPrev(SimpleSchema storage, NodeIndex ni, H h) {
        return storage.getPrevThreadForNode(ni, h);
    }

    private void build_corner(final SimpleSchema storage,
            final Size size, final int j, final H hDirection) {
        ColumnTemplate column = size.columnTemplate(j);
        if (column.isShort(H.LEFT)
                && hDirection == H.LEFT) {
            NodeIndex index = new NodeIndex(-1, j);
            Thread value = storage.getPrevThreadForNode(index, H.RIGHT);
            storage.setNextThreadForNode(index, H.RIGHT, value);
        }
        if ((hDirection == H.RIGHT && column
                .isShort(H.RIGHT))) {
            NodeIndex index = new NodeIndex(
                    column.lenght(), j);
            Thread value = storage.getPrevThreadForNode(index, H.LEFT);
            storage.setNextThreadForNode(index, H.LEFT, value);
        }
    }

}
