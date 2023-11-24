package com.gml.alianza.alianza.controller;


import com.gml.alianza.alianza.dto.UserDataDTO;
import com.gml.alianza.alianza.entity.UserDataEntity;
import com.gml.alianza.alianza.manager.UserDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/alianza")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserDataController {

    @Autowired
    private UserDataManager userDataManager;

    @PostMapping
    public ResponseEntity<UserDataEntity> saveUserData(@RequestBody UserDataDTO userDataDTO) {
        UserDataEntity savedUserData = userDataManager.saveUserData(userDataDTO);
        savedUserData.setDateAdded(new Date());
        return new ResponseEntity<>(savedUserData, HttpStatus.CREATED);
    }

    @GetMapping("/{sharedKey}")
    public ResponseEntity<UserDataEntity> getUserDataBySharedKey(@PathVariable String sharedKey) {
        UserDataEntity userData = userDataManager.getUserDataBySharedKey(sharedKey);
        if (userData != null) {
            return new ResponseEntity<>(userData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
