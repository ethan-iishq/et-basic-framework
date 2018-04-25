package ethan.etframework.mybatisplus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ethan.etframework.entity.SysUser;
import ethan.etframework.service.SysPermissionService;
import ethan.etframework.service.SysRoleService;
import ethan.etframework.service.SysUserService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateUser {
	
	@Autowired
	private SysPermissionService sysPermissionService;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysUserService sysUserService;
	
	
	@Test
	public void createAUser(){
		SysUser user = new SysUser();
		
		user.setUsername("etuser1");
		user.setPassword("etframework");
		sysUserService.createUser(user);
		
	}
	
}
