package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import entities.Order;
import entities.Stock;
import services.CustomerService;
import entities.Product;
import utils.Constant;
import utils.File;
import utils.Validate;

public class OrderService {
	Scanner sc = new Scanner(System.in);
	static List<Order> listOrder = new ArrayList<Order>();
	public static void ViewOrder() {	
		System.out.println();
		if ((File.read(Constant.ORDER_PATH)) == null) {
			System.out.println("There are no order");
		} else {
			listOrder = File.read(Constant.ORDER_PATH);
			System.out.printf("%-10s %-15s %-30s %-10s %-20s %-20s\n","OrderId()","CustomerId","CustomerName","ProductId","Amount","OrderDate");
			for (Order order : listOrder) {
				System.out.printf("%-10s %-15s %-30s %-10s %-20s %-20s\n",order.getOrderId(),order.getCustomerId(),order.getCustomerName(),order.getProductId(),order.getAmount(),order.getOrderDate());
			}
		}
	}
	
	public void SearchOrderByCustomerId() {
		int customerId;
		boolean check;
		do {
			check = true;
			System.out.print("Enter the Customer id:");
			customerId = sc.nextInt();
			if (!Validate.isId(String.valueOf(customerId))) {
				System.out.println("Please enter the number!");
				check = false;
				continue;
			}
			if(CustomerService.checkCustomerId(customerId)) {
				System.out.println("Customer id does not exists!");
				check = false;
			}
		} while (!check);
		if ((File.read(Constant.ORDER_PATH)) == null) {
			System.out.println("There is no order");
		} else {
			listOrder = File.read(Constant.ORDER_PATH);
			System.out.printf("%-10s %-15s %-30s %-10s %-20s %-20s\n","OrderId()","Customer Id","Customer Name","Product Id","Amount","OrderDate");
			for (Order order : listOrder) {
				if(order.getCustomerId() == customerId) {
					System.out.printf("%-10s %-15s %-30s %-10s %-20s %-20s\n",order.getOrderId(),order.getCustomerId(),order.getCustomerName(),order.getProductId(),order.getAmount(),order.getOrderDate());
					break;
				}
			}
		}
	}
	public boolean checkOrderId(int id) {
		if(File.read(Constant.ORDER_PATH) == null) return true;
		listOrder = File.read(Constant.ORDER_PATH);
		for(Order order : listOrder) {
			if(order.getOrderId() == id) {
				return false;
			}
		}
		return true;
	}
	
	public void CreateOrder() {
		if (File.read(Constant.ORDER_PATH) == null) {
			listOrder = new ArrayList<Order>();
		} else {
			listOrder = File.read(Constant.ORDER_PATH);
		}
		int orderId,customerId,productId;
		String customerName;
		float amount;
		Date orderDate = null;
		boolean check;
		do {
			check = true;
			System.out.print("Enter the Order id:");
			orderId = sc.nextInt();
			if (!Validate.isId(String.valueOf(orderId))) {
				System.out.println("Please enter the number!");
				check = false;
				continue;
			}
			if(!checkOrderId(orderId)) {
				System.out.println("Order id already exists!");
				check = false;
			}
		} while (!check);
		
		do {
			check = true;
			System.out.print("Enter the Customer id:");
			customerId = sc.nextInt();
			if (!Validate.isId(String.valueOf(customerId))) {
				System.out.println("Please enter the number!");
				check = false;
				continue;
			}
			if(CustomerService.checkCustomerId(customerId)) {
				System.out.println("Customer id does not exists!");
				check = false;
			}
		} while (!check);
		
		customerName = CustomerService.SearchNameById(customerId);
		
		do {
			check = true;
			System.out.print("Enter the product id:");
			productId = sc.nextInt();
			if (!Validate.isId(String.valueOf(productId))) {
				System.out.println("Please enter the number!");
				check = false;
				continue;
			}
			if(ProductService.checkProductId(productId)) {
				System.out.println("product id does not exists!");
				check = false;
			}
		} while (!check);
		sc.nextLine();
		do {
			check = true;
			System.out.print("Enter amount:");
			String stringamount = sc.nextLine();
			if(!Validate.CheckAmount(stringamount)) {
				System.out.println("invalid amount!");
				check = false;
			}
			amount = Float.parseFloat(stringamount);
		} while (!check);
		do {
			check = true;
			System.out.print("Enter Date:");
			String date = sc.nextLine();
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			df.setLenient(false); 
			try {
			    orderDate = df.parse(date); 
			}
			catch (ParseException e) { 
			   System.out.println("Invalid date");
			}
		} while (!check);
		
		try {
			Order order = new Order(orderId,customerId,customerName,productId,amount,orderDate);
			listOrder.add(order);
		} catch (Exception e) {
			System.out.println("Add fail order");
		}
		
		if (File.write(Constant.ORDER_PATH, listOrder)) {
			System.out.println("Add successful order!");
		} else {
			System.out.println("Add fail order!");
		}
	}
}
