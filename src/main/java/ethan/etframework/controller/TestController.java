package ethan.etframework.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;

import ethan.etframework.entity.Person;
import ethan.etframework.service.PersonService;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ethanx
 * @since 2018-02-13
 */
@RestController
@RequestMapping("/apis")
public class TestController {
	
	@PostMapping("/login")
    @ResponseBody
    public Object test(String loginname, String password) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("ethan", "123");
        return map;
    }
}
