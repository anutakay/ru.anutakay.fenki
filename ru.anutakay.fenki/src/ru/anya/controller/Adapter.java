package ru.anya.controller;

import ru.anya.graph.Iterator;

public interface Adapter<I extends Iterator, O extends Object> {
	
	O getObject(I it);
	I getIterator();

}
































