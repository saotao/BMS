package stao.BMS.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import stao.BMS.utils.ConfigProperties;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public boolean hashKeyExist(String key, String hashKey) {
        return stringRedisTemplate.opsForHash().hasKey(key, hashKey);
    }

    public boolean hashSetnx(String key, String hashKey, String value) {
        return stringRedisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }

    public void hashSet(String key, String hashKey, String value) {
        stringRedisTemplate.opsForHash().put(key, hashKey, value);
    }

    public void addSetValue(String key, String val) {
        stringRedisTemplate.opsForSet().add(key, val);
    }

    public void removeSetVal(String key, String val) {
        stringRedisTemplate.opsForSet().remove(key, val);
    }

    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public void setExpire(String key, String value,long expire,TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, value,expire,unit);
    }

    public void setnx(String key, String value) {
        stringRedisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    public Set<String> getSetAll(String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }

    public boolean hashDel(String key, String hashKey) {
        return stringRedisTemplate.opsForHash().delete(key, hashKey) == 1;
    }

    public Map<String, String> hashEntries(String key) {
        BoundHashOperations<String, String, String> operations = stringRedisTemplate.boundHashOps(key);
        return operations.entries();
    }

    public void hashMSet(String key, Map<String, String> params) {
        stringRedisTemplate.opsForHash().putAll(key, params);
    }

    public String hashGet(String key, String hashKey) {
        return stringRedisTemplate.<String, String>opsForHash().get(key, hashKey);
    }

    public boolean keyExist(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    public boolean expireKey(String key, int time, TimeUnit unit) {
        return stringRedisTemplate.expire(key, time, unit);
    }

    public long sset(String key, String value) {
        return stringRedisTemplate.opsForSet().add(key, value);
    }

    public boolean sexist(String key, String value) {
        return stringRedisTemplate.opsForSet().isMember(key, value);
    }

    public boolean sadd(String key, String value) {
        return stringRedisTemplate.opsForSet().add(key, value) == 1;
    }

    public boolean sdel(String key, String value) {
        return stringRedisTemplate.opsForSet().remove(key, value) == 1;
    }

    public Set<String> smembers(String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }

    public boolean getLock(String lockKey, int keepTime) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        String preCheckTimeStr = valueOperations.get(lockKey);

        long now = System.currentTimeMillis();
        if (StringUtils.isEmpty(preCheckTimeStr)) {
            return stringRedisTemplate.opsForValue().setIfAbsent(lockKey, String.valueOf(now));
        }

        long preCheckTime = Long.parseLong(preCheckTimeStr);
        if ((now - preCheckTime) > keepTime) {
            long diffTime = now - preCheckTime;
            long result = valueOperations.increment(lockKey, diffTime);
            if (result == now) {
                return true;
            }
            valueOperations.increment(lockKey, - diffTime);
        }
        return false;
    }

    public boolean checkToken(String userName,String token){
        if(userName==null||token==null) return false;
        String tokenCloud = stringRedisTemplate.opsForValue().get(userName);
        if(!token.equals(tokenCloud)) return false;
        else stringRedisTemplate.expire(userName, ConfigProperties.TOKEN_PASS,TimeUnit.MILLISECONDS);
        return true;
    }
}
