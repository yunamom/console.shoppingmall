package view;

import java.util.Scanner;

import model.Product;
import model.UserProduct;
import service.HostImpl;
import service.UserImpl;

public class MenuImpl implements Menu, Code{
	
	public static UserImpl user = UserImpl.getInstance();
	public static HostImpl host = HostImpl.getInstance();

	public static Scanner scan = new Scanner(System.in);
	String userId; 
	
	HostImpl hostImpl;
	UserImpl userImpl;
	
	private MenuImpl() {}
	private static MenuImpl menu = new MenuImpl();
	
	public static MenuImpl getInstance() {
		if(menu == null) {
			menu = new MenuImpl();
		}		
		return menu;
	}
	
	@Override 
	public void init() {//샘플 데이터 넣기
		Product.productMap.put(123, new Product(123,2500,200,"소고기죽"));
		Product.productMap.put(124, new Product(124,2000,200,"야채죽"));
		Product.productMap.put(125, new Product(125,3000,200,"전복죽"));
		Product.productMap.put(126, new Product(126,3000,200,"호박죽"));
		Product.productMap.put(127, new Product(127,2000,200,"흰죽"));
		
		UserProduct.join.put("aaa", "aaa");
		UserProduct.join.put("bbb", "bbb");
		UserProduct.join.put("ccc", "ccc");
		UserProduct.join.put("ddd", "ddd");
		UserProduct.join.put("eee", "eee");
		loginMenu();
	}
	
	@Override 
	public void commonMenu(int menuNo) {
		switch (menuNo) {
		case HOST_MENU : hostView(); 		
			break;			
		case HOST_MENU_LOGIN : hostLogin();	
			break;			
		case HOST_STOCK_MENU : hostProductMenu();		
			break;		
		case HOST_GOODS_LIST : host.productList(); //상품목록
			break;
		case HOST_GOODS_ADD : host.productAdd(); //상품추가
			break;
		case HOST_GOODS_UPDATE : host.productUpdate(); //상품수정
			break;
		case HOST_GOODS_DEL : host.productRemove(); //상품삭제
			break;	
		case USER_MENU : userView();
			break;
		case USER_JOIN : userJoin();
			break;
		case USER_MENU_LOGIN : userLogin();
			break;
		case USER_GOODS : 
			break;
		case USER_CART_LIST : user.cartList(userId);
			break;
		case USER_CART_ADD : user.cartAdd(userId);		
			break;			
		case USER_CART_DEL : user.cartRemove();		
			break;			
		default : break;		
		}
	}
	
	@Override
	public void loginMenu() {
		
		System.out.println("= = = = = = = = = = = = = = = = = = = =");
		System.out.println(" ----- * 죽집 에 오신걸 환영합니다.* ----- \n");
		System.out.println("[1].관리자 [2].회원로그인 [3].회원가입 [0].종료");
		System.out.println("= = = = = = = = = = = = = = = = = = = =\n");
		System.out.print("메뉴 번호를 입력해주세요 : ");
		String sel = scan.next();
		try {
			switch(Integer.parseInt(sel)) {
			case 1 : commonMenu(HOST_MENU_LOGIN); //관리자 로그인 화면
				break;
			case 2 : commonMenu(USER_MENU_LOGIN); //고객 로그인 화면 이
				break;
			case 3 : commonMenu(USER_JOIN); //회원가입 화면 이동
				break;
			case 0 :
				System.out.println(" 종료합니다. ");
				System.exit(0);
				break;
			default : 
				loginMenu();
				break;					
			}
		} catch(Exception e) {
			System.out.println(" * 오류입니다 다시 입력해주세요. ");
			loginMenu();
		}	
	}

	
	@Override
	public void hostLogin() {
		System.out.println(" * 아이디를 입력하세요 : ");
		String id = scan.next();
		System.out.println(" * 비밀번호를 입력하세요 :");
		String pw = scan.next();
		if(id.equals(hostImpl.getHostId()) && pw.equals(hostImpl.getHostPw())) {
			System.out.println(" * 관리자님 안녕하세요 * ");
			hostView();
		}else {
			System.out.println(" * 아이디 & 비밀번호 를 확인해주세요.");
			loginMenu();
		}
		
	}
	@Override
	public void userLogin() {
		System.out.println(" * 아이디를 입력하세요 : ");
		String id = scan.next();
		System.out.println(" * 비밀번호를 입력하세요 :");
		String pw = scan.next();
		if(UserProduct.join.containsKey(id) && pw.equals(UserProduct.join.get(id))) {
			System.out.println(" * "+id+"님 환영합니다. * ");
			this.userId = id;
			userView();
		}else {
			System.out.println(" * 아이디 & 비밀번호 를 확인해주세요.");
			loginMenu();
		}
		
	}

