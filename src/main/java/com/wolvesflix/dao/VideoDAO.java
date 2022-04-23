package com.wolvesflix.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.wolvesflix.entity.Comment;
import com.wolvesflix.entity.Favorite;
import com.wolvesflix.entity.Share;
import com.wolvesflix.entity.Video;
import com.wolvesflix.entity.VideoGenre;
import com.wolvesflix.entity.View;
import com.wolvesflix.helper.FormValidator;
import com.wolvesflix.helper.JpaUtil;

public class VideoDAO implements WolvesFlixDAO<Video, Long> {

	private static EntityManager em;

	public VideoDAO() {
		em = JpaUtil.getEntityManger();
	}

	/**
	 * Add Video to Database
	 *
	 */
	@Override
	public Boolean create(Video entity) {
		try {
			em.getTransaction().begin();
			// Add Video
			em.persist(entity);
			// Commit Transaction and return true
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Update Video to Database
	 */
	@Override
	public Boolean update(Video entity) {
		try {
			em.getTransaction().begin();
			// Update Video
			em.merge(entity);
			// Commit Transaction and return true
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Remove Video from Database
	 */
	@Override
	public Boolean remove(Long id) {
		em.getTransaction().begin();
		try {
			// Find Video by id (Primary key)
			Video entity = em.find(Video.class, id);
			// Remove Video
			em.remove(entity);
			// Commit transaction and return true
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Find Video by ID
	 */
	@Override
	public Video findByID(Long id) {
		try {
			Video entity = em.find(Video.class, id);
			// Return Video find by ID (Primary key)
			em.refresh(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			// Return null
			return null;
		}
	}

	@Override
	public List<Video> findAll() {
		try {
// Create query
			TypedQuery<Video> query = em.createNamedQuery("Video.findAll", Video.class);
			List<Video> list = query.getResultList();
			list.forEach((item) -> {
				em.refresh(item);
			});
			// Return list result
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			// Return null
			return null;
		}
	}

	public Map<Integer, List<Video>> findAllOrderBy(int od, int currentPage, String keyword) {
		Map<Integer, List<Video>> map = new HashMap<Integer, List<Video>>();
		try {
			TypedQuery<Video> query;

			if (od == 1) {
				query = em.createNamedQuery("Video.findByKeywordAndSortById", Video.class);
			} else if (od == 2) {
				query = em.createNamedQuery("Video.findByKeywordAndSortByTitle", Video.class);
			} else if (od == 3) {
				query = em.createNamedQuery("Video.findByKeywordAndSortByAddDate", Video.class);
			} else {
				query = em.createNamedQuery("Video.findByKeywordAndSortByViews", Video.class);
			}
			query.setParameter("keyword", "%" + keyword + "%");
			List<Video> list = new ArrayList<Video>();
			list = query.getResultList();
			int size = list.size();
			query.setFirstResult((currentPage - 1) * 10);
			query.setMaxResults(10);
			list = query.getResultList();
			list.forEach((item) -> {
				em.refresh(item);
			});
			map.put(size, list);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Video findByYoutubeId(String youtubeId) {
		try {
			TypedQuery<Video> query = em.createNamedQuery("Video.findByYoutubeId", Video.class);
			query.setParameter("youtubeId", youtubeId);
			Video entity = query.getSingleResult();
			em.refresh(entity);
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<Video> topViews() {
		try {
			Query query = em.createNamedQuery("Video.topViews");
			List<Video> resultList = query.getResultList();
			resultList.forEach((item) -> {
				em.refresh(item);
			});
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Video> getToShow(int currentPage, String keyword) {
		try {
			TypedQuery<Video> query;
			if (FormValidator.isTextNotEmpty(keyword)) {
				query = em.createNamedQuery("Video.findToShowByKeyword", Video.class);
				query.setParameter("keyword", "%" + keyword + "%");
			} else {
				query = em.createNamedQuery("Video.findToShow", Video.class);
			}
			query.setFirstResult((currentPage - 1) * 6);
			query.setMaxResults(6);
			List<Video> list = query.getResultList();
			list.forEach((item) -> {
				em.refresh(item);
			});
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Video> getAllActive() {
		List<Video> list = new ArrayList<Video>();
		TypedQuery<Video> query = em.createNamedQuery("Video.findToShow", Video.class);
		list = query.getResultList();
		list.forEach((item) -> {
			em.refresh(item);
		});
		return list;
	}

	public List<Video> getActiveByKeyword(String keyword) {
		List<Video> list = new ArrayList<Video>();
		TypedQuery<Video> query = em.createNamedQuery("Video.findToShowByKeyword", Video.class);
		query.setParameter("keyword", "%" + keyword + "%");
		list = query.getResultList();
		list.forEach((item) -> {
			em.refresh(item);
		});
		return list;
	}

	public List<Video> getToTable(int currentPage) {
		try {
			TypedQuery<Video> query = em.createNamedQuery("Video.findAll", Video.class);
			query.setFirstResult((currentPage - 1) * 10);
			query.setMaxResults(10);
			List<Video> list = query.getResultList();
			list.forEach((item) -> {
				em.refresh(item);
			});
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Long getMinId() {
		try {
			TypedQuery<Long> query = em.createNamedQuery("Video.findMinId", Long.class);
			Long value = query.getSingleResult();
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Long getMaxId() {
		try {
			TypedQuery<Long> query = em.createNamedQuery("Video.findMaxId", Long.class);
			Long value = query.getSingleResult();
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Long countAddByMonth() {
		TypedQuery<Long> query = em.createNamedQuery("Video.countAddByMonth", Long.class);
		Long count = query.getSingleResult();
		return count;
	}

//	@Override
//	protected void finalize() throws Throwable {
//		em.close();
//		super.finalize();
//	}

}
