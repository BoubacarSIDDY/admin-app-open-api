package com.groupeisi.backendadmin.repositories;

import com.groupeisi.backendadmin.entities.AppRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppRolesRepository extends JpaRepository<AppRolesEntity, Integer> {
}
