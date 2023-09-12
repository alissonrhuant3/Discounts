package com.game.store.repository;

import org.springframework.data.repository.CrudRepository;

import com.game.store.models.Store;

public interface StoreRepository extends CrudRepository<Store, String> {
	Store findByCodigoGame(long codigoGame);
	Store deleteByCodigoGame(long codigoGame);
}
