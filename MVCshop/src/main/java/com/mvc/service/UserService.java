package com.mvc.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.CommonDAO;
import com.mvc.entity.User;
import com.mvc.exception.ObjectExistException;
import com.mvc.exception.ObjectNotFoundException;

@Service
@Transactional
public class UserService extends CommonDAO<User> {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<User> get() {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession.createQuery("Select u from User u").getResultList();

	}

	@Override
	public User getbyId(int id) throws ObjectNotFoundException {
		Session currentSession = sessionFactory.getCurrentSession();
		User User = currentSession.get(User.class, id);
		if (User == null) {
			throw new ObjectNotFoundException("not found id");
		}
		return User;

	}

	@Override
	public void add(User dto) throws ObjectExistException {

		Session currentSession = sessionFactory.getCurrentSession();
		User User = getbyName(dto.getLastName());
		if (User != null) {
			throw new ObjectExistException("object exits");
		}

		currentSession.persist(dto);

	}

	@Override
	public void update(User dto, int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		User User = getbyId(id);
//		if (User != null) {
//			User.setName(dto.get());
//		}

		currentSession.update(User);

	}

	@Override
	public void delete(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.delete(currentSession.get(User.class, id));

	}

	@Override
	public User getbyName(String str) {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			User User = currentSession.createQuery("Select u from User u where u.lastName = ?1", User.class)
					.setParameter(1, str).getSingleResult();
			return User;
		} catch (NoResultException e) {
			return null;
		}

	}

	public User getbyEmail(String str) {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			User User = currentSession.createQuery("Select u from User u where u.email = ?1", User.class)
					.setParameter(1, str).getSingleResult();
			return User;
		} catch (NoResultException e) {
			return null;
		}

	}

}
