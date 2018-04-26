package ethan.etframework.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ethan.etframework.util.StringUtil;

@RestController
@RequestMapping("/et-springboot")
public class NginxTestController {
	
	@PostMapping("/login")
    @ResponseBody
    public Object test(String loginname, String password) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("ethan", "123");
        return map;
    }
	
	@GetMapping("/justtest")
    @ResponseBody
    public Object justtest(String id) {
		Map<String, String> map = new HashMap<String, String>();
		
		if(!StringUtil.isStrEmpty(id)){
			map.put("ethan", "hello");
		}else{
			map.put("msg", "without id");
		}
        return map;
    }
}
