package com.groupeisi.backendadmin.repositories;

import com.groupeisi.backendadmin.entities.ProduitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProduitRepository extends JpaRepository<ProduitEntity, Integer> {
}
