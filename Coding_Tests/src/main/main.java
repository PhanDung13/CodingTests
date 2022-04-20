package main;

import java.util.Scanner;
import services.CustomerService;
import services.OrderService;
import services.ProductService;
import services.StockService;
import utils.Validate;

public class main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String menu;
		CustomerService customerService = new CustomerService();
		OrderService orderSevice = new OrderService();
		ProductService productSevice = new ProductService();
		StockService stockService = new StockService();
		while(true) {
			System.out.println("---------------MENU---------------");
			System.out.println("1.Create a customer");
			System.out.println("2.View all customer");
			System.out.println("3.Edit a customer");
			System.out.println("4.Create one order");
			System.out.println("5.View all order");
			System.out.println("6.Search order info by customer id");
			System.out.println("7.Exit");
			do {
				System.out.print("Please enter the choise: ");
				menu = sc.next();
				if(!Validate.isId(menu)) {
					System.err.println("Please enter the number!");
				}
			}while(!Validate.isId(menu));
			switch(menu) {
			case "1":
				customerService.AddCustomer();
				break;
			case "2":
				customerService.ViewCustomers();
				break;
			case "3":
				customerService.EditCustomer();
				break;
			case "4":
				orderSevice.CreateOrder();
				break;
			case "5":
				orderSevice.ViewOrder();
				break;
			case "6":
				orderSevice.SearchOrderByCustomerId();
				break;
			case "7":
				System.exit(0);
				break;
			default:
				System.err.println("Your choice is invalid! Please input another choice.");
				break;
			}
		}
	}
}
