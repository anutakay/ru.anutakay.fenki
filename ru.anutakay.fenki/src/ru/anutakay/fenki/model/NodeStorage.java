package ru.anutakay.fenki.model;
import java.util.ArrayList;

public abstract class NodeStorage<T> {
	
	private ArrayList<ArrayList<T>> mNodes;
	protected NodeStoreDimensions mDimensions;
	
	public NodeStorage(NodeStoreDimensions dimensions){
		
		mDimensions = dimensions;
	
		ArrayList<T> m;
		mNodes = new ArrayList<ArrayList<T>>();
		for(int j = 0; j < this.mDimensions.getColumnNumber()+1; j++){
			m = new ArrayList<T>();
			for(int i = 0; i < mDimensions.numberOfNodeInColumn(j); i++){
				//рандомные узлы
				m.add(createRandomObject(i,j));
			}
		mNodes.add(m);
		}
	}
	
	abstract protected T createRandomObject(int i, int j);
	
	public T getNode(NodeIndex ni){
		return mNodes.get(ni.j).get(ni.i);
	}
	
	public NodeStoreDimensions getDimensions(){
		return mDimensions;
	}

}
