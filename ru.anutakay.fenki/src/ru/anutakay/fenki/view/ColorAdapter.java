package ru.anutakay.fenki.view;

import java.awt.Color;

import ru.anutakay.fenki.controller.Adapter;
import ru.anutakay.fenki.controller.SchemaController;
import ru.anutakay.fenki.model.ColorSchema;
import ru.anutakay.fenki.model.Node.HDirection;
import ru.anutakay.fenki.model.NodeIndex;
import ru.anutakay.fenki.model.Dimensions;
import ru.anutakay.fenki.model.Schema;
import ru.anutakay.fenki.model.ThreadIndex;

public class ColorAdapter implements Adapter<Iterator2D, Object> {
	
	private Dimensions dimensions;
	
	private Schema schema;
	
	public ColorAdapter(final SchemaController schemaController) {
		schema = schemaController.getSchema();
		dimensions = schema.getDimensions();	}
	
	private ThreadIndex getThreadIndex(final Iterator2D it){
		int i = it.getI()/2;
		int j = it.getJ()/2;
		return new ThreadIndex(j, i);
	}
	
	private NodeIndex getNodeIndex(final Iterator2D it) {
		int i = (it.getI()-1)/2;
		int j = ((it.getJ())-2)/4;
		return new NodeIndex( j, i);
	}

	@Override
	public Color getObject(final Iterator2D it) {
		
		if (isThread(it)) {
			ThreadIndex threadIndex = getThreadIndex(it);
			int a = schema.getThreadFragment(threadIndex).getThreadID();
			return getColorForNum(a);	
		}
		
		if (isNode(it)) {
			NodeIndex nodeIndex = getNodeIndex(it);
			int a = schema.getNode(nodeIndex).getFirstThreadID();
			return getColorForNum(a);
		}

		if (isCorner(it)) {
			int i = (it.getI()-1)/2;
			HDirection left_right = HDirection.RIGHT;
			if(it.getJ()==0){
				left_right = HDirection.LEFT;
			}
			int a = schema.getCorner(i, left_right);
			return getColorForNum(a);
		}
		return null;
	}

	@Override
	public Iterator2D getIterator() {
		// TODO Auto-generated method stub
		return new Iterator2D(){

			@Override
			public int getNumOfString() {
				// TODO Auto-generated method stub
				return 1+dimensions.getColumnNumber()*2;
			}

			@Override
			public int getNumOfColumn() {
				// TODO Auto-generated method stub
				return 1+dimensions.getThreadNumber()*2;
			}};
	}
	
	private boolean isThread(Iterator2D it) {
		if(it.getI()%2 == 0){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean isSemiNode(Iterator2D it) {
		if(it.getI()%2 == 0){
			return false;
		}
		int a = it.getJ()/2+1;
		if(isShortLeft(it)){
			a = a+1;
		}
		if(a%2 == 0){
			return false;
		}
		return true;
	}
	
	private boolean isNode(Iterator2D it) {
		if(this.isSemiNode(it)&&!this.isCorner(it)) {
			return true;
		}
		return false;
	}
	
	private boolean isShortLeft(Iterator2D it) {
		int a = it.getI()/2;
		if(it.getI()%2 == 0){
			return false;
		}
		
		if(dimensions.firstCrossIsNode()) {
			a=a+1;
		}
		a = a%2;
		if(a == 0 ){
			return false;
		}
		return true;
	}
	
	private boolean isCorner(Iterator2D it) {
		if(isSemiNode(it)&&(it.getJ() == 0||it.getJ() == it.getNumOfColumn()-1)) {
			return true;
		}else{
			return false;
		}
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
