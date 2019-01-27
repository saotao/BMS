package stao.BMS.service.impl;

import com.auth0.jwt.interfaces.Claim;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import stao.BMS.utils.TokenUtil;
import stao.BaseTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RedisServiceTest extends BaseTest {

    @Autowired
    RedisService redisService;


    @Test
    public void set() throws Exception {
        redisService.set("test","test111");
        System.out.println(redisService.get("test"));
        Map<String,String> map = new HashMap<>();
        map.put("12314",TokenUtil.createToken(String.valueOf(123412)));

//        redisService.set("1231414",TokenUtil.createToken(1231414));
        String s1 = redisService.get("1231414");
        String s2 = redisService.get("12aff");
        System.out.println(s1);
        System.out.println(s2);

        Map<String, Claim> stringClaimMap = TokenUtil.verifyToken(s1);
        Claim dateTime = stringClaimMap.get("dateTime");
        Long aLong = dateTime.asLong();
        System.out.println(aLong);
        System.out.println(new Date().getTime());

    }
}