package ru.anutakay.fenki.model;

import java.util.ArrayList;

public class NodesImpl implements Nodes {
	
	private ArrayList<ArrayList<Node>> storage;
	
	private Size size;
	
	public NodesImpl(final Size dimensions) {
		setDimensions(dimensions);
		createStorage();
	}

	private void createStorage() {
		storage = new ArrayList<ArrayList<Node>>();
		final int numberOfColumn = size.getColumnNumber()+1;
		for(int j = 0; j < numberOfColumn; j++) {
			final ArrayList<Node> m = createColumn(j);
			storage.add(m);
		}
	}

	private ArrayList<Node> createColumn(int j) {
		final ArrayList<Node> m = new ArrayList<Node>();
		final int numberOfNodeInColumn 
		        = SchemaTemplate.numberOfNodeInColumn(size, j);
		for(int i = 0; i < numberOfNodeInColumn; i++) {
			m.add(createEmptyNode());
		}
		return m;
	}

    private Node createEmptyNode() {
        return new NodeImpl();
    }
	
	@Override
	public Node getNode(final NodeIndex nodeIndex) {
		return storage.get(nodeIndex.j).get(nodeIndex.i);
	}
	
	@Override
	public Size getDimensions() {
		return size;
	}

	@Override
	public void setDimensions(final Size dimensions) {
		this.size = dimensions;
	}

}
