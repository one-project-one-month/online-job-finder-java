package com.opom.jobfinder.model.repo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.function.Function;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {

}
