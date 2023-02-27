package com.mvc.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.CommonDAO;
import com.mvc.entity.Category;
import com.mvc.entity.Type;
import com.mvc.exception.ObjectExistException;
import com.mvc.exception.ObjectNotFoundException;

@Service
@Transactional
public class TypeService extends CommonDAO<Type> {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Type> get() {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession.createQuery("Select t from Type t").getResultList();

	}

	@Override
	public Type getbyId(int id) throws ObjectNotFoundException {
		Session currentSession = sessionFactory.getCurrentSession();
		Type Type = currentSession.get(Type.class, id);
		if (Type == null) {
			throw new ObjectNotFoundException("not found id");
		}
		return Type;

	}

	@Override
	public void add(Type dto) throws ObjectExistException {

		Session currentSession = sessionFactory.getCurrentSession();
		Type Type = getbyName(dto.getName());
		if (Type != null) {
			throw new ObjectExistException("object exits");
		}

		currentSession.persist(dto);

	}

	@Override
	public void update(Type dto,int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Type type = getbyId(id);
		if (type != null) {
			type.setName(dto.getName());
		}
		System.out.println(type.getName());
		currentSession.update(type);

	}

	@Override
	public void delete(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.delete(currentSession.get(Type.class, id));

	}

	@Override
	public Type getbyName(String str) {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Type Type = currentSession.createQuery("Select t from Type t where t.name = ?1", Type.class)
					.setParameter(1, str).getSingleResult();
			return Type;
		} catch (NoResultException e) {
			return null;
		}

	}

}
