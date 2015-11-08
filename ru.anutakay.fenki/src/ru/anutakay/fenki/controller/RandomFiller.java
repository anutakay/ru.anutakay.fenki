package ru.anutakay.fenki.controller;

import java.util.Random;

import ru.anutakay.fenki.model.ColumnTemplate;
import ru.anutakay.fenki.model.Schema;
import ru.anutakay.fenki.model.Arrow;

public class RandomFiller extends Filler implements IRandomizer<Arrow> {

	@Override
	public void fill(Schema schema) {
		for(int j = 0; j < schema.getDimensions().columns()+1; j++){
			for(int i = 0; i < new ColumnTemplate(j, schema.getDimensions()).lenght(); i++){
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
