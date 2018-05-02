package ethan.etframework.service.impl;

import ethan.etframework.entity.SysUser;
import ethan.etframework.mapper.SysUserMapper;
import ethan.etframework.service.SysUserService;
import ethan.etframework.util.StringUtil;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ethanx
 * @since 2018-02-27
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	@Autowired
	SysUserMapper sysUserMapper;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Integer  createUser(SysUser entity){
		String passwd =bCryptPasswordEncoder.encode(entity.getPassword().trim());
		entity.setPassword(passwd);
		return sysUserMapper.insert(entity);
	}
	
	@Override
	public SysUser findUserByUsername(String username) {
		SysUser sysUser = new SysUser();
		sysUser.setUsername(username);
		return sysUserMapper.selectOne(sysUser);
	}

	@Override
	public Integer updateUser(SysUser entity) {
		String passwd = entity.getPassword().trim();
		if(!StringUtil.isStrEmpty(passwd)){
			passwd = bCryptPasswordEncoder.encode(passwd);
			entity.setPassword(passwd);
		}
		
		return sysUserMapper.updateById(entity);
	}

}
