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
import com.mvc.exception.ObjectExistException;
import com.mvc.exception.ObjectNotFoundException;

@Service
@Transactional
public class CategoryService extends CommonDAO<Category> {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Category> get() {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession.createQuery("Select c from Category c").getResultList();

	}

	@Override
	public Category getbyId(int id) throws ObjectNotFoundException {
		Session currentSession = sessionFactory.getCurrentSession();
		Category category = currentSession.get(Category.class, id);
		if (category == null) {
			throw new ObjectNotFoundException("not found id");
		}
		return category;

	}

	@Override
	public void add(Category dto) throws ObjectExistException {

		Session currentSession = sessionFactory.getCurrentSession();
		Category category = getbyName(dto.getName());
		if (category != null) {
			throw new ObjectExistException("object exits");
		}

		currentSession.persist(dto);

	}

	@Override
	public void update(Category dto, int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Category category = getbyId(id);
		if (category != null) {
			category.setName(dto.getName());
		}
	
		
		currentSession.update(category);

	}

	@Override
	public void delete(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.delete(currentSession.get(Category.class, id));

	}

	@Override
	public Category getbyName(String str) {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Category category = currentSession.createQuery("Select c from Category c where c.name = ?1", Category.class)
					.setParameter(1, str).getSingleResult();
			return category;
		} catch (NoResultException e) {
			return null;
		}

	}

}
