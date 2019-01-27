package stao.BMS.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TokenUtil {

    public static String createToken(String userId) throws Exception {
        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        // build token
        // param backups {iss:Service, aud:APP}
        String token = JWT.create().withHeader(map) // header
                .withClaim("userId", null == userId ? null : userId.toString())
                .withClaim("dateTime", new Date().getTime())
                .sign(Algorithm.HMAC256(ConfigProperties.tokenKey)); // signature

        return token;
    }

    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(ConfigProperties.tokenKey)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            log.error("stao.BMS.utils.TokenUtil.verifyToken():",e);
        }
        return jwt != null ? jwt.getClaims() : null;
    }


    public static void main(String[] args) throws Exception {
        String token = createToken("1234");
        Map<String, Claim> stringClaimMap = verifyToken(token);
        Claim id = stringClaimMap.get("userId");
        System.out.println(id.asString());
        Map<String, Claim> stringClaimMap1 = verifyToken("124817yhfjkaj");

    }

}
