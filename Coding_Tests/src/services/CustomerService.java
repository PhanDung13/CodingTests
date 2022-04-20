package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Customer;
import utils.Constant;
import utils.File;
import utils.Validate;

public class CustomerService {
	Scanner sc = new Scanner(System.in);
	static List<Customer> listCustomer = new ArrayList<Customer>();
	
	public static void ViewCustomers() {	
		System.out.println();
		if ((File.read(Constant.CUSTOMER_PATH)) == null) {
			System.out.println("There are no customers");
		} else {
			listCustomer = File.read(Constant.CUSTOMER_PATH);
			System.out.printf("%-10s %-20s %-20s %-15s \n","CustomerID","CustomerName","Address","Phonenumber");
			for (Customer cus : listCustomer) {
				System.out.printf("%-10s %-20s %-20s %-15s \n",cus.getCustomerId(),cus.getCustomerName(),cus.getAddress(),cus.getPhone());
			}
		}
	}
	
	public static boolean checkCustomerId(int id) {
		if(File.read(Constant.CUSTOMER_PATH) == null) return true;
		listCustomer = File.read(Constant.CUSTOMER_PATH);
		for(Customer cus : listCustomer) {
			if(cus.getCustomerId() == id) {
				return false;
			}
		}
		return true;
	}
	
	public void AddCustomer() {
		if (File.read(Constant.CUSTOMER_PATH) == null) {
			listCustomer = new ArrayList<Customer>();
		} else {
			listCustomer = File.read(Constant.CUSTOMER_PATH);
		}
		String name,address,phone;
		int id;

		boolean check;
		do {
			check = true;
			System.out.print("Enter the customer id:");
			id = sc.nextInt();
			if (!Validate.isId(String.valueOf(id))) {
				System.out.println("Please enter the number!");
				check = false;
				continue;
			}
			if(!checkCustomerId(id)) {
				System.out.println("Customer id already exists!");
				check = false;
			}
		} while (!check);
		sc.nextLine();
		do {
			check = true;
			System.out.print("Enter the customer name: ");
			name = sc.nextLine();
			if (!Validate.checkName(name)) {
				System.out.println("Customer name is not empty!");
				check = false;
			}
		} while (!check);

		System.out.print("Enter Address: ");
		address = sc.nextLine();
		
		do {
			check = true;
			System.out.print("Enter the phone number: ");
			phone = sc.nextLine();
			if (!Validate.CheckPhone(phone)) {
				System.out.println("invalid phone number!");
				check = false;
			}
		} while (!check);
		
		try {
			Customer cus = new Customer(id,name,address,phone);
			listCustomer.add(cus);
		} catch (Exception e) {
			System.out.println("Add fail customers");
		}
		
		if (File.write(Constant.CUSTOMER_PATH, listCustomer)) {
			System.out.println("Add successful customers!");
		} else {
			System.out.println("Add fail customers!");
		}
	}
	
	public void EditCustomer() {
		boolean check;
		int id;
		String name,address,phone;
		do {
			check = true;
			System.out.print("Enter the customer id:");
			id = sc.nextInt();
			if (!Validate.isId(String.valueOf(id))) {
				System.out.println("Please enter the number!");
				check = false;
				continue;
			}
			if(checkCustomerId(id)) {
				System.out.println("Customer id does not exists!");
				check = false;
			}
		} while (!check);
		
		listCustomer = File.read(Constant.CUSTOMER_PATH);
		for(Customer cus : listCustomer) {
			if(cus.getCustomerId() == id) {
				do {
					check = true;
					System.out.print("Enter the customer name: ");
					sc.nextLine();
					name = sc.nextLine();
					if (!Validate.checkName(name)) {
						System.out.println("Customer name is not empty!");
						check = false;
					}
					cus.setCustomerName(name);
				} while (!check);

				System.out.print("Enter Address: ");
				address = sc.nextLine();
				cus.setAddress(address);
				
				do {
					check = true;
					System.out.print("Enter the phone number: ");
					phone = sc.nextLine();
					if (!Validate.CheckPhone(phone)) {
						System.out.println("invalid phone number!");
						check = false;
					}
					cus.setPhone(phone);
				} while (!check);
				
				if (File.write(Constant.CUSTOMER_PATH, listCustomer)) {
					System.out.println("Edit successful customers!");
				} else {
					System.out.println("Edit fail customers!");
				}
				break;
			}
		}
	}
	public static String SearchNameById(int customerId) {
		listCustomer = File.read(Constant.CUSTOMER_PATH);
		for(Customer cus : listCustomer) {
			if(cus.getCustomerId() == customerId) {
				return cus.getCustomerName();
			}
		}
		return "";
	}
}
