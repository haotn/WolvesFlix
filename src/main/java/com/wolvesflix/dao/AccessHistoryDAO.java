package com.wolvesflix.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.wolvesflix.entity.AccessHistory;
import com.wolvesflix.helper.JpaUtil;

public class AccessHistoryDAO implements WolvesFlixDAO<AccessHistory, Long> {
	private EntityManager em;
	private EntityTransaction transaction;

	public AccessHistoryDAO() {
		em = JpaUtil.getEntityManger();
		transaction = em.getTransaction();
	}

	@Override
	public Boolean create(AccessHistory entity) {
		transaction.begin();
		try {
			em.persist(entity);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean update(AccessHistory entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean remove(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccessHistory findByID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccessHistory> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
