package com.zad.jdk8.mapStructTest;

import com.zad.jdk8.Test.UserDO;
import com.zad.jdk8.Test.UserDTO;
import com.zad.jdk8.Test.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-10-16 19:29
 */

public class MapStructTest {

    private UserDTO userDTO;
    private UserDO userDO;

    @BeforeEach
    public void init() {
        userDTO = new UserDTO();
        userDTO.setCode("dtoCode");
        userDTO.setName("dtoName");

        userDO = new UserDO();
        userDO.setCode("doCode");
        userDO.setName("doName");
    }

    @Test
    public void testDTO2DO() {
        UserDO userDO = UserMapper.INSTANCE.toDO(userDTO);
        Assertions.assertNotNull(userDO);
        Assertions.assertEquals("dtoCode", userDO.getCode());
        Assertions.assertEquals("dtoName", userDO.getName());
    }

    @Test
    public void testDO2DTO() {
        UserDTO userDTO = UserMapper.INSTANCE.toDTO(userDO);
        Assertions.assertNotNull(userDTO);
        Assertions.assertEquals("doCode", userDTO.getCode());
        Assertions.assertEquals("doName", userDTO.getName());
    }


}
