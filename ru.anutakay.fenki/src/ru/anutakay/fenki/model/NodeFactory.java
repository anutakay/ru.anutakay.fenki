package ru.anutakay.fenki.model;

public class NodeFactory {
    
    public NodeFactory(){};
    
    public Node createEmptyNode() {
        return new NodeImpl();
    }
}
