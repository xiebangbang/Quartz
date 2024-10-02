//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package cn.itheima.quartz.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@Order(-2147482649)
public class RedisUtil {
    private static final String KEYHEADER = "SERVICE";
    private static final long TIME_OUT = 10000L;
    private static RedisTemplate<String, Object> redisTemplate;

    public RedisUtil() {
    }

    @Autowired(
            required = false
    )
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }

    public static void setObject(String key, Object obj) {
        setObject(key, obj, -1L);
    }

    public static void setObject(String key, Object obj, long timeout) {
        setObject(key, obj, timeout * 60L, TimeUnit.SECONDS);
    }

    public static void setObject(String key, Object obj, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set("SERVICE" + key, obj);
        if (timeout > 0L) {
            redisTemplate.expire("SERVICE" + key, timeout, unit);
        }

    }

    public static Object getObject(String key) {
        return redisTemplate.opsForValue().get("SERVICE" + key);
    }

    public static void delete(String key) {
        redisTemplate.delete("SERVICE" + key);
    }

    public static void setHmObject(String name, String key, Object obj) {
        setHmObject(name, key, obj, -1L);
    }

    public static void setHmObject(String name, String key, Object obj, long timeout) {
        setHmObject(name, key, obj, timeout * 60L, TimeUnit.SECONDS);
    }

    public static void setHmObject(String name, String key, Object obj, long timeout, TimeUnit unit) {
        redisTemplate.opsForHash().put("SERVICE" + name, key, obj);
        if (timeout > 0L) {
            redisTemplate.expire("SERVICE" + name, timeout, unit);
        }

    }

    public static Object getHmObject(String name, String key) {
        return redisTemplate.opsForHash().get("SERVICE" + name, key);
    }

    public static void deleteHm(String name, String key) {
        redisTemplate.opsForHash().delete("SERVICE" + name, key);
//        redisTemplate.opsForHash().delete("SERVICE" + name, new Object[]{key});
    }

    public static List<Object> getMapValues(String redisKey, List<Object> mapKeys) {
        return redisTemplate.opsForHash().multiGet(redisKey, mapKeys);
    }

    public static boolean hasKey(String redisKey) {
        return redisTemplate.hasKey(redisKey);
    }

    public static void setExpire(String redisKey, long timeout) {
        redisTemplate.expire(redisKey, timeout, TimeUnit.MILLISECONDS);
    }

    public static void setExpireBySecond(String redisKey, long timeout) {
        redisTemplate.expire(redisKey, timeout, TimeUnit.SECONDS);
    }

    public static void setExpireByMinutes(String redisKey, long timeout) {
        redisTemplate.expire(redisKey, timeout, TimeUnit.MINUTES);
    }

    public static void setExpireByHours(String redisKey, long timeout) {
        redisTemplate.expire(redisKey, timeout, TimeUnit.HOURS);
    }

    public static Set<Object> getMapKeys(String redisKey) {
        return redisTemplate.opsForHash().keys(redisKey);
    }

    public static List<Object> getMapValues(String redisKey) {
        return redisTemplate.opsForHash().values(redisKey);
    }

    public static Map<Object, Object> getMap(String redisKey) {
        return redisTemplate.opsForHash().entries(redisKey);
    }

    public static void deleteMapValue(String redisKey, Object... mapKey) {
        redisTemplate.opsForHash().delete(redisKey, mapKey);
    }

    public static boolean mapHasKey(String redisKey, Object mapKey) {
        return redisTemplate.opsForHash().hasKey(redisKey, mapKey);
    }

    public static void offer(String redisKey, Object value) {
        redisTemplate.opsForList().rightPush(redisKey, value);
    }

    public static Object poll(String redisKey) {
        return redisTemplate.opsForList().leftPop(redisKey);
    }

    public static Object peek(String redisKey) {
        return redisTemplate.opsForList().index(redisKey, 0L);
    }

    public static List<Object> getList(String redisKey) {
        return redisTemplate.opsForList().range(redisKey, 0L, -1L);
    }

    public static Set<String> getKeys(String prefix) {
        return redisTemplate.keys(prefix.concat("*"));
    }

//    public static Set<String> scanKeys(String prefix) {
//        Set<String> keys = (Set)redisTemplate.execute((connection) -> {
//            Set<String> keysTmp = new HashSet();
//            Cursor<byte[]> cursor = connection.scan((new ScanOptions.ScanOptionsBuilder()).match(prefix + "*").count(1000L).build());
//
//            while(cursor.hasNext()) {
//                keysTmp.add(new String((byte[])cursor.next()));
//            }
//
//            return keysTmp;
//        });
//        return keys;
//    }

    public static List<Object> getValues(List<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    public static void setSetValue(String redisKey, Object value) {
        redisTemplate.opsForSet().add(redisKey, new Object[]{value});
    }

    public static void deleteSetValue(String redisKey, Object value) {
        redisTemplate.opsForSet().remove(redisKey, new Object[]{value});
    }

    public static boolean setHasKey(String redisKey, Object value) {
        return redisTemplate.opsForSet().isMember(redisKey, value);
    }

    public static Set<Object> getSet(String redisKey) {
        return redisTemplate.opsForSet().members(redisKey);
    }

    public static void convertAndSend(String channel, String msg) {
        redisTemplate.convertAndSend(channel, msg);
    }

    public static boolean lock(String key, Object lockValue) {
        long nowTime = System.nanoTime();
        long timeout = 10000000000L;
        Random r = new Random();

        try {
            while(System.nanoTime() - nowTime < timeout) {
                if (redisTemplate.opsForValue().setIfAbsent(key, lockValue)) {
                    redisTemplate.opsForValue().set(key, lockValue, 10000L, TimeUnit.MILLISECONDS);
                    return true;
                }

                Thread.sleep(3L, r.nextInt(500));
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return false;
    }

    public static void unLock(String key) {
        redisTemplate.delete(key);
    }
}
