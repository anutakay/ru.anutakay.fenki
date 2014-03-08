package ru.fenki.view;

import java.awt.Color;

import ru.fenki.model.NodeIndex;
import ru.fenki.model.Schema;

public class SchemaAdapter extends SimpleSchemaAdapter {

	private Schema mSchema;
	
	
	private SchemaAdapter(int a){
		super(a);
	}
	
	public SchemaAdapter(Schema schema, int a){
		this(a);
		mSchema = schema;
	}
	
	@Override
	public Rhomb getRhomb(SimpleIterator it) {
		Rhomb r = super.getRhomb(it);
		r.clear();
		int i = it.i;
		int j = it.j;
		
		i=i/2;
		j=(j-1)/2;
		
		if(mSchema.getDimensions().isNode(it.i, it.j-1)){
			r.setColor(colors[mSchema.node(i, j).getColor()%3]);
			return r;
		}
		
		if(mSchema.getDimensions().isEmpty(it.i, it.j-1)){
			r.setFill(false);
			return r;
		}
		
		i=it.i/2;
		j=it.j-1;
		if(mSchema.getDimensions().isThread(it.i, it.j-1)){
			r.setColor(colors[mSchema.thread(j, i)%3]);
			return r;
		}
		
		
		i=it.i/2;
		j=(it.j)/2-1;
		if(mSchema.getDimensions().isCorner(it.i, it.j-1)){
			r.setColor(colors[mSchema.corner(j, i)%3]);
			
			mSchema.corner(j, i);
		}
		return r;
	}

	Color[] colors = { new Color(255, 215, 0), new Color(50, 205, 50),
			new Color(0, 206, 209) };
}
