package ru.anutakay.fenki.controller;

import ru.anutakay.fenki.model.SchemaTemplate;
import ru.anutakay.fenki.model.NodeImpl;
import ru.anutakay.fenki.model.NodeIndex;
import ru.anutakay.fenki.model.Size;
import ru.anutakay.fenki.model.SimpleSchema;
import ru.anutakay.fenki.model.Schema;
import ru.anutakay.fenki.model.H;
import ru.anutakay.fenki.model.Thread;

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

    private void build(final SimpleSchema storage, final Size dimensions) {
        for (int j = 0; j < dimensions.getColumnNumber(); j++) {
            build_corner(storage, dimensions, j, H.LEFT);
            for (int i = 0; i < SchemaTemplate.numberOfNodeInColumn(dimensions,
                    j); i++) {
                build_node(storage, dimensions, new NodeIndex(i, j));
            }
            build_corner(storage, dimensions, j, H.RIGHT);
        }
    }

    private void build_node(final SimpleSchema storage, final Size dimensions,
            final NodeIndex nodeIndex) {
        NodeImpl node = storage.getNode(nodeIndex);

        node.setBegin(getPrev(storage, nodeIndex, H.LEFT),
                getPrev(storage, nodeIndex, H.RIGHT));

        storage.setNextThreadForNode(nodeIndex, H.RIGHT, node.getEnd(H.RIGHT));
        storage.setNextThreadForNode(nodeIndex, H.LEFT, node.getEnd(H.LEFT));

    }

    private Thread getPrev(SimpleSchema storage, NodeIndex ni, H h) {
        return storage.getPrevThreadForNode(ni, h);
    }

    private void build_corner(final SimpleSchema storage,
            final Size dimensions, final int j, final H hDirection) {
        if (SchemaTemplate.isShortColumn(dimensions, j, H.LEFT)
                && hDirection == H.LEFT) {
            NodeIndex index = new NodeIndex(-1, j);
            Thread value = storage.getPrevThreadForNode(index, H.RIGHT);
            storage.setNextThreadForNode(index, H.RIGHT, value);
        }
        if ((hDirection == H.RIGHT && SchemaTemplate.isShortColumn(dimensions,
                j, H.RIGHT))) {
            NodeIndex index = new NodeIndex(
                    SchemaTemplate.numberOfNodeInColumn(dimensions, j), j);
            Thread value = storage.getPrevThreadForNode(index, H.LEFT);
            storage.setNextThreadForNode(index, H.LEFT, value);
        }
    }

}
