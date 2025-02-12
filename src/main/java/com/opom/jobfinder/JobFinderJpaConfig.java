package com.opom.jobfinder;

import com.opom.jobfinder.model.repo.BaseRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class JobFinderJpaConfig {
}
