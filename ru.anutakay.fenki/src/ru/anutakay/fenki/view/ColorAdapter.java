package ru.anutakay.fenki.view;

import java.awt.Color;

import ru.anutakay.fenki.controller.SchemaController;
import ru.anutakay.fenki.model.ColorSchema;
import ru.anutakay.fenki.model.FieldIterator;
import ru.anutakay.fenki.model.Dimensions;
import ru.anutakay.fenki.model.Schema;

@SuppressWarnings("rawtypes")
public class ColorAdapter<T extends Iterator2D, C extends Color>  implements Adapter {
	
	private Dimensions dimensions;
	
	private Schema schema;
	
	public ColorAdapter(final SchemaController schemaController) {
		schema = schemaController.getSchema();
		dimensions = schema.getDimensions();	
	}	
	
	public Color getObject(final Iterator2D it1) {
		
		FieldIterator it = (FieldIterator)it1;
		
		if (it.isThread()) {
			int a = schema.getThreadFragment(it.getThreadIndex()).getThreadID();
			return getColorForNum(a);	
		}
		
		if (it.isNode()) {
			int a = schema.getNode(it.getNodeIndex()).getFirstThreadID();
			return getColorForNum(a);
		}

		if (it.isCorner()) {
			CornerIndex cornerIndex = it.getCornerIndex();
			int a = schema.getCorner(cornerIndex);
			return getColorForNum(a);
		}
		
		return null;
	}

	@Override
	public FieldIterator getIterator() {
		return new FieldIterator(this.dimensions);
	}
	
	private Color getColorForNum(int threadID) {
		int colorID = this.schema.getColorsIDAdapter().getColorID(threadID);
		Color color = new ColorSchema().getColorByID(colorID);
		if (color == null){
			return Color.WHITE;
		} else {
			return color;
		}
	}

}
