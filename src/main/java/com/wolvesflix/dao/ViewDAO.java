package com.wolvesflix.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.wolvesflix.entity.View;
import com.wolvesflix.helper.JpaUtil;

public class ViewDAO implements WolvesFlixDAO<View, Long> {
	private static EntityManager em;

	public ViewDAO() {
		em = JpaUtil.getEntityManger();
	}

	@Override
	public Boolean create(View entity) {
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

	@Override
	public Boolean update(View entity) {
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean remove(Long id) {
		try {
			em.getTransaction().begin();
			View entity = em.find(View.class, id);
			em.remove(entity);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public View findByID(Long id) {
		try {
			View entity = em.find(View.class, id);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<View> findAll() {
		try {
			TypedQuery<View> query = em.createNamedQuery("View.findAll", View.class);
			List<View> list = query.getResultList();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Long viewByMonth() {
		TypedQuery<Long> query = em.createNamedQuery("View.countByMonth", Long.class);
		Long count = query.getSingleResult();
		return count;
	}

//	@Override
//	protected void finalize() throws Throwable {
//		em.close();
//		super.finalize();
//	}
}