	@Override
	public void userJoin() {
		System.out.println(" [회원가입]");
		System.out.println(" * 아이디 를 입력하세요 :");
		String id = scan.next();
		
		if(!(UserProduct.join.containsKey(id))) {
			System.out.println(" * 비밀번호 입력 : ");
			String pw = scan.next();
			UserProduct.join.put(id, pw); // HashMap 에 아이디,비밀번호 추가		
		}else {
			System.out.println(" 중복된 아이디입니다.");
			loginMenu();
		}
		System.out.println(" 회원가입이 완료 되었습니다.");
		loginMenu();
	}


	@Override
	public void userView() {
		// TODO Auto-generated method stub
		System.out.println("\n= = = = = = = = = = = = = = = = = = = =");
		System.out.println(" ----- * 죽집 에 오신걸 환영합니다.* ----- \n");
		System.out.println("         [1].쇼핑      [2].로그아웃        ");
		System.out.println("= = = = = = = = = = = = = = = = = = = =\n");
		System.out.print("메뉴 번호를 입력해주세요 : ");
		String menuNo = scan.next();
		try {
			
			switch(Integer.parseInt(menuNo)) {
			
			case 1 : userCartMenu();
				break;
			case 2 : 
				userId = null;
				loginMenu();
				break;
			default : commonMenu(USER_MENU);
				break;
			}
		} catch(Exception e) {
			System.out.println(" 다시 입력해주세요. ");
			userView();
		}
	}
	
	@Override
	public void hostView() {
		System.out.println("\n= = = = = = = = = = = = = = = = = = = =");
		System.out.println(" ----------- * 관리자 전용 * ------------ \n");
		System.out.println("       [1].상품관리      [2].로그아웃       ");
		System.out.println("= = = = = = = = = = = = = = = = = = = =\n");
		System.out.print("메뉴 번호를 입력해주세요 : ");
		String menuNo = scan.next();
		try {
		
			switch(Integer.parseInt(menuNo)) {
			
			case 1 : commonMenu(HOST_STOCK_MENU);
				break;
			case 2 : loginMenu();
				break;
			default : commonMenu(HOST_MENU);
				break;
			}
		} catch(Exception e) {
			System.out.println(" 다시 입력해주세요. ");
			hostView();
		}
	}

	@Override
	public void hostProductMenu() {
		System.out.println("\n= = = = = = = = = = = = = = = = = = = =");
		System.out.println(" ----------- * 상 품 관 리 * ------------ \n");
		System.out.println("[1].제품목록 [2].제품추가 [3].제품수정 [4].제품삭제 [0].뒤로가기 ");
		System.out.print(" 메뉴를 선택하세요 : ");
		String menuNo = scan.next();
		try {
		
			switch(Integer.parseInt(menuNo)){
			
			case 1 : commonMenu (HOST_GOODS_LIST);
				break;
			case 2 : commonMenu (HOST_GOODS_ADD);
				break;
			case 3 : commonMenu (HOST_GOODS_UPDATE) ;
				break;
			case 4 : commonMenu (HOST_GOODS_DEL) ;
				break;
			case 0 : commonMenu(HOST_MENU);
				break;
			default : commonMenu (HOST_STOCK_MENU) ;
				break;
				
			}
		} catch(Exception e) {
			System.out.println(" 다시 입력해주세요.");
			commonMenu(HOST_STOCK_MENU);
		}		
	}

	@Override
	public void userCartMenu() {
		System.out.println("\n= = = = = = = = = = = = = = = = = = = =");
		System.out.println(" ----------- * 쇼 핑 메 인 * ------------ \n");
		System.out.println("[1].상품보기 [2].장바구니 [3].장바구니 삭제 [0].뒤로가기 ");
		System.out.print(" 메뉴를 선택하세요 : ");
		String menuNo = scan.next();
		try {
			
			switch(Integer.parseInt(menuNo)){
			
			case 1 : commonMenu (USER_CART_ADD);
				break;
			case 2 : commonMenu (USER_CART_LIST);
				break;
			case 3 : commonMenu (USER_CART_DEL);
				break;
			case 0 : commonMenu (USER_MENU);
				break;
			default : userCartMenu();
				break;			
			}
		} catch(Exception e) {
			System.out.println(" 다시 입력해주세요.");
			userCartMenu();
		}	
	}

}