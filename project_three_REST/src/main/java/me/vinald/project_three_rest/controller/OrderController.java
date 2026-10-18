package me.vinald.project_three_rest.controller;

import me.vinald.project_three_rest.model.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
	@PostMapping("/orders")
	public String createOrder(@RequestBody Order order) {
		return  "Order created successfully: " + order;
	}
}
