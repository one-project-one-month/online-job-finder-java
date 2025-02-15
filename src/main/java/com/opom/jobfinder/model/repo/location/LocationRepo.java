package com.opom.jobfinder.model.repo.location;

import com.opom.jobfinder.model.entity.location.Location;
import com.opom.jobfinder.model.repo.BaseRepository;
import com.opom.jobfinder.model.repo.BaseRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepo extends BaseRepository<Location, String> {

}
