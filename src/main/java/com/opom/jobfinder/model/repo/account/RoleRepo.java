package com.opom.jobfinder.model.repo.account;

import com.opom.jobfinder.model.entity.account.Role;
import com.opom.jobfinder.model.repo.BaseRepository;

import java.util.Optional;

public interface RoleRepo extends BaseRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
