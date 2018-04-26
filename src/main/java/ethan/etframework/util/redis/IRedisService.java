package ethan.etframework.util.redis;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

public interface IRedisService {

	public boolean set(String key, String value);
	
	public <T> boolean set(String key, T value);
 
	public String get(String key);
	
	public <T> T get(String key, Type type);
	  
	public boolean expire(String key,long expire);
	  
	
	  
	/*public <T> boolean setList(String key ,List<T> list);
	  
	public <T> List<T> getList(String key, Type type);*/
	  
	public long lpush(String key,Object obj);
	  
	public long rpush(String key,Object obj);
	  
	public String lpop(String key);
}
