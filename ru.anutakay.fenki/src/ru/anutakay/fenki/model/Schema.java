package ru.anutakay.fenki.model;

import ru.anutakay.fenki.model.Const.HDirection;

public class Schema {
	
	private static final int MIN_NUMBER_OF_COLUMN = 1;

	private static final int MIN_NUMBER_OF_THREAD = 2;
	
	private NodesAndThreadFragmentsStorage storage;
	
	private NodeStoreDimensions dimensions;

	public Schema() 
	{
		this(MIN_NUMBER_OF_THREAD, MIN_NUMBER_OF_COLUMN, true);
	}

	public Schema(final int numberOfThread, final int numberOfColumn, final boolean firstCrossIsNode) 
	{
		dimensions = new NodeStoreDimensions(numberOfThread, numberOfColumn, firstCrossIsNode);
		storage = new NodesAndThreadFragmentsStorage(dimensions);	
	}

	public void build() {
		for (int j = 0; j < dimensions.getColumnNumber(); j++) {
			build_corner(j, HDirection.LEFT);
			for (int i = 0; i < FieldTemplate.numberOfNodeInColumn(this.dimensions, j); i++) {
				build_node(new NodeIndex(i, j));
			}
			build_corner(j, HDirection.RIGHT);
		}
	}
	
	public NodeStoreDimensions getDimensions(){
		return dimensions;
	}
	
	public Node getNode(final int i, final int j) {
		return storage.getNode(new NodeIndex(j, i));
	}
	
	public ThreadFragment getThreadFragment(final int i, final int j) {
		return storage.getThreadFragment(new ThreadIndex(i, j));
	}
	
	public int getCorner(final int j, final HDirection hDirection){
		return storage.getCorner(j, hDirection);
	}
	
	public void build_node(final NodeIndex nodeIndex) 
	{
		Node node = storage.getNode(nodeIndex);

		node.setLeftThreadID(storage.getPrevThreadForNode(nodeIndex, HDirection.LEFT));
		node.setRightThreadID(storage.getPrevThreadForNode(nodeIndex, HDirection.RIGHT));
	
		storage.setNextThreadForNode(nodeIndex, HDirection.RIGHT, node.getBottomThreadID(HDirection.RIGHT));
		storage.setNextThreadForNode(nodeIndex, HDirection.LEFT, node.getBottomThreadID(HDirection.LEFT));
		
	}
	
	public void build_corner(final int j, final HDirection hDirection) {
		if (FieldTemplate.isShortColumn(this.dimensions, j, HDirection.LEFT) && hDirection == HDirection.LEFT) {
			NodeIndex index = new NodeIndex(-1, j);
			int value = storage.getPrevThreadForNode(index, HDirection.RIGHT);
			storage.setNextThreadForNode(index, HDirection.RIGHT, value);
		}
		if ((hDirection == HDirection.RIGHT && FieldTemplate.isShortColumn(this.dimensions, j, HDirection.RIGHT))) {
			NodeIndex index = new NodeIndex(FieldTemplate.numberOfNodeInColumn(this.dimensions, j), j);
			int value = storage.getPrevThreadForNode(index, HDirection.LEFT);
			storage.setNextThreadForNode(index, HDirection.LEFT, value);
		}
	}

}