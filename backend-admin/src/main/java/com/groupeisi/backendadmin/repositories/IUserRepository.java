package com.groupeisi.backendadmin.repositories;

import com.groupeisi.backendadmin.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);
}
