package org.wyy.tech.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wyy
 **/
@Configuration
public class ContextHolder implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	private static ThreadLocal<ApplicationContext> threadSpringContext = new ThreadLocal<ApplicationContext>();

	public static ApplicationContext getContext(){
		ApplicationContext context = getSprintContext();
		if (context == null) {
			return applicationContext;
		} else {
			return context;
		}
	}

	public static ApplicationContext getSprintContext(){
		return threadSpringContext.get();
	}

	public static void putSpringContext(ApplicationContext context){
		threadSpringContext.set(context);
	}

	@SuppressWarnings("static-access")
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		putSpringContext(applicationContext);
		this.applicationContext = applicationContext;
	}
}
