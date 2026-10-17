package me.vinald.project_two.service;

import me.vinald.project_two.MyFirstClass;
import org.springframework.stereotype.Service;

@Service
public class MyFirstService {
	private final MyFirstClass myFirstClass;

	public MyFirstService(MyFirstClass myFirstClass) {
		this.myFirstClass = myFirstClass;
	}

	public String serviceHello() {
		return "Hello from MyFirstService: " + myFirstClass.sayHello();
	}
}
