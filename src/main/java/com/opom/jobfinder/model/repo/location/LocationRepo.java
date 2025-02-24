package com.opom.jobfinder.model.repo.location;

import com.opom.jobfinder.model.entity.info.Location;
import com.opom.jobfinder.model.repo.BaseRepository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepo extends BaseRepository<Location, Integer> {
        Optional<Location> findByName(String name); 
}
