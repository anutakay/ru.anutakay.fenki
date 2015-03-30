package ru.anutakay.fenki.model;

import java.util.ArrayList;

public class NodeStorage {
	
	private ArrayList<ArrayList<Node>> nodes;
	
	protected Dimensions dimensions;
	
	public NodeStorage(final Dimensions dimensions) {
		this.dimensions = dimensions;
		createStorage();
	}

	private void createStorage() {
		this.nodes = new ArrayList<ArrayList<Node>>();
		final int numberOfColumn = this.dimensions.getColumnNumber()+1;
		for(int j = 0; j < numberOfColumn; j++) {
			final ArrayList<Node> m = createColumn(j);
		this.nodes.add(m);
		}
	}

	private ArrayList<Node> createColumn(int j) {
		final ArrayList<Node> m = new ArrayList<Node>();
		final int numberOfNodeInColumn 
			= SchemaTemplate.numberOfNodeInColumn(this.dimensions, j);
		for(int i = 0; i < numberOfNodeInColumn; i++) {
			m.add(new Node());
		}
		return m;
	}
	
	public Node getNode(final NodeIndex nodeIndex) {
		return nodes.get(nodeIndex.j).get(nodeIndex.i);
	}
	
	public Dimensions getDimensions() {
		return this.dimensions;
	}

}
