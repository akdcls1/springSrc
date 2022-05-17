package sam06;

public class MessageBeanImpl implements MessageBean {
	private String name;
	private String greet;
	
	public void setName(String name) {
		this.name = name;
	}

	public void setGreet(String greet) {
		this.greet = greet;
	}
	// set으로 설정하면 xml에서는 property로 해야한다.

	public void sayHello() {
		System.out.println(name+"님 "+greet+" !");
	}

}
