package org.wyy.tech.springUtil;

import org.springframework.context.ApplicationContext;

/**
 * @author Wyy
 **/
public class AppContextUtil {
	private static ApplicationContext applicationContext;

	public static ApplicationContext getApplicationContext(){
		if (AppContextUtil.applicationContext == null) {
			throw new RuntimeException("applicationContext is null");
		}
		return AppContextUtil.applicationContext;
	}

	public static void setApplicationContext(ApplicationContext applicationContext) {
		AppContextUtil.applicationContext = applicationContext;
	}

	public static <T> T getBean(Class<T> tClass){
		return getApplicationContext().getBean(tClass);
	}
}
