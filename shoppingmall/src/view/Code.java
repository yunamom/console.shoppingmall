package view;

public interface Code {
	
	// 상수 정의, 숫자는 메뉴 번호
	static final int SHOP_LOGIN = 999; //로그인
	
	// 관리자
	static final int HOST_MENU = 100;
	static final int HOST_MENU_LOGIN = 199;
	
	// 관리자 - 재고관리
	static final int HOST_STOCK_MENU = 110;
	
	static final int HOST_GOODS_LIST = 111; //상품목록
	static final int HOST_GOODS_ADD = 112; //상품추가
	static final int HOST_GOODS_UPDATE = 113; //상품수정
	static final int HOST_GOODS_DEL = 114; //상품삭제 
	
	// 회원
	
	static final int USER_MENU = 200;
	static final int USER_JOIN = 201; //회원가입
	static final int USER_MENU_LOGIN = 299; //회원 로그인
	
	// 회원 - 상품목록
	static final int USER_GOODS = 210; //상품리스트
	static final int USER_CART_LIST = 220; //장바구니 리스트
	static final int USER_CART_ADD = 221; //장바구니 담기
	static final int USER_CART_DEL = 222; //장바구니 삭제
	
	
	

}
