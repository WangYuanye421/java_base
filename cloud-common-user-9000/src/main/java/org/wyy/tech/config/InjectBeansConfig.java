package org.wyy.tech.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wyy
 **/
@Configuration
public class InjectBeansConfig {

	@Bean
	public Subject getSubject(){
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		securityManager.setRealm(new CustomRealm());
		SecurityUtils.setSecurityManager(securityManager);
		return SecurityUtils.getSubject();
	}
}
