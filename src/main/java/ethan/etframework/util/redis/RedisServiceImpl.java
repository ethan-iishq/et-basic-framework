package ethan.etframework.util.redis;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import ethan.etframework.util.JsonUtil;
import ethan.etframework.util.StringUtil;

@Service  
public class RedisServiceImpl implements IRedisService{  
  
    @Autowired  
    private RedisTemplate<String, ?> redisTemplate;  
    
    @Autowired
    private JsonUtil jsonUtil;
      
    @Override  
    public boolean set(final String key, final String value) {  
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            @Override  
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                connection.set(serializer.serialize(key), serializer.serialize(value));  
                return true;  
            }  
        });  
        return result;  
    }
    
    @Override  
    public <T> boolean set(final String key, final T value){
    	final String json = jsonUtil.toJson(value);
    	return set(key, json);
    }
  
    @Override 
    public String get(final String key){  
        String result = redisTemplate.execute(new RedisCallback<String>() {  
            @Override  
            public String doInRedis(RedisConnection connection) throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                byte[] value =  connection.get(serializer.serialize(key));  
                return serializer.deserialize(value);  
            }  
        });  
        return result;  
    }  
  
    @Override  
    public boolean expire(final String key, long expire) {  
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);  
    }  
  
    /*
    @Override  
    public <T> boolean setList(String key, List<T> list) {  
        String value = jsonUtil.toJson(list);
        return set(key,value);  
    }  
  
    @SuppressWarnings("unchecked")
	@Override  
    public <T> List<T> getList(String key, Type type) {  
        String json = get(key);  
        if(json!=null){  
            List<T> list = (List<T>) jsonUtil.fromJson(json, type);  
            return list;  
        }  
        return null;  
    } 
    */ 
  
    @Override  
    public long lpush(final String key, Object obj) {  
        final String value = jsonUtil.toJson(obj);  
        long result = redisTemplate.execute(new RedisCallback<Long>() {  
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer(); 
                long count = connection.lPush(serializer.serialize(key), serializer.serialize(value));  
                return count;  
            }  
        });  
        return result;  
    }  
  
    @Override  
    public long rpush(final String key, Object obj) {  
        final String value = jsonUtil.toJson(obj);  
        long result = redisTemplate.execute(new RedisCallback<Long>() {  
            @Override  
            public Long doInRedis(RedisConnection connection) throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                long count = connection.rPush(serializer.serialize(key), serializer.serialize(value));  
                return count;  
            }  
        });  
        return result;  
    }  
  
    @Override  
    public String lpop(final String key) {  
        String result = redisTemplate.execute(new RedisCallback<String>() {  
            @Override  
            public String doInRedis(RedisConnection connection) throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                byte[] res =  connection.lPop(serializer.serialize(key));  
                return serializer.deserialize(res);  
            }  
        });  
        return result;  
    }

	@Override
	public <T> T get(String key, Type type) {
		String json = get(key);
		if(StringUtil.isStrEmpty(json)){
			return null;
		}else{
			return jsonUtil.fromJson(json, type);
		}
	}

  
  
}  
