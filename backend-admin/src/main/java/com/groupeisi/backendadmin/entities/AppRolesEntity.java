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
public class AppRolesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false, length = 100)
    private String name;
//    @ManyToMany(mappedBy = "appRolesEntities")
//    private List<AppUserEntity> appUserEntities;
}
