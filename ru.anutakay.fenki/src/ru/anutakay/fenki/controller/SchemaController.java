package ru.anutakay.fenki.controller;

import ru.anutakay.fenki.model.SchemaTemplate;
import ru.anutakay.fenki.model.NodeImpl;
import ru.anutakay.fenki.model.NodeIndex;
import ru.anutakay.fenki.model.Size;
import ru.anutakay.fenki.model.SimpleSchema;
import ru.anutakay.fenki.model.Schema;
import ru.anutakay.fenki.model.NodeImpl.Horizontal;

public class SchemaController {
	
	Schema schema;
	
	public SchemaController(final Schema schema) {
		this.schema = schema;
	}
	
	public Schema getSchema() {
		return this.schema;
	}
	
	public void fillSchema(final Filler filler){
		filler.fill(schema);
	}
	
	public void buildSchema() {
		Size dimensions = schema.getDimensions();
		build(schema, dimensions);
	}
	
	private void build(	final SimpleSchema storage, 
						final Size dimensions) {
		for (int j = 0; j < dimensions.getColumnNumber(); j++) {
			build_corner(storage, dimensions, j, Horizontal.LEFT);
			for (int i = 0; i < SchemaTemplate.numberOfNodeInColumn(dimensions, j); i++) {
				build_node(storage, dimensions,new NodeIndex(i, j));
			}
			build_corner(storage, dimensions,j, Horizontal.RIGHT);
		}
	}
	
	
	private void build_node(final SimpleSchema storage, 
							final Size dimensions, 
							final NodeIndex nodeIndex) {
		NodeImpl node = storage.getNode(nodeIndex);

		node.setLeftThreadID(storage.getPrevThreadForNode(nodeIndex, Horizontal.LEFT));
		node.setRightThreadID(storage.getPrevThreadForNode(nodeIndex, Horizontal.RIGHT));
	
		storage.setNextThreadForNode(nodeIndex, Horizontal.RIGHT, node.getEndThreadID(Horizontal.RIGHT));
		storage.setNextThreadForNode(nodeIndex, Horizontal.LEFT, node.getEndThreadID(Horizontal.LEFT));
		
	}
	
	private void build_corner(	final SimpleSchema storage, 
								final Size dimensions, 
								final int j, 
								final Horizontal hDirection) {
		if (SchemaTemplate.isShortColumn(dimensions, j, Horizontal.LEFT) && hDirection == Horizontal.LEFT) {
			NodeIndex index = new NodeIndex(-1, j);
			int value = storage.getPrevThreadForNode(index, Horizontal.RIGHT);
			storage.setNextThreadForNode(index, Horizontal.RIGHT, value);
		}
		if ((hDirection == Horizontal.RIGHT && SchemaTemplate.isShortColumn(dimensions, j, Horizontal.RIGHT))) {
			NodeIndex index = new NodeIndex(SchemaTemplate.numberOfNodeInColumn(dimensions, j), j);
			int value = storage.getPrevThreadForNode(index, Horizontal.LEFT);
			storage.setNextThreadForNode(index, Horizontal.LEFT, value);
		}
	}

}
