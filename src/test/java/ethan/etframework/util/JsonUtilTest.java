package ethan.etframework.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.reflect.TypeToken;

import ethan.etframework.entity.Person;

@RunWith(SpringRunner.class)
@SpringBootTest

public class JsonUtilTest {

	@Autowired
	private JsonUtil jsonUtil;
	
	@Test 
	public void testJsonObj(){
		Person p = new Person();
		p.setAge(12);
		p.setJob("coder");
		p.setName("Bob");
		
		System.out.println("str: " + p.toString());
		
		String s = jsonUtil.toJson(p);
		System.out.println("json: " + s);
		
		Person p2 = (Person) jsonUtil.fromJson(s, Person.class);
		System.out.println("p2 :"+ p + "str: " + p.toString());
	}
	
	@Test 
	public void testJsonList(){
		List<Person> list = generatePersonList();
		
		System.out.println("list to str: " + list.toString());
		
		String s = jsonUtil.toJson(list);
		System.out.println("list to json: " + s);
		
		List<Person> list2 = (List<Person>) jsonUtil.fromJson(s, new TypeToken<List<Person>>() {}.getType());
		
		for(Person p : list2){
			System.out.println("list2 : " + p.toString());
		}
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
