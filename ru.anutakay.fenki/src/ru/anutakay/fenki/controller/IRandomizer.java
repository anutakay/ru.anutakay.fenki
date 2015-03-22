package ru.anutakay.fenki.controller;

public interface IRandomizer<T> {
	abstract public T createRandomObject(int i, int j);
}
