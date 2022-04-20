package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
	public static boolean checkName(String customerName) {
		return customerName == null || customerName == "" ? false:true;
	}
	
	public static boolean CheckPhone(String phone) {
		return phone == null || phone == "" ? false :Pattern.matches("\\d{10}", phone);
	}
	
	public static boolean CheckAmount (String amount) {
		return amount == null || amount == "" ? false:Pattern.matches("^\\d*[1-9]\\d*$",amount);
	}
	
	public static boolean CheckDate(String date) {
		return date == null || date == "" ? false :Pattern.matches("\\d{1,2}/\\d{1,2}/\\d{4}",date);
	}
	public static boolean isId(String id) {
		Pattern pattern = Pattern.compile("^\\d{1,}$");
		Matcher matcher = pattern.matcher(id);
		return matcher.matches();
	}
}
