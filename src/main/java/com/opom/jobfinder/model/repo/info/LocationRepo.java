package com.opom.jobfinder.model.repo.info;

import com.opom.jobfinder.model.entity.info.Location;
import com.opom.jobfinder.model.repo.BaseRepository;

import java.util.Optional;

public interface LocationRepo extends BaseRepository<Location, Integer> {
    Optional<Location> findOneById(int id);
    Optional<Location> findByName(String name);
}
