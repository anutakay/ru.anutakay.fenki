package ru.anutakay.fenki.view;

import ru.anutakay.fenki.controller.SchemaController;
import ru.anutakay.fenki.model.FieldIterator;
import ru.anutakay.fenki.model.Dimensions;
import ru.anutakay.fenki.model.Schema;

@SuppressWarnings({ "rawtypes"})
public class ColorAdapter<T extends Iterator2D>  implements Adapter {
	
	private Dimensions dimensions;
	
	private SchemaController schemaController;
	
	public ColorAdapter(final SchemaController schemaController) {
		this.schemaController = schemaController;
		dimensions = this.schemaController.getSchema().getDimensions();	
	}	
	
	public Integer getObject(final Iterator2D it1) {
		
		FieldIterator it = (FieldIterator)it1;
		Schema schema = this.schemaController.getSchema();
		
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
	
	private Integer getColorForNum(int threadID) {
		return Integer.valueOf(threadID);
	}

}
