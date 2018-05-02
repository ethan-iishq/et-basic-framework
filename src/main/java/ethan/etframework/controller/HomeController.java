package ethan.etframework.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ethan.etframework.entity.SysUser;
import ethan.etframework.util.LoginUserDetail;

@Controller
public class HomeController {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    
	@GetMapping("greeting")
	public String index(Map<String,Object> map){
		return "/index";
	}
	
	
	@GetMapping({"/", "home", "home.html"})
	public String home(Map<String,Object> map){
		map.put("hello","from TemplateController.helloHtml");
		UserDetails userDetails =  LoginUserDetail.getLoginUserDetail();
		logger.info(userDetails.toString());
		map.put("username", userDetails.getUsername());
		return "/home";
	}
	
	@GetMapping("home2")
	public String home2(Map<String,Object> map){
		UserDetails userDetails =  LoginUserDetail.getLoginUserDetail();
		logger.info(userDetails.toString());
		map.put("username", userDetails.getUsername());
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
