package volley_net.volley_net.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j //genera il logger
public class TokenService {

    public static  final String TOKEN_SECRET="Flows_tech";
    public static  final int EXPIRE_AFTER=3600; //tempo di scadenza in secondi

    public String createToken(int id_user){
        try {
            Algorithm algorithm=Algorithm.HMAC256(TOKEN_SECRET);
            String token = JWT.create()
                    .withClaim("id_user",id_user) //claim personalizzato

                    .withIssuedAt(Instant.now()) //claim iat
                    .withExpiresAt(Instant.now().plus(EXPIRE_AFTER, ChronoUnit.SECONDS)) //claim scadenza
                    .sign(algorithm);
            return  token;
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return  null;
    }

    public UserToken getUserIdFromToken(String token){
        try{
            Algorithm algorithm=Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            UserToken ut = new UserToken(jwt.getClaim("id_user").asInt());
            return  ut;
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }

}

