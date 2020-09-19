package com.arjun.shoppingbackend.DaoImp;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.arjun.shoppingbackend.Dao.UserDao;
import com.arjun.shoppingbackend.Dto.Address;
import com.arjun.shoppingbackend.Dto.Cart;
import com.arjun.shoppingbackend.Dto.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addUser(User user) {
		try {
			sessionFactory.getCurrentSession().persist(user);
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return false;
	}

	@Override
	public boolean addAddress(Address address) {
		try {
			sessionFactory.getCurrentSession().persist(address);
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return false;
	}

	 

	@Override
	public User getByEmail(String email) {
		String selectQuery = "FROM User WHERE email = :email";
		try {

			return sessionFactory.getCurrentSession().createQuery(selectQuery, User.class).setParameter("email", email)
					.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Address getbillingAddress(User user) {
		String selectQuery = "FROM Address WHERE user = :user AND billing= :billing";
		try {

			return sessionFactory.getCurrentSession().createQuery(selectQuery, Address.class).setParameter("user", user)
					.setParameter("billing", true).getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Address> listSheppingAddress(User user) {
		String selectQuery = "FROM Address WHERE user = :user AND shipping = :shipping";
		try {

			return sessionFactory.getCurrentSession().createQuery(selectQuery, Address.class)
					.setParameter("user", user)
					.setParameter("shipping", 
					true).getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
