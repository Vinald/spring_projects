package me.vinald.project_two;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectTwoApplication {

	public static void main(String[] args) {
		var ctx = SpringApplication.run(ProjectTwoApplication.class, args);
		MyFirstClass myFirstClass = ctx.getBean(MyFirstClass.class);

		System.out.println(myFirstClass.sayHello());
	}

}
