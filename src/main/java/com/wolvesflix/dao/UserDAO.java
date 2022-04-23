package com.wolvesflix.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.wolvesflix.entity.User;
import com.wolvesflix.helper.JpaUtil;
import com.wolvesflix.helper.XScope;

public class UserDAO implements WolvesFlixDAO<User, Long> {
	private static EntityManager em;

	public UserDAO() {
		em = JpaUtil.getEntityManger();
	}

	/**
	 * Create User
	 */
	@Override
	public Boolean create(User entity) {
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Update User
	 */
	@Override
	public Boolean update(User entity) {
		em.getTransaction().begin();
		try {
			User user = em.find(User.class, entity.getId());
			user = entity;
			em.merge(user);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Remove User
	 */
	@Override
	public Boolean remove(Long id) {
		try {
			em.getTransaction().begin();
			User found = em.find(User.class, id);
			em.remove(found);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User findByID(Long id) {
		try {
			User entity = em.find(User.class, id);
			em.refresh(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<User> findAll() {
		try {
			User user = User.parseUser(XScope.getSesion("user"));
			TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
			query.setParameter("currentUserId", user.getId());
			List<User> list = query.getResultList();
			list.forEach((item) -> {
				em.refresh(item);
			});
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * CheckLogin
	 */
	public User checkLogin(String username, String password) {
		try {
			String jpql = "SELECT o FROM User o WHERE o.username=:usn AND o.password=:pass AND o.accept=true";
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			query.setParameter("usn", username);
			query.setParameter("pass", password);
			User entity = query.getSingleResult();
			em.refresh(entity);
			return entity;
		} catch (Exception e) {
			return null;
		}
	}

	public Long getMinId() {
		try {
			TypedQuery<Long> query = em.createNamedQuery("User.findMinId", Long.class);
			return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Long getMaxId() {
		try {
			TypedQuery<Long> query = em.createNamedQuery("User.findMaxId", Long.class);
			return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<User> getToTable(int currentPage) {
		User user = User.parseUser(XScope.getSesion("user"));
		try {
			TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
			query.setParameter("currentUserId", user.getId());
			query.setFirstResult((currentPage - 1) * 10);
			query.setMaxResults(10);
			List<User> list = query.getResultList();
			list.forEach((item) -> {
				em.refresh(item);
			});
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public User checkRemember(String cookieId) {
		User user = null;
		try {
			TypedQuery<User> query = em.createNamedQuery("User.findRemember", User.class);
			query.setParameter("cid", cookieId);
			user = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public Long countByMonth() {
		TypedQuery<Long> query = em.createNamedQuery("User.countByMonth", Long.class);
		Long count = query.getSingleResult();
		return count;
	}

	public Map<Integer, List<User>> findAllOrderBy(int orderId, int page, String keyword) {
		User user = User.parseUser(XScope.getSesion("user"));
		Map<Integer, List<User>> map = new HashMap<Integer, List<User>>();
		TypedQuery<User> query;
		if (orderId == 1) {
			query = em.createNamedQuery("User.findByKeywordAndSortById", User.class);
		} else if (orderId == 2) {
			query = em.createNamedQuery("User.findByKeywordAndSortUsername", User.class);
		} else {
			query = em.createNamedQuery("User.findByKeywordAndSortByRegisterDate", User.class);
		}
		query.setParameter("currentUserId", user.getId());
		;
		query.setParameter("keyword", "%" + keyword + "%");
		List<User> list = new ArrayList<User>();
		list = query.getResultList();
		int size = list.size();
		query.setFirstResult((page - 1) * 10);
		query.setMaxResults(10);
		list = query.getResultList();
		list.forEach((item) -> {
			em.refresh(item);
		});
		map.put(size, list);
		return map;
	}
//	@Override
//	protected void finalize() throws Throwable {
//		em.close();
//		super.finalize();
//	}
}
