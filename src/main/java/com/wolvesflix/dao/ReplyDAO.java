package com.wolvesflix.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.wolvesflix.entity.Comment;
import com.wolvesflix.entity.Reply;
import com.wolvesflix.helper.JpaUtil;

public class ReplyDAO implements WolvesFlixDAO<Reply, Long> {
	private static EntityManager em;

	public ReplyDAO() {
		em = JpaUtil.getEntityManger();
	}

	/**
	 * Add Reply
	 */
	@Override
	public Boolean create(Reply entity) {
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
	 * Update Reply
	 */
	@Override
	public Boolean update(Reply entity) {
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

	/**
	 * Remove Reply
	 */
	@Override
	public Boolean remove(Long id) {
		try {
			em.getTransaction().begin();
			Reply entity = em.find(Reply.class, id);
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
	public Reply findByID(Long id) {
		try {
			Reply entity = em.find(Reply.class, id);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Reply> findAll() {
		try {
			TypedQuery<Reply> query = em.createNamedQuery("Reply.findAll", Reply.class);
			List<Reply> list = query.getResultList();
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
