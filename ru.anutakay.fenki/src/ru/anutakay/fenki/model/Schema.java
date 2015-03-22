package ru.anutakay.fenki.model;

public class Schema extends SimpleSchema {

	private static final int MIN_NUMBER_OF_COLUMN = 1;

	private static final int MIN_NUMBER_OF_THREAD = 2;
	
	private String schemaName = "unnamed";
	
	private ColorGroupSchema colorGroupSchema;

	public Schema() {
		this(MIN_NUMBER_OF_THREAD, MIN_NUMBER_OF_COLUMN, true);
	}

	public Schema(	final int numberOfThread, 
					final int numberOfColumn, 
					final boolean firstCrossIsNode) {
		super(new Dimensions(numberOfThread, numberOfColumn, firstCrossIsNode));	
		colorGroupSchema = new ColorGroupSchema();
	}	
	
	public String getSchemaName() {
		return schemaName;
	}
	
	public ColorGroupSchema getColorsIDAdapter() {
		return this.colorGroupSchema;
	}
	

}