package com.wolvesflix.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.wolvesflix.entity.Genre;
import com.wolvesflix.helper.JpaUtil;

public class GenreDAO implements WolvesFlixDAO<Genre, Long> {
	private EntityManager em;

	public GenreDAO() {
		em = JpaUtil.getEntityManger();
	}

	@Override
	public Boolean create(Genre entity) {
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
	public Boolean update(Genre entity) {
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
			Genre entity = em.find(Genre.class, id);
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
	public Genre findByID(Long id) {
		try {
			em.getTransaction().begin();
			Genre entity = em.find(Genre.class, id);
			em.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Genre> findAll() {
		try {
			em.getTransaction().begin();
			TypedQuery<Genre> query = em.createNamedQuery("Genre.findAll", Genre.class);
			List<Genre> list = query.getResultList();
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
