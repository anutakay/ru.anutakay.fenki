package ru.anutakay.fenki.model;

public class Schema extends SimpleSchema {

	private static final int MIN_NUMBER_OF_COLUMN = 1;

	private static final int MIN_NUMBER_OF_THREAD = 2;
	
	private String schemaName = "unnamed";
	
	private ColorGroupSchema colorsIDAdapter;

	public Schema() {
		this(MIN_NUMBER_OF_THREAD, MIN_NUMBER_OF_COLUMN, true);
	}

	public Schema(	final int numberOfThread, 
					final int numberOfColumn, 
					final boolean firstCrossIsNode) {
		super(new Dimensions(numberOfThread, numberOfColumn, firstCrossIsNode));	
		colorsIDAdapter = new ColorGroupSchema();
	}

	
	public Dimensions getDimensions(){
		return dimensions;
	}	
	
	/*public SimpleSchema getStorage(){
		return this;
	}*/
	
	public Node getNode(final int i, final int j) {
		return getNode(new NodeIndex(j, i));
	}
	
	
	public ThreadFragment getThreadFragment(final int i, final int j) {
		return getThreadFragment(new ThreadIndex(i, j));
	}
	
	public String getSchemaName() {
		return schemaName;
	}
	
	public ColorGroupSchema getColorsIDAdapter(){
		return this.colorsIDAdapter;
	}
	

}