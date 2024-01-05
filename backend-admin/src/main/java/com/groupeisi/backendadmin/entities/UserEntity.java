package com.groupeisi.backendadmin.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 100)
    private String nom;
    @Column(nullable = false, length = 200)
    private String prenom;
    @Column(nullable = false, length = 200)
    private String email;
    @Column(nullable = false, length = 255)
    private String password;
    private int etat;
    @OneToMany(mappedBy = "userEntity")
    private List<ProduitEntity> produitEntities;
//    @ManyToMany(mappedBy = "appUserEntities")
//    private List<RoleEntity> appRolesEntities;
}
