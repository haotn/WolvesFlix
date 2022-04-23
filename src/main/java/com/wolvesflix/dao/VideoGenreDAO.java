package com.wolvesflix.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.wolvesflix.entity.Genre;
import com.wolvesflix.entity.VideoGenre;
import com.wolvesflix.helper.JpaUtil;

public class VideoGenreDAO implements WolvesFlixDAO<VideoGenre, Long> {
	private static EntityManager em;

	public VideoGenreDAO() {
		em = JpaUtil.getEntityManger();
	}

	@Override
	public Boolean create(VideoGenre entity) {
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
	public Boolean update(VideoGenre entity) {
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
			VideoGenre entity = em.find(VideoGenre.class, id);
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
	public VideoGenre findByID(Long id) {
		try {
			VideoGenre entity = em.find(VideoGenre.class, id);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<VideoGenre> findAll() {
		try {
			TypedQuery<VideoGenre> query = em.createNamedQuery("VideoGenre.findAll", VideoGenre.class);
			List<VideoGenre> list = query.getResultList();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public VideoGenre findByVideoIdAndGenreId(Long videoId, Long genreId) {
		try {
			TypedQuery<VideoGenre> query = em.createNamedQuery("VideoGenre.findByVideoIdAndGenreId", VideoGenre.class);
			query.setParameter("videoId", videoId);
			query.setParameter("genreId", genreId);
			return query.getSingleResult();
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
