package com.fe.service;

import java.util.List;

public interface CommonService<T> {

	public List<T> get(String token);

	public T getbyId(int id,String token);

	public void add(T dto,String token);

	public void update(T dto, int id,String token);

	public void delete(int id,String token);

	public T getbyName(String str,String token);

}
