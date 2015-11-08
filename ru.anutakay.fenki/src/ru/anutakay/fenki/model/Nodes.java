package ru.anutakay.fenki.model;

import ru.anutakay.fenki.model.size.Size;

public interface Nodes {

    public Node getNode(final NodeIndex index);

    public void setSize(final Size size);

    public Size getSize();

    public void setFactory(final NodeFactory factory);

    public NodeFactory NodeFactory();

}