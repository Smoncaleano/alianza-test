package com.gml.alianza.alianza.mapper;

import com.gml.alianza.alianza.dto.UserDataDTO;
import com.gml.alianza.alianza.entity.UserDataEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserDataMapper {

    @Mapping(source = "sharedKey", target = "sharedKey", defaultExpression = "java(null)")
    UserDataEntity createEntityFromDTO(UserDataDTO dto);
}
