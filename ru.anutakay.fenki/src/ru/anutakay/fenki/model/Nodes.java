package ru.anutakay.fenki.model;

public interface Nodes {
	
	public Node getNode(final NodeIndex nodeIndex);
	
	public Size getDimensions();
	
	public void setDimensions(final Size dimensions);

}