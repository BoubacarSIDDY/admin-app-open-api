package com.groupeisi.backendadmin.services;

import com.groupeisi.backendadmin.exceptions.EntityNotFoundException;
import com.groupeisi.backendadmin.exceptions.RequestException;
import com.groupeisi.backendadmin.mapping.UserMapper;
import com.groupeisi.backendadmin.repositories.IUserRepository;
import com.groupeisi.generated.model.UserDTO;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
public class UserService {
    private IUserRepository iUserRepository;
    private UserMapper userMapper;
    private MessageSource messageSource;

    public UserService(IUserRepository iUserRepository, UserMapper userMapper, MessageSource messageSource) {
        this.iUserRepository = iUserRepository;
        this.userMapper = userMapper;
        this.messageSource = messageSource;
    }

    @Transactional
    public List<UserDTO> getAllUsers(){
        return iUserRepository.findAll().stream().map(userMapper::toUserDTO).toList();
    }

    @Transactional
    public UserDTO getUser(int id){
        return userMapper.toUserDTO(iUserRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id}, Locale.getDefault()))));
    }
    @Transactional
    public UserDTO save(UserDTO userDTO){
        return userMapper.toUserDTO(iUserRepository.save(userMapper.toUserEntity(userDTO)));
    }
    @Transactional
    public UserDTO update(int id, UserDTO userDTO){
        return iUserRepository.findById(id).map(entity -> {
            userDTO.setId(id);
            return userMapper.toUserDTO(iUserRepository.save(userMapper.toUserEntity(userDTO)));
        }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id}, Locale.getDefault())));
    }
    @Transactional
    public void delete(int id){
        try{
            iUserRepository.deleteById(id);
        }catch (Exception e){
            throw new RequestException(messageSource.getMessage("user.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}
