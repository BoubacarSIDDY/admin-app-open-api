package com.groupeisi.backendadmin.repositories;

import com.groupeisi.backendadmin.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity, Integer> {
}
