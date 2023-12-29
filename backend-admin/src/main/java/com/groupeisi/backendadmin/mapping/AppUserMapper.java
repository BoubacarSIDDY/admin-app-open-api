package com.groupeisi.backendadmin.mapping;

import com.groupeisi.backendadmin.entities.AppUserEntity;
import com.groupeisi.generated.model.AppUserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface AppUserMapper {
    AppUserDTO toAppUserDTO (AppUserEntity appUserEntity);
    AppUserEntity toAppUserEntity (AppUserDTO appUserDTO);
}
