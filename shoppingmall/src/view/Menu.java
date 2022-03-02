package view;

public interface Menu {
	
	public void init();
	public void loginMenu(); //첫 시작화면
	public void commonMenu(int menuNo); 

	
	public void userJoin(); //회원가입 
	public void userLogin(); //회원 로그인
	public void userView(); //회원 메뉴 
	public void userCartMenu(); //회원 장바구니 
	
	public void hostLogin(); //관리자 로그인 
	public void hostView(); //관리자 메뉴
	public void hostProductMenu(); // 재고관리 메뉴

}
