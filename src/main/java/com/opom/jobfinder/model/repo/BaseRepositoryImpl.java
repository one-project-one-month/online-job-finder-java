package com.opom.jobfinder.model.repo;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

	@Override
	public <DTO> List<DTO> search(Function<CriteriaBuilder, CriteriaQuery<DTO>> queryFunc) {
		var cq = queryFunc.apply(entityManager.getCriteriaBuilder());
		var query = entityManager.createQuery(cq);
		return query.getResultList();
	}
}
