package com.arjun.shoppingbackend.DaoImp;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.arjun.shoppingbackend.Dao.ProductDao;
import com.arjun.shoppingbackend.Dto.Product;

@Repository("productDao")
@Transactional
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * get list of product
	 */

	@Override
	public List<Product> list() {

		return sessionFactory.getCurrentSession().createQuery("FROM Product", Product.class).getResultList();
	}

	/*
	 * get single product
	 */
	@Override
	public Product get(int ProductId) {
		try {
			return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(ProductId));

		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return null;
	}

	/*
	 * insert(add) product from database
	 */
	@Override
	public boolean add(Product product) {
		try {
			sessionFactory.getCurrentSession().persist(product);
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return false;
	}

	/*
	 * update product from database
	 */
	@Override
	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			;
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return false;
	}

	@Override
	public boolean delete(Product product) {
		try {
			product.setActive(false);
			return this.update(product);

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return false;
	}

	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProducts= "FROM Product WHERE active = :active";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProducts, Product.class)
				.setParameter("active", true).getResultList();
	}

	@Override
	public List<Product> ActiveProductsByCategory(int categoryId) {
		String selectActiveProductsByCategory = "FROM Product WHERE active = :active AND categoryId = :categoryId ";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProductsByCategory, Product.class)
				.setParameter("categoryId", categoryId).setParameter("active", true).getResultList();
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		return sessionFactory.getCurrentSession()
				.createQuery("FROM Product WHERE active= :active ORDER BY id", Product.class).setFirstResult(0)
				.setMaxResults(count).setParameter("active", true).getResultList();
	}

}
