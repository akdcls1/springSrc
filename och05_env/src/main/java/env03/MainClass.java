package env03;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		System.out.println("1. Run");
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		System.out.println("4. adminConfig Before Run");
		AdminConnection connection = ctx.getBean("adminConfig", AdminConnection.class);
		System.out.println("5. adminConfig After Run");
		
		System.out.println("connection.getAdminId amindID : "+connection.getAdminId());
		System.out.println("connection.getAdminPw amindPW : "+connection.getAdminPw());
		System.out.println("connection.getSub-adminId() sub_amindID : "+connection.getSub_adminId());
		System.out.println("connection.getSub-adminPw() sub_amindPW : "+connection.getSub_adminPw());
		ctx.close();
	}

}
