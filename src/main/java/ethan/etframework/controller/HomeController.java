package ethan.etframework.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ethan.etframework.entity.SysUser;

@Controller
public class HomeController {
	
	@GetMapping("greeting")
	public String index(Map<String,Object> map){
		return "/index";
	}
	
	
	@GetMapping({"/", "home", "home.html"})
	public String home(Map<String,Object> map){
		map.put("hello","from TemplateController.helloHtml");
		return "/home";
	}
	
	@GetMapping("home2")
	public String home2(Map<String,Object> map){
		return "/index";
	}
	
	@GetMapping({"login"})
	public String login(Map<String,Object> map){
		
		return "/login";
	}
	
	@PostMapping({"login"})
	public String login(String username, String password){
		System.out.println();
		return "redirect:/home";
	}
	
	@GetMapping({"admin"})
	public String admin(Map<String,Object> map){
		
		return "/admin";
	}

}
