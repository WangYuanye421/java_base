package org.wyy.tech.service.impl;

import cn.hutool.Hutool;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;
import org.wyy.tech.entity.SysUser;
import org.wyy.tech.mapper.SysUserMapper;
import org.wyy.tech.service.ISysUserService;

import static org.wyy.tech.exception.cons.AccountExceptionMsg.ACCOUNT_EXIST;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author wyy
 * @since 2022-08-11
 */
@Service
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

	@Override
	public SysUser registerUser(SysUser user) {
		// 是否已存在
		if (userExist(user.getUsername())) throw new RuntimeException(ACCOUNT_EXIST);
		String salt = RandomUtil.randomString(8);;
		String pwd = new Md5Hash(user.getPwd(), salt, 1024).toHex();
		user.setPwd(pwd);
		user.setSalt(salt);
		save(user);
		return null;
	}

	@Override
	public SysUser getByUsername(String username) {
		QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(SysUser::getUsername, username);
		return getOne(wrapper);
	}

	@Override
	public boolean userExist(String username) {
		return getByUsername(username) != null;
	}
}
