package me.vinald.project_two.service;

import me.vinald.project_two.MyFirstClass;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class MyFirstService {
	private final MyFirstClass myFirstClass;
	private final Environment environment;
	@Value("${my.custom.property:default_value}")
	private String customProperty;

	public MyFirstService(MyFirstClass myFirstClass, Environment environment) {
		this.environment = environment;
		this.myFirstClass = myFirstClass;
	}

	public String serviceHello() {
		return "Hello from MyFirstService: " + myFirstClass.sayHello();
	}

	public String getJavaVersion() {
		return environment.getProperty("java.version");
	}

	public String getOsName() {
		return environment.getProperty("os.name");
	}

	public String getCustomProperty() {
		return environment.getProperty("my.custom.property");
	}

	public String getCustomPropertyFromField() {
		return customProperty;
	}
}
