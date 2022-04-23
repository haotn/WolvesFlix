package com.wolvesflix.dao;

import java.util.List;

public interface WolvesFlixDAO<E, K> {
	Boolean create(E entity);

	Boolean update(E entity);

	Boolean remove(K id);

	E findByID(K id);

	List<E> findAll();
}
