package ru.anutakay.fenki.model;

import java.util.ArrayList;

import ru.anutakay.fenki.model.size.ColumnTemplate;
import ru.anutakay.fenki.model.size.Size;

public class NodesImpl implements Nodes {

    private ArrayList<ArrayList<Node>> storage;

    private Size size;

    NodeFactory factory;

    public NodesImpl(final Size size, final NodeFactory factory) {
        setSize(size);
        setFactory(factory);
        createStorage();
    }

    private void createStorage() {
        storage = new ArrayList<ArrayList<Node>>();
        final int colNumber = size.columns();
        for (int j = 0; j <= colNumber; j++) {
            final ArrayList<Node> m = createColumn(j);
            storage.add(m);
        }
    }

    private ArrayList<Node> createColumn(int j) {
        final ArrayList<Node> m = new ArrayList<Node>();
        ColumnTemplate column = size.columnTemplate(j);
        final int lenght = column.lenght();
        for (int i = 0; i < lenght; i++) {
            m.add(createEmptyNode());
        }
        return m;
    }

    private Node createEmptyNode() {
        return factory.createEmptyNode();
    }

    @Override
    public Node getNode(final NodeIndex nodeIndex) {
        return storage.get(nodeIndex.j).get(nodeIndex.i);
    }

    @Override
    public void setSize(final Size size) {
        this.size = size;
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public void setFactory(NodeFactory factory) {
        this.factory = factory;
    }

    @Override
    public NodeFactory NodeFactory() {
        return factory;
    }

}
