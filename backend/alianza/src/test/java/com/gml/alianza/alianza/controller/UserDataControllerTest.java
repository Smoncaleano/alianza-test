package com.gml.alianza.alianza.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gml.alianza.alianza.AlianzaApplicationTests;
import com.gml.alianza.alianza.data.UserDataRepository;
import com.gml.alianza.alianza.dto.UserDataDTO;
import com.gml.alianza.alianza.entity.UserDataEntity;

import com.gml.alianza.alianza.mapper.UserDataMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(AlianzaApplicationTests.class)
public class UserDataControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    UserDataRepository userDataRepository;

    @MockBean
    UserDataMapper userDataMapper;


    @Test
    public void testGetUserDataBySharedKeyFound() {

        String sharedKey = "testKey";
        UserDataEntity userDataEntity = new UserDataEntity();
        userDataEntity.setSharedKey(sharedKey);

        when(userDataRepository.findById(sharedKey)).thenReturn(Optional.of(userDataEntity));

        URI url = UriComponentsBuilder.fromUri(URI.create(restTemplate.getRootUri()))
                .path("/api/alianza/{sharedKey}")
                .buildAndExpand(sharedKey)
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());


        ObjectMapper objectMapper = new ObjectMapper();
        UserDataEntity responseUserData;
        try {
            responseUserData = objectMapper.readValue(response.getBody(), UserDataEntity.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir la respuesta a UserDataEntity", e);
        }

        assertEquals(userDataEntity.getSharedKey(), responseUserData.getSharedKey());

        verify(userDataRepository, times(1)).findById(sharedKey);
    }

    @Test
    public void testGetUserDataBySharedKeyNotFound() {

        String sharedKey = "nonexistentKey";

        when(userDataRepository.findById(sharedKey)).thenReturn(Optional.empty());

        URI url = UriComponentsBuilder.fromUri(URI.create(restTemplate.getRootUri()))
                .path("/api/alianza/{sharedKey}")
                .buildAndExpand(sharedKey)
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
        );


        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());

        verify(userDataRepository, times(1)).findById(sharedKey);
    }

    @Test
    public void testSaveUserData() {

        UserDataDTO userDataDTO = new UserDataDTO();
        UserDataEntity savedUserData = new UserDataEntity();
        userDataDTO.setEmail("pruebatest@gmail.com");
        userDataDTO.setPhone("3209993456");
        userDataDTO.setBusinessID("Usuario test");

        savedUserData.setEmail("pruebatest@gmail.com");
        savedUserData.setPhone("3209993456");
        savedUserData.setBusinessID("Usuario test");


        when(userDataRepository.save(any(UserDataEntity.class))).thenAnswer(invocation -> {
            UserDataEntity argument = invocation.getArgument(0);

            return argument;
        });

        when(userDataMapper.createEntityFromDTO(any(UserDataDTO.class))).thenReturn(savedUserData);

        URI url = UriComponentsBuilder.fromUri(URI.create(restTemplate.getRootUri()))
                .path("/api/alianza").build().encode().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UserDataDTO> requestEntity = new HttpEntity<>(userDataDTO, headers);

        ResponseEntity<UserDataEntity> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                UserDataEntity.class
        );

        assertEquals(response.getBody().getEmail(), userDataDTO.getEmail());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(userDataRepository, times(1)).save(any());
        verify(userDataMapper, times(1)).createEntityFromDTO(any());
    }


    @Test
    public void testGetAllUsers() {

        List<UserDataEntity> userDataList = Arrays.asList(
                new UserDataEntity(null, "email1@gmail.com", "123456", "User1", new Date(), new Date(), new Date()),
                new UserDataEntity(null, "email2@gmail.com", "789012", "User2", new Date(), new Date(), new Date())
        );

        when(userDataRepository.findAll()).thenReturn(userDataList);

        URI url = UriComponentsBuilder.fromUri(URI.create(restTemplate.getRootUri()))
                .path("/api/alianza").build().encode().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
        );


        assertEquals(HttpStatus.OK, response.getStatusCode());


        ObjectMapper objectMapper = new ObjectMapper();
        List<UserDataEntity> responseUserDataList;
        try {
            responseUserDataList = objectMapper.readValue(response.getBody(), new TypeReference<List<UserDataEntity>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir la respuesta a UserDataEntity", e);
        }

        assertNotNull(responseUserDataList);
        assertEquals(2, responseUserDataList.size());
        assertEquals(userDataList.get(0).getEmail(), responseUserDataList.get(0).getEmail());
        assertEquals(userDataList.get(1).getEmail(), responseUserDataList.get(1).getEmail());

        verify(userDataRepository, times(1)).findAll();
    }

}
