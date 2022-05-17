package DI03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		String configLocation = "classpath:applicationCTX3.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		StudentInfo studentInfo = ctx.getBean("studentInfo", StudentInfo.class);
		studentInfo.getStudentInfo();
		
//		Student student2 = new Student("김유신", 30, "3학년", "2반");
		Student student2 = ctx.getBean("student2", Student.class);
		studentInfo.setStudent(student2);
		studentInfo.getStudentInfo();
		System.out.println("student2.getAge() --> "+student2.getAge());
		System.out.println(student2.getAge()+5);
		
		// Legacy
		Student student3 = new Student("대조영", 37, "1학년", "7반");
		student3.setAge(38);
		studentInfo.setStudent(student3);
		studentInfo.getStudentInfo();
		
		ctx.close();
	}

}
