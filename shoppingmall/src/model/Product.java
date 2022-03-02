package model;

import java.util.HashMap;
import java.util.Map;

public class Product { // 상품 재고 목록
	
	public static Map<Integer,Product> productMap = new HashMap<Integer,Product>();
	
	private int productNo;
	private int price;
	private int count;
	private String productName;
	
	private Product() {}
	
	public Product(int productNo, int price, int count, String productName) {
		this.productNo = productNo;
		this.price = price;
		this.count = count;
		this.productName = productName;
	}

	public static Map<Integer, Product> getProductMap() {
		return productMap;
	}

	public static void setProductMap(Map<Integer, Product> productMap) {
		Product.productMap = productMap;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String toString() {
		
		return "-----| "+ productNo + "\t" + productName + "\t" + price + "\t" + String.valueOf(count) + "|-----";
	}
}
