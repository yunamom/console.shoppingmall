package model;

import java.util.HashMap;
import java.util.Map;

import service.UserImpl;

public class UserProduct {
	public static Map<String,String>join = new HashMap<String,String>(); 
	//회원가입
	public static Map<Integer,UserProduct> userMap = new HashMap<Integer,UserProduct>(); 
	//장바구니
	
	private int productNo; //상품번호
	private int price; //가격
	private int count; //수량
	private String productName; //상품이름
	private String userId; //회원 id
	
	private UserProduct() {}
	
	public UserProduct(int productNo, int price, int count, String productName, String userId) {
		this.productNo = productNo;
		this.price = price;
		this.count = count;
		this.productName = productName;
		this.userId = userId;
	}

	public static Map<Integer, UserProduct> getUserMap() {
		return userMap;
	}

	public static void setUserMap(Map<Integer, UserProduct> userMap) {
		UserProduct.userMap = userMap;
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
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String toString() { // 번호 이름 가격 수량
		return "-----| "+ productNo + "\t" + productName + "\t" + price + "\t" + String.valueOf(count) + "|-----";
	}
}