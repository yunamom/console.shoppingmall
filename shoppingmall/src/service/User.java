package service;

public interface User {
	
	void cartList(String userId); //장바구니 리스트
	void cartAdd(String userId); //장바구니 담기
	void cartRemove(); //장바구니 삭제

}
