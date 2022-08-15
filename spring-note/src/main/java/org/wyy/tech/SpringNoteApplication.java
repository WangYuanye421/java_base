package org.wyy.tech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.wyy.tech.bean.Dog;
import org.wyy.tech.springUtil.AppContextUtil;

/**
 * @author Wyy
 **/
@SpringBootApplication
public class SpringNoteApplication {
	public static void main(String[] args){
		ConfigurableApplicationContext ctx = SpringApplication.run(SpringNoteApplication.class, args);

		AppContextUtil.setApplicationContext(ctx);
		Dog bean = AppContextUtil.getBean(Dog.class);
		System.out.println(bean.toString());

	}
}

/**
 * spring启动流程
 * bean的生命周期
 *
 */
