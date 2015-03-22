package ru.anutakay.fenki.model;

import ru.anutakay.fenki.model.Node.HDirection;

public class Schema {

	private static final int MIN_NUMBER_OF_COLUMN = 1;

	private static final int MIN_NUMBER_OF_THREAD = 2;
	
	private String schemaName = "unnamed";
	
	private ColorGroupSchema colorsIDAdapter;
	
	private NodesAndThreadFragmentsStorage storage;
	
	private NodeStoreDimensions dimensions;

	public Schema() {
		this(MIN_NUMBER_OF_THREAD, MIN_NUMBER_OF_COLUMN, true);
	}

	public Schema(	final int numberOfThread, 
					final int numberOfColumn, 
					final boolean firstCrossIsNode) {
		dimensions = new NodeStoreDimensions(numberOfThread, numberOfColumn, firstCrossIsNode);
		storage = new NodesAndThreadFragmentsStorage(dimensions);	
		colorsIDAdapter = new ColorGroupSchema();
	}

	
	public NodeStoreDimensions getDimensions(){
		return dimensions;
	}	
	
	public NodesAndThreadFragmentsStorage getStorage(){
		return this.storage;
	}
	
	public Node getNode(final int i, final int j) {
		return storage.getNode(new NodeIndex(j, i));
	}
	
	public ThreadFragment getThreadFragment(final int i, final int j) {
		return storage.getThreadFragment(new ThreadIndex(i, j));
	}
	
	public int getCorner(final int j, final HDirection hDirection) {
		return storage.getCorner(j, hDirection);
	}
	
	public String getSchemaName() {
		return schemaName;
	}
	
	public ColorGroupSchema getColorsIDAdapter(){
		return this.colorsIDAdapter;
	}
	

}