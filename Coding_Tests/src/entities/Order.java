package entities;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
	private int orderId; 
	private int customerId; 
	private String customerName;
	private int productId; 
	private float amount; 
	private Date orderDate;
	
	public Order(int orderId, int customerId, String customerName, int productId, float amount, Date orderDate) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.customerName = customerName;
		this.productId = productId;
		this.amount = amount;
		this.orderDate = orderDate;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	
}
