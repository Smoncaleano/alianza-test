package com.gml.alianza.alianza.manager;

import com.gml.alianza.alianza.data.UserDataRepository;
import com.gml.alianza.alianza.dto.UserDataDTO;
import com.gml.alianza.alianza.entity.UserDataEntity;
import com.gml.alianza.alianza.mapper.UserDataMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class UserDataManagerImpl implements  UserDataManager{

    @Autowired
    UserDataMapper userDataMapper;

    @Autowired
    UserDataRepository userDataRepository;


    @Override
    public UserDataEntity saveUserData(UserDataDTO userDataDTO) {


            log.info("<<<<<<<<<<<<<<<<<< UserDataManagerImpl saveUserData" + userDataDTO);
            userDataDTO.setDateAdded(new Date());
            userDataDTO.setSharedKey(generateUniqueKey(userDataDTO.getEmail()));
            UserDataEntity findUser = userDataRepository.findById(userDataDTO.getSharedKey()).orElse(null);
            if(findUser == null){
                log.info("<<<<<<<<<<<<<<<<<< UserDataManagerImpl no existe" + userDataDTO.getSharedKey());
                return userDataRepository.save(userDataMapper.createEntityFromDTO(userDataDTO));
            }else{
                log.info("<<<<<<<<<<<<<<<<<< UserDataManagerImpl ya existe" + userDataDTO.getSharedKey());
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ya existe un usuario con este correo",null);
            }




    }

    @Override
    public List<UserDataEntity> getAllUserData() {
        log.info("<<<<<<<<<<<<<<<<<< UserDataManagerImpl getAllUserData");
        return userDataRepository.findAll();
    }



    @Override
    public UserDataEntity getUserDataBySharedKey(String sharedKey) {
        log.info("<<<<<<<<<<<<<<<<<< UserDataManagerImpl getUserDataBySharedKey" + sharedKey);
        return userDataRepository.findById(sharedKey).orElse(null);
    }

    @Override
    public void deleteUserData(String sharedKey) {

    }

    private String generateUniqueKey(String correo) {
        String[] partes = correo.split("@");

        return partes[0];

    }
}
