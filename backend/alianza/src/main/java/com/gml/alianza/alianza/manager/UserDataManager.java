package com.gml.alianza.alianza.manager;

import com.gml.alianza.alianza.dto.UserDataDTO;
import com.gml.alianza.alianza.entity.UserDataEntity;

import java.util.List;

public interface UserDataManager {

    UserDataEntity saveUserData(UserDataDTO userDataDTO);

    List<UserDataEntity> getAllUserData();

    UserDataEntity getUserDataBySharedKey(String sharedKey);

    void deleteUserData(String sharedKey);
}