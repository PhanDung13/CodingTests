package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Product;
import utils.Constant;
import utils.File;
import utils.Validate;

public class ProductService {
	Scanner sc = new Scanner(System.in);
	static List<Product> listProduct = new ArrayList<Product>();
	
	public static void ViewProduct() {	
		if ((File.read(Constant.PRODUCT_PATH)) == null) {
			System.out.println("There are no products");
		} else {
			listProduct = File.read(Constant.PRODUCT_PATH);
			for (Product product : listProduct) {
				System.out.printf("%-10s %-20s %-20s\n",product.getProductId(),product.getProductPrice(),product.getProductType());
			}
		}
	}
	
	public static boolean checkProductId(int id) {
		if(File.read(Constant.PRODUCT_PATH) == null) return true;
		listProduct = File.read(Constant.PRODUCT_PATH);
		for(Product product : listProduct) {
			if(product.getProductId() == id) {
				return false;
			}
		}
		return true;
	}
	
	public void AddProduct() {
		if (File.read(Constant.PRODUCT_PATH) == null) {
			listProduct = new ArrayList<Product>();
		} else {
			listProduct = File.read(Constant.PRODUCT_PATH);
		}
		int id;
		float price;
		String type;
		boolean check;
		do {
			check = true;
			System.out.print("Enter the product id:");
			id = sc.nextInt();
			if (!Validate.isId(String.valueOf(id))) {
				System.out.println("Please enter the number!");
				check = false;
				continue;
			}
			if(!checkProductId(id)) {
				System.out.println("Product id already exists!");
				check = false;
			}
		} while (!check);
		
		System.out.print("Enter price: ");
		price = sc.nextFloat();

		System.out.print("Enter product type: ");
		sc.nextLine();
		type = sc.nextLine();
		
		
		try {
			Product product = new Product(id,price,type);
			listProduct.add(product);
		} catch (Exception e) {
			System.out.println("Add fail product");
		}
		
		if (File.write(Constant.PRODUCT_PATH, listProduct)) {
			System.out.println("Add successful product!");
		} else {
			System.out.println("Add fail product!");
		}
	}
}
