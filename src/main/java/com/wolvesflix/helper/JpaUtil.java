package com.wolvesflix.helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	private static EntityManagerFactory factory;

	public static EntityManager getEntityManger() {
		if (factory == null || !factory.isOpen()) {
			factory = Persistence.createEntityManagerFactory("db");
		}
		return factory.createEntityManager();
	}

	protected void shutdown() {
		if (factory != null && factory.isOpen()) {
			factory.close();
		}
		factory = null;
	}
}
