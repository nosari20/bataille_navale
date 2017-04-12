package com.cad.repository.dao;

public interface Dao<T> {
	public T create(T object);	
	public T update(T object);
	public T delete(T object);
	public T find(T object);
}
