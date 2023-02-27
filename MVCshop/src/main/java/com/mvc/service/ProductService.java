package com.mvc.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.CommonDAO;
import com.mvc.entity.Product;
import com.mvc.entity.Type;
import com.mvc.exception.ObjectExistException;
import com.mvc.exception.ObjectNotFoundException;

@Service
@Transactional
public class ProductService extends CommonDAO<Product> {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Product> get() {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession.createQuery("Select c from Product c").getResultList();

	}

	@Override
	public Product getbyId(int id) throws ObjectNotFoundException {
		Session currentSession = sessionFactory.getCurrentSession();
		Product product = currentSession.get(Product.class, id);
		if (product == null) {
			throw new ObjectNotFoundException("not found id");
		}
		return product;

	}

	@Override
	public void add(Product dto) throws ObjectExistException {

		Session currentSession = sessionFactory.getCurrentSession();
		Product product = getbyName(dto.getName());
		if (product != null) {
			throw new ObjectExistException("object exits");
		}

		currentSession.persist(dto);

	}

	@Override
	public void update(Product dto, int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Product product = getbyId(id);
		if (product != null) {
			product.setName(dto.getName());
			product.setDescription(dto.getDescription());
			product.setPrice(dto.getPrice());
			product.setType(dto.getType());
			product.setQuantity(dto.getQuantity());
			product.setUrlImage(dto.getUrlImage());
		}
	
		currentSession.update(product);
 
	}

	@Override
	public void delete(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.delete(currentSession.get(Product.class, id));

	}

	@Override
	public Product getbyName(String str) {
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			Product product = currentSession.createQuery("Select p from Product p where p.name = ?1", Product.class)
					.setParameter(1, str).getSingleResult();
			return product;
		} catch (NoResultException e) {
			return null;
		}

	}

}
