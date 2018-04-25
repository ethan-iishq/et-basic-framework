package ethan.etframework.service.impl;

import ethan.etframework.entity.SysPermission;
import ethan.etframework.entity.vo.SysPermissionVo;
import ethan.etframework.mapper.SysPermissionMapper;
import ethan.etframework.service.SysPermissionService;
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
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

	@Autowired
	SysPermissionMapper sysPermissionMapper;
	
	@Override
	public List<SysPermission> findAll() {
		return sysPermissionMapper.selectList(null);
	}

	@Override
	public List<SysPermissionVo> findByUserId(int userId) {
		return sysPermissionMapper.findByUserId(userId);
	}

	@Override
	public List<SysPermissionVo> findPermissionConRole() {
		return sysPermissionMapper.findPermissionConRole();
	}

}
