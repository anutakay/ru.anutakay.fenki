package ru.anutakay.fenki.controller;

import ru.anutakay.fenki.view.Iterator;

public interface Adapter<I extends Iterator, O extends Object> {
	
	O getObject(I it);
	I getIterator();

}
































