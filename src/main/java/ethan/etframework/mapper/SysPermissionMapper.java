package ethan.etframework.mapper;

import ethan.etframework.entity.SysPermission;
import ethan.etframework.entity.vo.SysPermissionVo;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ethanx
 * @since 2018-02-27
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
	List<SysPermissionVo> findPermissionConRole();
	List<SysPermissionVo> findByUserId(int userId);
}
