package ethan.etframework.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ethan.etframework.entity.SecuritySysUser;
import ethan.etframework.entity.SysRole;
import ethan.etframework.entity.SysUser;
import ethan.etframework.service.SysPermissionService;
import ethan.etframework.service.SysRoleService;
import ethan.etframework.service.SysUserService;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	SysUserService sysUserService;
	@Autowired
	SysPermissionService sysPermissionService;
	@Autowired
	SysRoleService sysRoleService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser user = sysUserService.findUserByUsername(username);
		if(user != null){
			List<SysRole> roles = sysRoleService.findRoleByUserId(user.getId());
			return new SecuritySysUser(user, roles);
		}else{
			throw new UsernameNotFoundException(username + " do not exist!");
		}
	}

}
