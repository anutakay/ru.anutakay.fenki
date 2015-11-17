package ru.anutakay.fenki.controller;

import java.util.Random;

import ru.anutakay.fenki.model.Arrow;
import ru.anutakay.fenki.model.Schema;
import ru.anutakay.fenki.model.size.ColumnTemplate;
import ru.anutakay.fenki.model.size.Size;

public class RandomFiller extends Filler implements IRandomizer<Arrow> {

	@Override
	public void fill(Schema schema) {
		Size size = schema.getSize();
        for (int j = 0; j < size.columns()+1; j++) {
            ColumnTemplate column = size.columnTemplate(j);
			for(int i = 0; i < column.lenght(); i++){
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
