package ethan.etframework.mapper;

import ethan.etframework.entity.SysRole;

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
public interface SysRoleMapper extends BaseMapper<SysRole> {
	List<SysRole> findRoleByUserId(int userId);
}
