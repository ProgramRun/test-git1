package com.zad.jwt;

import lombok.Builder;
import lombok.Data;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-10-01 15:11
 */
@Data
@Builder
public class User {

    private String userId;
    private String username;
    private String password;
    private String avatar;

}
