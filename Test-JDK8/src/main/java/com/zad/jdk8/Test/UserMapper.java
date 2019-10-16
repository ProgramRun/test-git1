package com.zad.jdk8.Test;

import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-10-16 19:08
 */
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @MapMapping(valueTargetType = UserDTO.class)
    UserDTO toDTO(UserDO userDO);

    @MapMapping(valueTargetType = UserDO.class)
    UserDO toDO(UserDTO userDTO);
}
