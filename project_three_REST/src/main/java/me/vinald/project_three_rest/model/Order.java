package me.vinald.project_three_rest.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {
	private Integer orderId;
	private String customerName;
	private String productName;
	private Integer quantity;
}
