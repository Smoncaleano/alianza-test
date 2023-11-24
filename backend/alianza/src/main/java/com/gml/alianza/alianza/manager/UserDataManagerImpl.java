package com.gml.alianza.alianza.manager;

import com.gml.alianza.alianza.data.UserDataRepository;
import com.gml.alianza.alianza.dto.UserDataDTO;
import com.gml.alianza.alianza.entity.UserDataEntity;
import com.gml.alianza.alianza.mapper.UserDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDataManagerImpl implements  UserDataManager{

    @Autowired
    UserDataMapper userDataMapper;

    @Autowired
    UserDataRepository userDataRepository;


    @Override
    public UserDataEntity saveUserData(UserDataDTO userDataDTO) {
        return userDataRepository.save(userDataMapper.createEntityFromDTO(userDataDTO));
    }

    @Override
    public List<UserDataEntity> getAllUserData() {
        return null;
    }



    @Override
    public UserDataEntity getUserDataBySharedKey(String sharedKey) {
        return userDataRepository.findById(sharedKey).orElse(null);
    }

    @Override
    public void deleteUserData(String sharedKey) {

    }
}
