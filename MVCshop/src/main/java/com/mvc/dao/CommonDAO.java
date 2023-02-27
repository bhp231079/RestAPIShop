package com.mvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.entity.Category;
import com.mvc.exception.ObjectExistException;
import com.mvc.exception.ObjectNotFoundException;


public abstract class CommonDAO<T> {

	public abstract List<T> get();

	public abstract T getbyId(int id) throws Exception;

	public abstract void add(T dto) throws ObjectExistException;

	public abstract void update(T dto,int id);

	public abstract void delete(int id);
	
	public abstract T getbyName(String str) ;

}
