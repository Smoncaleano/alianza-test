package com.gml.alianza.alianza.data;

import com.gml.alianza.alianza.entity.UserDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserDataRepository extends JpaRepository<UserDataEntity, String> {

}