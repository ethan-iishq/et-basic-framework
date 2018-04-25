package ethan.etframework.redis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.reflect.TypeToken;

import ethan.etframework.entity.Person;
import ethan.etframework.util.JsonUtil;
import ethan.etframework.util.StringUtil;
import ethan.etframework.util.redis.IRedisService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
	
	@Autowired
	private JsonUtil jsonUtil;
	
	@Autowired
	private IRedisService redisService;
	
	@Test
	public void testGetset(){
		Person p = new Person();
		p.setAge(12);
		p.setJob("coder");
		p.setName("Bob");
		
		
		
		String s = jsonUtil.toJson(p);
		System.out.println("json: " + s);
		
		redisService.set("person1", s);
		String s2 = redisService.get("person1");
		assert(StringUtil.isStrEqual(s, s2));
	}
	
	@Test 
	public void testGetsetList(){
		List<Person> list = generatePersonList();
		
		System.out.println("list to str: " + list.toString());
		
		String s = jsonUtil.toJson(list);
		System.out.println("list to json: " + s);
		
		
	}
	
	private List<Person> generatePersonList(){
		List<Person> list = new ArrayList<Person>();
		for(int i = 0; i < 5; ++i){
			Person p = new Person();
			p.setAge(12+i);
			p.setJob("coder_"+i);
			p.setName("Bob "+i);
			list.add(p);
		}
		return list;
	}
}
