package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entities.Stock;
import utils.Constant;
import utils.File;
import utils.Validate;

public class StockService {
	Scanner sc = new Scanner(System.in);
	static List<Stock> listStock = new ArrayList<Stock>();
	
	public static void ViewStocks() {	
		if ((File.read(Constant.STOCK_PATH)) == null) {
			System.out.println("There are no stocks");
		} else {
			listStock = File.read(Constant.STOCK_PATH);
			for (Stock stock : listStock) {
				System.out.printf("%-10s %-20s %-20s\n",stock.getProductId(),stock.getQuatily(),stock.getShopNo());
			}
		}
	}
	
	public boolean checkProductId(int id) {
		if(File.read(Constant.STOCK_PATH) == null) return true;
		listStock = File.read(Constant.STOCK_PATH);
		for(Stock stock : listStock) {
			if(stock.getProductId() == id) {
				return false;
			}
		}
		return true;
	}
	
	public void AddStock() {
		if (File.read(Constant.STOCK_PATH) == null) {
			listStock = new ArrayList<Stock>();
		} else {
			listStock = File.read(Constant.STOCK_PATH);
		}
		int id,quatily,shopNo;
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
		
		System.out.print("Enter the quality: ");
		quatily = sc.nextInt();

		System.out.print("Enter shopNo: ");
		shopNo = sc.nextInt();
		
		
		try {
			Stock stock = new Stock(id,quatily,shopNo);
			listStock.add(stock);
		} catch (Exception e) {
			System.out.println("Add fail Stock");
		}
		
		if (File.write(Constant.STOCK_PATH, listStock)) {
			System.out.println("Add successful stock!");
		} else {
			System.out.println("Add fail stock!");
		}
	}
	
}
