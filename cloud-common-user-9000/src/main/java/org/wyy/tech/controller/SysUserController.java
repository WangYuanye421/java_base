package org.wyy.tech.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wyy.tech.entity.SysUser;
import org.wyy.tech.service.impl.SysUserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author wyy
 * @since 2022-08-11
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController {
	@Resource
	private SysUserService service;
	@Resource
	private Subject subject;


	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	@PostMapping("register")
	public HttpEntity<SysUser> register(@RequestBody SysUser user){
		user.paramCheck();
		service.registerUser(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	/**
	 * 登录
	 * @param request
	 * @return
	 */
	@RequestMapping("login")
	public HttpEntity login(HttpServletRequest request){
		UsernamePasswordToken token = new UsernamePasswordToken(
				request.getParameter("username"),
				request.getParameter("password"));
		try{
			subject.login(token);
		} catch (UnknownAccountException e) {
			System.out.println("用户名错误");
		} catch (AuthenticationException e) {
			System.out.println("密码错误");
		}
		// todo 返回session,后续所有需要登录的请求都去校验session是否存在
		return new ResponseEntity<>("login success", HttpStatus.OK);
	}


}

