package ru.anutakay.fenki.controller;
import java.awt.Color;

import ru.anutakay.fenki.graph.Iterator2D;
import ru.anutakay.fenki.model.Const.HDirection;
import ru.anutakay.fenki.model.NodeStoreDimensions;
import ru.anutakay.fenki.model.Schema;


public class ColorAdapter implements Adapter<Iterator2D, Object> {
	
	Color[] colors = {new Color(180, 50 , 200), new Color(0, 200, 180), new Color(0, 200, 180)};
	
	private NodeStoreDimensions nsd;
	private Schema mSchema;
	
	public ColorAdapter(Schema schema){
		mSchema = schema;
		nsd = mSchema.getDimensions();
	}

	@Override
	public Color getObject(Iterator2D it) {
		
		if(isThread(it)){
			int i = it.getI()/2;
			int j = it.getJ()/2;
			//System.out.println(j + " " + i + " " +mSchema.thread( j, i));
			int a = mSchema.getThreadFragment( j, i).getThreadID();
			if(a == -1){
				return Color.WHITE;
			}
			return getColorForNum(a);	
		}
		
		if(isNode(it)){
			int i = (it.getI()-1)/2;
			int j = ((it.getJ())-2)/4;
			int a = mSchema.getNode( i, j).getFirstThreadID();
			if(a == -1){
				return Color.WHITE;
			}
			return getColorForNum(a);
		}

		if(isCorner(it)){
			int i = (it.getI()-1)/2;
			HDirection left_right = HDirection.RIGHT;
			if(it.getJ()==0){
				left_right = HDirection.LEFT;
			}
			int a = mSchema.getCorner(i, left_right);
			if(a == -1){
				return Color.WHITE;
			}
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
				return 1+nsd.getColumnNumber()*2;
			}

			@Override
			public int getNumOfColumn() {
				// TODO Auto-generated method stub
				return 1+nsd.getThreadNumber()*2;
			}};
	}
	
	private boolean isThread(Iterator2D it){
		if(it.getI()%2 == 0){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean isSemiNode(Iterator2D it){
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
	
	private boolean isNode(Iterator2D it){
		if(this.isSemiNode(it)&&!this.isCorner(it)){
			return true;
		}
		return false;
	}
	
	private boolean isShortLeft(Iterator2D it){
		int a = it.getI()/2;
		if(it.getI()%2 == 0){
			return false;
		}
		
		if(nsd.firstCrossIsNode()){
			a=a+1;
		}
		a = a%2;
		if(a == 0 ){
			return false;
		}
		return true;
	}
	
	private boolean isCorner(Iterator2D it){
		if(isSemiNode(it)&&(it.getJ() == 0||it.getJ() == it.getNumOfColumn()-1)){
			return true;
		}else{
			return false;
		}
	}
	
	private Color getColorForNum(int num){
		return colors[num%colors.length];
	}

}
