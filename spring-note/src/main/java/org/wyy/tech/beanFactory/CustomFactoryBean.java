package org.wyy.tech.beanFactory;

import org.springframework.beans.factory.FactoryBean;
import org.wyy.tech.bean.Dog;

/**
 * @author Wyy
 **/
public class CustomFactoryBean implements FactoryBean<Dog> {
	/**
	 * 自定义工厂bean
	 */

	@Override
	public Dog getObject() throws Exception {
		return null;
	}

	@Override
	public Class<?> getObjectType() {
		return null;
	}


}
