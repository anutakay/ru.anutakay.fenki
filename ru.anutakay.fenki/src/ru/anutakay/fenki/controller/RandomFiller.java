package ru.anutakay.fenki.controller;

import java.util.Random;

import ru.anutakay.fenki.model.SchemaTemplate;
import ru.anutakay.fenki.model.Schema;
import ru.anutakay.fenki.model.Arrow;

public class RandomFiller extends Filler implements IRandomizer<Arrow> {

	@Override
	public void fill(Schema schema) {
		for(int j = 0; j < schema.getDimensions().getColumnNumber()+1; j++){
			for(int i = 0; i < SchemaTemplate.numberOfNodeInColumn(schema.getDimensions(), j); i++){
				schema.getNode(j, i).setArrow(createRandomObject(j,i));
			}
		}
	}

	@Override
	public Arrow createRandomObject(int i, int j) {
		Random r = new Random();
		return Arrow.values()[Math.abs(r.nextInt())%4+1];
	}

}
