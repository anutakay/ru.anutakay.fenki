package ru.anutakay.fenki.model;

public interface INodes {
	
	public Node getNode(final NodeIndex nodeIndex);
	
	public Dimensions getDimensions();
	
	public void setDimensions(final Dimensions dimensions);

}