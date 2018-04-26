package ethan.etframework.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import ethan.etframework.entity.SysPermission;
import ethan.etframework.entity.SysRole;
import ethan.etframework.entity.vo.SysPermissionVo;
import ethan.etframework.service.SysPermissionService;

@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource{

	@Autowired
	private SysPermissionService permissionService;
	
	private HashMap<String, Collection<ConfigAttribute>> map =null;
	
	public void loadResourceDefine(){
		map = new HashMap<>();
		Collection<ConfigAttribute> array;
		ConfigAttribute cg;
		List<SysPermissionVo> permissionvos = permissionService.findPermissionConRole();
		for(SysPermissionVo permissionvo : permissionvos){
			array = new ArrayList<>();
			for(SysRole role : permissionvo.getRoleList()){
				cg = new SecurityConfig(role.getName());
				array.add(cg);
			}
			map.put(permissionvo.getUrl(), array);
			
		}
		
	}
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object obj) throws IllegalArgumentException {
		if(map == null) loadResourceDefine();
		HttpServletRequest request = ((FilterInvocation)obj).getHttpRequest();
		AntPathRequestMatcher matcher;
		String resUrl;
		for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ){
			resUrl = iter.next();
			matcher = new AntPathRequestMatcher(resUrl);
			if(matcher.matches(request)){
				return map.get(resUrl);
			}
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
