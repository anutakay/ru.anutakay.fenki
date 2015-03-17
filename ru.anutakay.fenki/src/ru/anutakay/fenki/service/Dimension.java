package ru.anutakay.fenki.service;

public class Dimension {
	
	private int mStringNumber;
	private int mColumnNumber;
	
	public Dimension(int string, int column){
		mStringNumber = string;
		mColumnNumber = column;
	}
	
	public int getColumnNumber(){
		return mColumnNumber;
	}
	
	public int getStringNumber(){
		return mStringNumber;
	}

}
