package ru.anutakay.fenki.view;


public interface Adapter<I extends Iterator, O extends Object> {
	
	O getObject(I it);
	I getIterator();

}
































