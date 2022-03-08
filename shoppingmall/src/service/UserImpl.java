package service;

import java.util.HashMap;
import java.util.Map;

import model.Product;
import model.UserProduct;
import view.MenuImpl;

public class UserImpl implements User{
	
	MenuImpl menu = MenuImpl.getInstance();
	HostImpl hostimpl = HostImpl.getInstance();
	
	String userId;
	String userPw;
	
	private UserImpl() {}
	
	public UserImpl(String userId, String userPw) {
		this.userId = userId;
		this.userPw = userPw;
	}
	
	private static UserImpl userImpl = new UserImpl();
	
	public static UserImpl getInstance() {
		if(userImpl == null) {
			userImpl = new UserImpl();
		}
		return userImpl;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	
	public String getUserPw() {
		return userPw;
	}	

	@Override
	public void cartList() {
		System.out.println("\n= = = = = = = = 상 품 목 록 = = = = = = = =");
		System.out.println("-----| 번호     이름     가격     수량 |-----\n");
		for(int key : UserProduct.userMap.keySet()) {//장바구니 안의 상품들
			if(userId.equals(UserProduct.userMap.get(key).getUserId())) {
				int price = UserProduct.userMap.get(key).getPrice();
				int count = UserProduct.userMap.get(key).getCount();
				UserProduct value = UserProduct.userMap.get(key);
				System.out.println(value.toString());
				
				System.out.println("       가격 : "+price*count+" 원       ");
			}
		}
		menu.userCartMenu();
		//메인화면으로 돌아갑니다.
	}
	@Override
	public void cartAdd() {

		System.out.println("\n= = = = = = = = 상 품 목 록 = = = = = = = =");
		System.out.println("-----| 번호     이름     가격     수량 |-----\n");

		for(int key : Product.productMap.keySet()) {
			Product value = Product.productMap.get(key);
			System.out.println(value.toString());
		}//전체 상품리스트
		System.out.println();
		
		while(true) {
			System.out.println("* 장바구니 추가하실 상품 번호를 입력해 주세요.[0] 이전화면");
			System.out.print("상품 번호 : ");
			int menuNo = menu.scan.nextInt();
			if(menuNo == 0) break;
			try {
				
				if(Product.productMap.containsKey(menuNo)) {
					System.out.println("구매할 상품의 수량을 입력하세요 :");
					int count = menu.scan.nextInt();
					String name = Product.productMap.get(menuNo).getProductName();
					int price = Product.productMap.get(menuNo).getPrice();
					
					//this.userId = userId;
					//상품 담은후 갯수 수정
					int totalCount = Product.productMap.get(menuNo).getCount();
					//상품갯수 빼주기  *
					if(count <= 0 || totalCount - count < 0) {
						System.out.println("수량을 확인해주세요.");
					}else {
						Product.productMap.put(menuNo, new Product(menuNo,price,totalCount-count,name));
						UserProduct.userMap.put(menuNo, new UserProduct(menuNo, price, count, name, userId));
						System.out.println("\n= = = = = = = = = = = = = = = = = = = =");
						System.out.println("-------- 장바구니 에 추가 되었습니다. --------");
						System.out.println("= = = = = = = = = = = = = = = = = = = =\n");		
					}		
					menu.userCartMenu();
				}else {
					System.out.println("잘못 입력하셨습니다.");
					cartAdd();
				}		
				
			} catch(Exception e) {
				System.out.println("ff 다시 입력해주세요.");
				cartList();
			}
			
			break;
		}
		
		menu.userCartMenu();
		//메인화면으로 돌아갑니다.
	}

	@Override
	public void cartRemove() {
		
		while(true) {
			System.out.println("* 삭제하실 상품 번호를 입력해주세요. [0] 이전화면");
			int menuNo = menu.scan.nextInt();
			if(menuNo == 0) break;
			
			try {
				if(UserProduct.userMap.containsKey(menuNo)) {
					int count = UserProduct.userMap.get(menuNo).getCount();
					//삭제할 경우 카운트를 다시 상품에 더해줍니다.
					int totalCount = Product.productMap.get(menuNo).getCount();
					int price = Product.productMap.get(menuNo).getPrice();
					String name = Product.productMap.get(menuNo).getProductName();
					//상품갯수 
					Product.productMap.put(menuNo, new Product(menuNo,price,totalCount+count,name));
					
					UserProduct.userMap.remove(menuNo);
					System.out.println("\n= = = = = = = = = = = = = = = = = = = =");
					System.out.println("-------- 장바구니 에서 삭제 되었습니다. --------");
					System.out.println("= = = = = = = = = = = = = = = = = = = =\n");
				}
			} catch(Exception e) {
				System.out.println(" 다시 입력해주세요.");
				menu.scan.next();
				cartRemove();
			}
			break;
		}
			
		menu.userCartMenu();
		//메인화면으로 돌아갑니다.
	}
}