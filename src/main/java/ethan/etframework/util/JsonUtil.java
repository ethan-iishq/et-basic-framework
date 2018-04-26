package ethan.etframework.util;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class JsonUtil {

	private Gson gson = new Gson();
	
	public <T> String toJson(T t){
		return gson.toJson(t);
	}
	
	
	
	public <T> T fromJson(String json, Class<T> type){
		return (T) gson.fromJson(json, type);
	}
	
	public <T> T fromJson(String json, Type type){

		return gson.fromJson(json, type);
	}
	
	
}
