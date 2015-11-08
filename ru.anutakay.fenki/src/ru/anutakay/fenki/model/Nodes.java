package ru.anutakay.fenki.model;

public interface Nodes {
	
	public Node getNode(final NodeIndex nodeIndex);
	
	public void setSize(final Size dimensions);
	
	public Size getSize();
	
	public void setFactory(final NodeFactory factory);
	
	public NodeFactory NodeFactory();

}