package com.wolvesflix.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.wolvesflix.entity.Comment;
import com.wolvesflix.helper.JpaUtil;

public class CommentDAO implements WolvesFlixDAO<Comment, Long> {
	private static EntityManager em;

	public CommentDAO() {
		em = JpaUtil.getEntityManger();
	}

	/**
	 * Add Comment
	 */
	@Override
	public Boolean create(Comment entity) {
		em.getTransaction().begin();
		try {
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
	 * Update Comment
	 */
	@Override
	public Boolean update(Comment entity) {
		em.getTransaction().begin();
		try {
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
	 * Remove Comment
	 */
	@Override
	public Boolean remove(Long id) {
		em.getTransaction().begin();
		try {
			Comment entity = em.find(Comment.class, id);
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
	public Comment findByID(Long id) {
		try {
			Comment entity = em.find(Comment.class, id);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Comment> findAll() {
		try {
			TypedQuery<Comment> query = em.createNamedQuery("Comment.findAll", Comment.class);
			List<Comment> list = query.getResultList();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Long getMinId() {
		try {
			TypedQuery<Long> query = em.createNamedQuery("Comment.findMinId", Long.class);
			return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Long getMaxId() {
		try {
			TypedQuery<Long> query = em.createNamedQuery("Comment.findMaxId", Long.class);
			return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Comment> getToTable(int currentPage, Long userId) {
		em.getTransaction().begin();
		try {
			TypedQuery<Comment> query = em.createNamedQuery("Comment.findByUserId", Comment.class);
			query.setParameter("userId", userId);
			query.setFirstResult((currentPage - 1) * 10);
			query.setMaxResults(10);
			List<Comment> list = query.getResultList();
			em.getTransaction().commit();
			return list;
		} catch (Exception e) {
			em.getTransaction().rollback();
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
