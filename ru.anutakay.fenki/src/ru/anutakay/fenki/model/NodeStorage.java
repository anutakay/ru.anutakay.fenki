package ru.anutakay.fenki.model;
import java.util.ArrayList;

public abstract class NodeStorage {
	
	private ArrayList<ArrayList<Node>> nodes;
	
	protected NodeStoreDimensions dimensions;
	
	public NodeStorage(final NodeStoreDimensions dimensions){
		
		this.dimensions = dimensions;
	
		ArrayList<Node> m;
		nodes = new ArrayList<ArrayList<Node>>();
		for(int j = 0; j < this.dimensions.getColumnNumber()+1; j++){
			m = new ArrayList<Node>();
			for(int i = 0; i < FieldTemplate.numberOfNodeInColumn(this.dimensions, j); i++){
				m.add(new Node());
			}
		nodes.add(m);
		}
	}
	
	//сеттера нет, получаем узел, и заполняем как нужно
	public Node getNode(final NodeIndex nodeIndex){
		return nodes.get(nodeIndex.j).get(nodeIndex.i);
	}
	
	public NodeStoreDimensions getDimensions(){
		return this.dimensions;
	}

}
