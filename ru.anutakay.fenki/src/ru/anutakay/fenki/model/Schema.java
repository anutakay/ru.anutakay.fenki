package ru.anutakay.fenki.model;

import ru.anutakay.fenki.view.IThreadColorSchema;

public class Schema extends SimpleSchema {

	private static final int MIN_NUMBER_OF_COLUMN = 1;

	private static final int MIN_NUMBER_OF_THREAD = 2;
	
	private String schemaName = "unnamed";
	
	private IThreadColorSchema threadColorSchema;

	public Schema() {
		this(MIN_NUMBER_OF_THREAD, MIN_NUMBER_OF_COLUMN, true);
	}

	public Schema(	final int numberOfThread, 
					final int numberOfColumn, 
					final boolean firstCrossIsNode) {
		super(new Dimensions(numberOfThread, numberOfColumn, firstCrossIsNode));	
	}	
	
	public String getSchemaName() {
		return schemaName;
	}
	
	public IThreadColorSchema getThreadColorSchema() {
		return threadColorSchema;
	}

	public void setThreadColorSchema(IThreadColorSchema threadColorSchema) {
		this.threadColorSchema = threadColorSchema;
	}

}