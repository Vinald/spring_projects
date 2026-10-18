package me.vinald.project_two;

import me.vinald.project_two.service.MyFirstService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectTwoApplication {

	public static void main(String[] args) {
		var ctx = SpringApplication.run(ProjectTwoApplication.class, args);
		MyFirstClass myFirstClass = ctx.getBean(MyFirstClass.class);
		MyFirstService myFirstService = ctx.getBean(MyFirstService.class);

		System.out.println(myFirstClass.sayHello());
		System.out.println(myFirstService.serviceHello());
		System.out.println("Java version: " + myFirstService.getJavaVersion());
		System.out.println("OS name: " + myFirstService.getOsName());
		System.out.println("Custom property: " + myFirstService.getCustomProperty());
		System.out.println("Custom property from field: " + myFirstService.getCustomPropertyFromField());
	}

}
