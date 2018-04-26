package ethan.etframework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
 * @since 2017-12-08
 */
@RestController
@RequestMapping("/person")
public class PersonController {
	@Autowired
	private PersonService personService;
	
	@GetMapping("/test")
    @ResponseBody
    public Page<Person> test() {
        return personService.selectPage(new Page<Person>(0,10));
    }
}
