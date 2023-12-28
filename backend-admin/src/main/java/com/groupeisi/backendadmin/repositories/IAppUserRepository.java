package com.groupeisi.backendadmin.repositories;

import com.groupeisi.backendadmin.entities.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppUserRepository extends JpaRepository<AppUserEntity, Integer> {
    AppUserEntity findByEmail(String email);
}
