package ethan.etframework.mybatisplus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import ethan.etframework.entity.SysUser;
import ethan.etframework.service.SysPermissionService;
import ethan.etframework.service.SysRoleService;
import ethan.etframework.service.SysUserService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCtl {
	
	@Autowired
	private SysPermissionService sysPermissionService;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//@Test
	public void createAUser(){
		SysUser user = new SysUser();
		
		user.setUsername("etuser3");
		user.setPassword("etframework");
		sysUserService.createUser(user);
		
		if(bCryptPasswordEncoder.matches("etframework", "$2a$10$hWAlXIdOZfKftcTs0H0Bq.yayB2ko/CfKvOMM7dE/1iRpYODlHC72")){
			System.out.println("match!");
		}else{
			System.out.println("dont match!");
		}
		
	}
	
	@Test
	public void updateUser(){
		SysUser user = sysUserService.findUserByUsername("ethan");
		
		user.setPassword("etframework");
		sysUserService.updateUser(user);
		
	}
	
}
