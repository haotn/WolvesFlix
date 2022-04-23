package com.wolvesflix.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.wolvesflix.entity.Share;
import com.wolvesflix.helper.JpaUtil;

public class ShareDAO implements WolvesFlixDAO<Share, Long> {
	private static EntityManager em;

	public ShareDAO() {
		em = JpaUtil.getEntityManger();
	}

	@Override
	public Boolean create(Share entity) {
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
	public Boolean update(Share entity) {
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
			Share entity = em.find(Share.class, id);
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
	public Share findByID(Long id) {
		try {
			Share entity = em.find(Share.class, id);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Share> findAll() {
		try {
			TypedQuery<Share> query = em.createNamedQuery("Share.findAll", Share.class);
			List<Share> list = query.getResultList();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

//	@Override
//	protected void finalize() throws Throwable {
//		em.close();
//		super.finalize();
//	}

}
