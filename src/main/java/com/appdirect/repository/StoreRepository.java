package com.appdirect.repository;

import com.appdirect.model.Store;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface StoreRepository extends PagingAndSortingRepository<Store, Long> {
}
