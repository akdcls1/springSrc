package sam05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex05 {

	public static void main(String[] args) {
		// 1. 전통
//		MessageBean mb = new MessageBeanImpl("Merry", "어린절");
//		mb.sayHello();
		
		// 2. DI
		ApplicationContext ac = new ClassPathXmlApplicationContext("bean05.xml");
		MessageBean mb = (MessageBean) ac.getBean("mb5");
		mb.sayHello();
	}

}
