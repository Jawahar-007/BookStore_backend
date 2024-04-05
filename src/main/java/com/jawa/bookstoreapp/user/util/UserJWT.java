package com.jawa.bookstoreapp.user.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class UserJWT{
    private static final String SECRET_KEY = "jawa@23243";

    public String createToken(String firstname)
    {
        return JWT.create()
                .withClaim(firstname,firstname)
                .sign(Algorithm.HMAC256(Base64.getEncoder().encode(SECRET_KEY.getBytes())));
    }

    public String decodeToken(String Token){
        try{
            return JWT.require(Algorithm.HMAC256(Base64.getEncoder().encode(SECRET_KEY.getBytes())))
                    .build()
                    .verify(Token)
                    .getClaim("firstname")
                    .asString();
        }catch(Exception e)
        { return null;}
    }
}
