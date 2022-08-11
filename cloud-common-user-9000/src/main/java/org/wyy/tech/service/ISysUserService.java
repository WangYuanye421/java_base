package org.wyy.tech.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.wyy.tech.entity.SysUser;
import org.wyy.tech.service.impl.SysUserService;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author wyy
 * @since 2022-08-11
 */
public interface ISysUserService extends IService<SysUser> {

	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	SysUser registerUser(SysUser user);

	SysUser getByUsername(String username);

	boolean userExist(String username);
}
