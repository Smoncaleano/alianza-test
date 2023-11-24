package com.gml.alianza.alianza.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class UserDataDTO {
    private String sharedKey;
    private String businessID;
    private String email;
    private String phone;
    private Date dateAdded;
    private Date startdate;
    private Date enddate;
}
