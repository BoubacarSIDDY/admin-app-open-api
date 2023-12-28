package com.groupeisi.backendadmin.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
public class ProduitEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 150)
    private String name;
    private double qtyStock;
    @ManyToOne
    private AppUserEntity appUserEntity;
}
