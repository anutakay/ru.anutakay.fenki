package ru.anutakay.fenki.model;

import java.util.Random;

import ru.anutakay.fenki.model.Node.Direction;

public class RandomFiller extends Filler implements IRandomizer<Direction> {

	@Override
	public void fill(Schema schema) {
		for(int j = 0; j < schema.getDimensions().getColumnNumber()+1; j++){
			for(int i = 0; i < FieldTemplate.numberOfNodeInColumn(schema.getDimensions(), j); i++){
				schema.getNode(j, i).setDirection(createRandomObject(j,i));
			}
		}
	}

	@Override
	public Direction createRandomObject(int i, int j) {
		Random r = new Random();
		return Node.Direction.values()[Math.abs(r.nextInt())%4+1];
	}

}
