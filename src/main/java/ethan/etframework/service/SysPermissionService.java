package ethan.etframework.service;

import ethan.etframework.entity.SysPermission;
import ethan.etframework.entity.vo.SysPermissionVo;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ethanx
 * @since 2018-02-27
 */
public interface SysPermissionService extends IService<SysPermission> {

	List<SysPermission> findAll();
	List<SysPermissionVo> findPermissionConRole();
	List<SysPermissionVo> findByUserId(int userId);
}
