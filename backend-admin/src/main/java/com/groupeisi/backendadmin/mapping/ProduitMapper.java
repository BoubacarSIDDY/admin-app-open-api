package com.groupeisi.backendadmin.mapping;

import com.groupeisi.backendadmin.entities.ProduitEntity;
import com.groupeisi.generated.model.ProduitDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ProduitMapper {
    ProduitDTO toProduitDTO (ProduitEntity produitEntity);
    ProduitEntity toProduitEntity (ProduitDTO produitDTO);
}
