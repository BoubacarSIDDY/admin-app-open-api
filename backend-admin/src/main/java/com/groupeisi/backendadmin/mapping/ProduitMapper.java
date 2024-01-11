package com.groupeisi.backendadmin.mapping;

import com.groupeisi.backendadmin.entities.ProduitEntity;
import com.groupeisi.generated.model.ProduitDTO;
import com.groupeisi.generated.model.ProduitsResultListDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProduitMapper {
    ProduitDTO toProduitDTO (ProduitEntity produitEntity);
    ProduitEntity toProduitEntity (ProduitDTO produitDTO);

    ProduitsResultListDTO toProduitsResultListDto (ProduitEntity produitEntity);
    List<ProduitsResultListDTO> toProduitsResultListDtoList (List<ProduitEntity> produitEntities);
}
