package ru.anutakay.fenki.controller;

import java.util.Random;

import ru.anutakay.fenki.model.NodeImpl;
import ru.anutakay.fenki.model.SchemaTemplate;
import ru.anutakay.fenki.model.Schema;
import ru.anutakay.fenki.model.NodeImpl.Direction;

public class RandomFiller extends Filler implements IRandomizer<Direction> {

	@Override
	public void fill(Schema schema) {
		for(int j = 0; j < schema.getDimensions().getColumnNumber()+1; j++){
			for(int i = 0; i < SchemaTemplate.numberOfNodeInColumn(schema.getDimensions(), j); i++){
				schema.getNode(j, i).setDirection(createRandomObject(j,i));
			}
		}
	}

	@Override
	public Direction createRandomObject(int i, int j) {
		Random r = new Random();
		return NodeImpl.Direction.values()[Math.abs(r.nextInt())%4+1];
	}

}
