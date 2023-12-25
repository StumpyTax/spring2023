package com.spring2023.auth.app.repositories;

import com.spring2023.auth.app.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;

public interface RolesRepository extends CrudRepository<RoleEntity,Long> {
}
