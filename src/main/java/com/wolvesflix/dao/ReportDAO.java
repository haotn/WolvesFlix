package com.wolvesflix.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import com.wolvesflix.entity.ReportFavoriteByVideo;
import com.wolvesflix.entity.ReportShare;
import com.wolvesflix.entity.ReportUserLikeVideo;
import com.wolvesflix.helper.JpaUtil;

public class ReportDAO {
	private EntityManager em;

	public ReportDAO() {
		em = JpaUtil.getEntityManger();
	}

	public List<ReportFavoriteByVideo> reportFavoriteByVideo() {
		List<ReportFavoriteByVideo> list = new ArrayList<ReportFavoriteByVideo>();
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("ReportFavoriteByVideo");
		list = query.getResultList();
		return list;
	}

	public List<ReportUserLikeVideo> reportFavoriteByUser(Long videoId) {
		List<ReportUserLikeVideo> list = new ArrayList<ReportUserLikeVideo>();
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("ReportUserLikeVideo");
		query.setParameter("ID", videoId);
		list = query.getResultList();
		return list;
	}

	public List<ReportShare> reportShare(Long videoId) {
		List<ReportShare> list = new ArrayList<ReportShare>();
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("ReportShare");
		query.setParameter("ID", videoId);
		list = query.getResultList();
		return list;
	}

}
