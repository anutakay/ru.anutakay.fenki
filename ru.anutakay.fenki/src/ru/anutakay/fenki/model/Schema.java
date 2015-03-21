package ru.anutakay.fenki.model;

import ru.anutakay.fenki.model.Const.HDirection;

public class Schema {
	
	private static final int MIN_NUMBER_OF_COLUMN = 1;

	private static final int MIN_NUMBER_OF_THREAD = 2;
	
	private NodesAndThreadFragmentsStorage nodesAndThreadFragments;
	
	private NodeStoreDimensions dimensions;

	public Schema() 
	{
		this(MIN_NUMBER_OF_THREAD, MIN_NUMBER_OF_COLUMN, true);
	}

	public Schema(final int numberOfThread, final int numberOfColumn, final boolean firstCrossIsNode) 
	{
		dimensions = new NodeStoreDimensions(numberOfThread, numberOfColumn, firstCrossIsNode);
		nodesAndThreadFragments = new NodesAndThreadFragmentsStorage(dimensions);	
	}

	public void build() {
		for (int j = 0; j < dimensions.getColumnNumber(); j++) {
			nodesAndThreadFragments.build_corner(j, HDirection.LEFT);
			for (int i = 0; i < FieldTemplate.numberOfNodeInColumn(this.dimensions, j); i++) {
				nodesAndThreadFragments.build_node(new NodeIndex(i, j));
			}
			nodesAndThreadFragments.build_corner(j, HDirection.RIGHT);
		}
	}
	
	public NodeStoreDimensions getDimensions(){
		return dimensions;
	}
	
	public Node getNode(final int i, final int j) {
		return nodesAndThreadFragments.getNode(new NodeIndex(j, i));
	}
	
	public ThreadFragment getThreadFragment(final int i, final int j) {
		return nodesAndThreadFragments.getThread(new ThreadIndex(i, j));
	}
	
	public int getCorner(final int j, final HDirection hDirection){
		return nodesAndThreadFragments.getCorner(j, hDirection);
	}

}