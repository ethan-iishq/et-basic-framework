package ethan.etframework.service;

import ethan.etframework.entity.SysUser;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ethanx
 * @since 2018-02-27
 */
public interface SysUserService extends IService<SysUser> {
	Integer createUser(SysUser entity);
	Integer updateUser(SysUser entity);
	SysUser findUserByUsername(String username);
}
