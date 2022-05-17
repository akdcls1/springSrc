package DI01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass02 {

	public static void main(String[] args) {
		// 2. DI 적용
		String configLocation = "classpath:applicationCTX.xml";
		System.out.println(configLocation);
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		System.out.println(ctx);
		MyCalculator myCalculator = ctx.getBean("myCalculator", MyCalculator.class);
		System.out.println(myCalculator);
		myCalculator.add();
		myCalculator.sub();
		myCalculator.mul();
		myCalculator.div();
	}

}
