package com.wolvesflix.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.wolvesflix.entity.Favorite;
import com.wolvesflix.helper.JpaUtil;

public class FavoriteDAO implements WolvesFlixDAO<Favorite, Long> {
	private static EntityManager em;

	public FavoriteDAO() {
		em = JpaUtil.getEntityManger();
	}

	@Override
	public Boolean create(Favorite entity) {
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
	public Boolean update(Favorite entity) {
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
			Favorite entity = em.find(Favorite.class, id);
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
	public Favorite findByID(Long id) {
		try {
			Favorite entity = em.find(Favorite.class, id);
			em.refresh(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Favorite> findAll() {
		try {
			TypedQuery<Favorite> query = em.createNamedQuery("Favorite.findAll", Favorite.class);
			List<Favorite> list = query.getResultList();
			list.forEach((item) -> {
				em.refresh(item);
			});
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Favorite findByUserIdAndVideoId(Long userId, Long videoId) {
		Favorite entity = null;
		try {
			TypedQuery<Favorite> query = em.createNamedQuery("Favorite.findByUserIdAndUserId", Favorite.class);
			query.setParameter("userId", userId);
			query.setParameter("videoId", videoId);
			entity = query.getSingleResult();
			em.refresh(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	public Long countByMonth() {
		TypedQuery<Long> query = em.createNamedQuery("Favorite.countByMonth", Long.class);
		Long count = query.getSingleResult();
		return count;
	}

//	@Override
//	protected void finalize() throws Throwable {
//		em.close();
//		super.finalize();
//	}

}
