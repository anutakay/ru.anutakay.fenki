package ru.anutakay.fenki.controller;

import ru.anutakay.fenki.model.SchemaTemplate;
import ru.anutakay.fenki.model.Node;
import ru.anutakay.fenki.model.NodeIndex;
import ru.anutakay.fenki.model.Dimensions;
import ru.anutakay.fenki.model.SimpleSchema;
import ru.anutakay.fenki.model.Schema;
import ru.anutakay.fenki.model.Node.HDirection;

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
		Dimensions dimensions = schema.getDimensions();
		build(schema, dimensions);
	}
	
	private void build(	final SimpleSchema storage, 
						final Dimensions dimensions) {
		for (int j = 0; j < dimensions.getColumnNumber(); j++) {
			build_corner(storage, dimensions, j, HDirection.LEFT);
			for (int i = 0; i < SchemaTemplate.numberOfNodeInColumn(dimensions, j); i++) {
				build_node(storage, dimensions,new NodeIndex(i, j));
			}
			build_corner(storage, dimensions,j, HDirection.RIGHT);
		}
	}
	
	
	private void build_node(final SimpleSchema storage, 
							final Dimensions dimensions, 
							final NodeIndex nodeIndex) {
		Node node = storage.getNode(nodeIndex);

		node.setLeftThreadID(storage.getPrevThreadForNode(nodeIndex, HDirection.LEFT));
		node.setRightThreadID(storage.getPrevThreadForNode(nodeIndex, HDirection.RIGHT));
	
		storage.setNextThreadForNode(nodeIndex, HDirection.RIGHT, node.getBottomThreadID(HDirection.RIGHT));
		storage.setNextThreadForNode(nodeIndex, HDirection.LEFT, node.getBottomThreadID(HDirection.LEFT));
		
	}
	
	private void build_corner(	final SimpleSchema storage, 
								final Dimensions dimensions, 
								final int j, 
								final HDirection hDirection) {
		if (SchemaTemplate.isShortColumn(dimensions, j, HDirection.LEFT) && hDirection == HDirection.LEFT) {
			NodeIndex index = new NodeIndex(-1, j);
			int value = storage.getPrevThreadForNode(index, HDirection.RIGHT);
			storage.setNextThreadForNode(index, HDirection.RIGHT, value);
		}
		if ((hDirection == HDirection.RIGHT && SchemaTemplate.isShortColumn(dimensions, j, HDirection.RIGHT))) {
			NodeIndex index = new NodeIndex(SchemaTemplate.numberOfNodeInColumn(dimensions, j), j);
			int value = storage.getPrevThreadForNode(index, HDirection.LEFT);
			storage.setNextThreadForNode(index, HDirection.LEFT, value);
		}
	}

}
