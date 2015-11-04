package ru.anutakay.fenki.model;

import java.util.ArrayList;

public class Nodes implements INodes {
	
	private ArrayList<ArrayList<NodeImpl>> nodes;
	
	protected Size dimensions;
	
	public Nodes(final Size dimensions) {
		setDimensions(dimensions);
		createStorage();
	}

	private void createStorage() {
		nodes = new ArrayList<ArrayList<NodeImpl>>();
		final int numberOfColumn = dimensions.getColumnNumber()+1;
		for(int j = 0; j < numberOfColumn; j++) {
			final ArrayList<NodeImpl> m = createColumn(j);
			nodes.add(m);
		}
	}

	private ArrayList<NodeImpl> createColumn(int j) {
		final ArrayList<NodeImpl> m = new ArrayList<NodeImpl>();
		final int numberOfNodeInColumn 
		        = SchemaTemplate.numberOfNodeInColumn(dimensions, j);
		for(int i = 0; i < numberOfNodeInColumn; i++) {
			m.add(new NodeImpl());
		}
		return m;
	}
	
	@Override
	public NodeImpl getNode(final NodeIndex nodeIndex) {
		return nodes.get(nodeIndex.j).get(nodeIndex.i);
	}
	
	@Override
	public Size getDimensions() {
		return dimensions;
	}

	@Override
	public void setDimensions(final Size dimensions) {
		this.dimensions = dimensions;
	}

}
