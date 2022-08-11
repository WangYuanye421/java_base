package org.wyy.tech.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.wyy.tech.entity.SysUser;
import org.wyy.tech.service.impl.SysAuthService;
import org.wyy.tech.service.impl.SysRoleService;
import org.wyy.tech.service.impl.SysUserService;

import javax.annotation.Resource;

/**
 * 自定义realm
 * @author Wyy
 **/
@Configuration
public class CustomRealm extends AuthorizingRealm {
	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysAuthService sysAuthService;

	private SysUserService getSysUserService(){
		if (sysUserService == null) {
			sysUserService = ContextHolder.getContext().getBean(SysUserService.class);
		}
		return sysUserService;
	}

	/**
	 * 授权处理
	 * @param principalCollection
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
	}

	/**
	 * 认证处理
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		SysUser one = getSysUserService().getByUsername(username);
		if (one != null) {
			UsernamePasswordToken t = (UsernamePasswordToken) token;
			String pwd = new Md5Hash(t.getPassword(), one.getSalt(), 1024).toHex();
			if (pwd.equals(one.getPwd())) {
				/**
				 * 继续调用AuthenticatingRealm# this.assertCredentialsMatch(token, info);
				 * token中pwd使用明文,所以info对象中的pwd需要用明文才能比对成功
				 */
				return new SimpleAuthenticationInfo(username, t.getPassword() , this.getName());
			}
		}
		return null;
	}

	/**
	 * 生成密码
	 * @param password
	 * @param salt
	 * @param hashIterations
	 * @return
	 */
	public static String generatorPwd(String password, String salt, int hashIterations){
		Md5Hash md5Hash = new Md5Hash(password, salt, hashIterations);
		return md5Hash.toHex();
	}
}
