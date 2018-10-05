package com.zad.jwt;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-10-01 15:08
 */
public class JWTUtil {

    private static final String USER_ID = "userid";

    private static Key getKeyInstance() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] zads = DatatypeConverter.parseBase64Binary("zad");
        return new SecretKeySpec(zads, signatureAlgorithm.getJcaName());
    }

    public static String createJWT(User user, int expireTime) {
        JwtBuilder builder = Jwts.builder()
                .claim(USER_ID, user.getUserId())
                .setExpiration(Date.from(LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.UTC)))
                .signWith(SignatureAlgorithm.HS256, getKeyInstance());
        return builder.compact();
    }

    public static User getTokenInfo(String token){
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(getKeyInstance())
                .parseClaimsJws(token);
        Claims claims= claimsJws.getBody();
        return new User.UserBuilder().userId((String)claims.get(USER_ID)).build();
    }

}
