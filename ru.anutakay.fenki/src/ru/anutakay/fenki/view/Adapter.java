package ru.anutakay.fenki.view;

public interface Adapter<I extends Iterator2D, O extends Object> {

	I getIterator();
	O getObject(I it);

}
































