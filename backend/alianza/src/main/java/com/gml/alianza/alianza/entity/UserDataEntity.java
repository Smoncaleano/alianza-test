package com.gml.alianza.alianza.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERDATA")
public class UserDataEntity {

    @Id
    @Column(name = "sharedkey")
    private String sharedKey;
    private String businessID;
    private String email;
    private String phone;
    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name = "dateadded")
    private Date dateAdded;
    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name = "startdate")
    private Date startDate;
    @JsonFormat(pattern="dd-MM-yyyy")
    @Column(name = "enddate")
    private Date endDate;
}
