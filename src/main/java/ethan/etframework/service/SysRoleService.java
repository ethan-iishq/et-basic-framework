package ethan.etframework.service;

import ethan.etframework.entity.SysRole;

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
public interface SysRoleService extends IService<SysRole> {
	List<SysRole> findRoleByUserId(int userId);
}
