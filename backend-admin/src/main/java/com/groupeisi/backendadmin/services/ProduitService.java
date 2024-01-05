package com.groupeisi.backendadmin.services;

import com.groupeisi.backendadmin.exceptions.EntityNotFoundException;
import com.groupeisi.backendadmin.exceptions.RequestException;
import com.groupeisi.backendadmin.mapping.ProduitMapper;
import com.groupeisi.backendadmin.repositories.IProduitRepository;
import com.groupeisi.generated.model.ProduitDTO;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
public class ProduitService {
    private IProduitRepository iProduitRepository;
    private ProduitMapper produitMapper;
    private MessageSource messageSource;

    public ProduitService(IProduitRepository iProduitRepository, ProduitMapper produitMapper, MessageSource messageSource) {
        this.iProduitRepository = iProduitRepository;
        this.produitMapper = produitMapper;
        this.messageSource = messageSource;
    }

    @Transactional(readOnly = true)
    public List<ProduitDTO> getAllProduits(){
        return iProduitRepository.findAll().stream().map(produitMapper::toProduitDTO).toList();
    }

    @Transactional(readOnly = true)
    public ProduitDTO getProduit(int id){
        return produitMapper.toProduitDTO(iProduitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("produit.notfound", new Object[]{id}, Locale.getDefault()))));
    }

    @Transactional
    public ProduitDTO save(ProduitDTO produitDTO){
        return produitMapper.toProduitDTO(iProduitRepository.save(produitMapper.toProduitEntity(produitDTO)));
    }

    @Transactional
    public ProduitDTO update(int id, ProduitDTO produitDTO){
        return iProduitRepository.findById(id).map(entity -> {
            produitDTO.setId(id);
            return produitMapper.toProduitDTO(iProduitRepository.save(produitMapper.toProduitEntity(produitDTO)));
        }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("produit.notfound", new Object[]{id}, Locale.getDefault())));
    }
    @Transactional
    public void delete(int id){
        try{
            iProduitRepository.deleteById(id);
        } catch (Exception e){
            throw new RequestException(messageSource.getMessage("produit.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}
