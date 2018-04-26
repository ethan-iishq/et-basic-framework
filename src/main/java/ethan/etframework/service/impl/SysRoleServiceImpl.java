package ethan.etframework.service.impl;

import ethan.etframework.entity.SysRole;
import ethan.etframework.mapper.SysRoleMapper;
import ethan.etframework.service.SysRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Override
	public List<SysRole> findRoleByUserId(int userId) {
		
		return sysRoleMapper.findRoleByUserId(userId);
	}
	
	
}
