package com.groupeisi.backendadmin.web;

import com.groupeisi.backendadmin.services.ProduitService;
import com.groupeisi.generated.api.ProduitApi;
import com.groupeisi.generated.model.ProduitDTO;
import com.groupeisi.generated.model.ProduitsResultListDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@NoArgsConstructor @AllArgsConstructor
public class ProduitsController implements ProduitApi {
    @Autowired
    private ProduitService produitService;

    @Override
    public ResponseEntity<ProduitDTO> getProduit(Integer idProduit) throws Exception {
        return ResponseEntity.ok(produitService.getProduit(idProduit));
    }

    @Override
    public ResponseEntity<ProduitsResultListDTO> getAllProducts(Integer currentPage, Integer sizePage) throws Exception {
        return new ResponseEntity<>(produitService.getAllProducts(currentPage, sizePage), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProduitDTO> createProduit(ProduitDTO produitDTO) throws Exception {
        return new ResponseEntity<>(produitService.save(produitDTO), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProduitDTO> updateProduit(Integer id, ProduitDTO produitDTO) throws Exception {
        return new ResponseEntity<>(produitService.update(id, produitDTO), HttpStatus.OK);
    }
}