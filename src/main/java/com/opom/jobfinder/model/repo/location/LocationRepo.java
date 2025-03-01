package com.opom.jobfinder.model.repo.location;

import com.opom.jobfinder.model.entity.info.Location;
import com.opom.jobfinder.model.repo.BaseRepository;
import com.opom.jobfinder.model.repo.BaseRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepo extends BaseRepository<Location, Integer> {

    Optional<Location> findByName(String name);
}
