package ethan.etframework.mybatisplus;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ethan.etframework.entity.SysPermission;
import ethan.etframework.entity.SysRole;
import ethan.etframework.entity.vo.SysPermissionVo;
import ethan.etframework.service.SysPermissionService;
import ethan.etframework.service.SysRoleService;
import ethan.etframework.service.SysUserService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {
	
	@Autowired
	private SysPermissionService sysPermissionService;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Test
	public void testPermissionServiceForfindAll(){
		List<SysPermission> list = sysPermissionService.findAll();
		assert(list != null);
		System.out.println("findAll:" + list);
	}
	
	@Test
	public void testPermissionServiceForfindByUserId(){
		List<SysPermissionVo> list = sysPermissionService.findByUserId(1);
		assert(list != null);
		System.out.println("findByUserId:" + list);
	}
	
	@Test
	public void testPermissionServiceForfindPermissionConRole(){
		List<SysPermissionVo> list = sysPermissionService.findPermissionConRole();
		assert(list != null);
		System.out.println("findPermissionConRole:" + list);
	}
	
	@Test
	public void testRoleServiceForfindByUserId(){
		List<SysRole> list = sysRoleService.findRoleByUserId(1);
		assert(list != null);
		System.out.println("findPermissionConRole:" + list);
	}
	
	
}
