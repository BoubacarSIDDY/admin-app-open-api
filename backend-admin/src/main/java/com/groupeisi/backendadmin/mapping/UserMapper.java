package com.groupeisi.backendadmin.mapping;

import com.groupeisi.backendadmin.entities.UserEntity;
import com.groupeisi.generated.model.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDTO toUserDTO (UserEntity userEntity);
    UserEntity toUserEntity (UserDTO appUserDTO);
}
