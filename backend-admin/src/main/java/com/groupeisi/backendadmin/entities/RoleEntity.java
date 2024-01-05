package com.groupeisi.backendadmin.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false, length = 100)
    private String name;
//    @ManyToMany(mappedBy = "appRolesEntities")
//    private List<UserEntity> appUserEntities;
}
