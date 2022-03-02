package service;

import java.util.HashMap;
import java.util.Map;

import model.Product;
import view.MenuImpl;

public class HostImpl implements Host{
	
	Map<Integer,Integer> codeList = new HashMap<Integer,Integer>();
	
	MenuImpl menu = MenuImpl.getInstance();
	//MenuImpl 화면으로 돌아가기 위함
	UserImpl userimpl;
	
	private int totalMoney;
	private String userId;
	
	
	private static HostImpl hostImpl = new HostImpl();
	
	public static HostImpl getInstance() {
		if(hostImpl == null) {
			
			hostImpl = new HostImpl();
		}
		return hostImpl;
	}
	
	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}
	public int getTotalMoney() {
		return totalMoney;
	}
	
	private static final String HOST_ID = "host"; //관리자 아이디값
	private static final String HOST_PW = "host";
	
	public static String getHostId() {
		return HOST_ID;
	}
	public static String getHostPw() {
		return HOST_PW;
	}

	@Override
	public void productList() {
		System.out.println("\n= = = = = = = = 상 품 목 록 = = = = = = = =");
		System.out.println("-----| 번호     이름     가격     수량 |-----");

		for(int key : Product.productMap.keySet()) {
			Product value = Product.productMap.get(key);
			System.out.println(value.toString());
		}
		System.out.println();
		
		menu.hostProductMenu();
		//메인 화면으로 돌아갑니다.
	}

	@Override
	public void productAdd() {
		System.out.print("* 상품 이름을 입력하세요 :");
		String productName = menu.scan.next();
		System.out.print("* 상품 개수를 입력하세요 :");
		int count = menu.scan.nextInt();
		System.out.print("* 상품 가격을 입력하세요 :");
		int price = menu.scan.nextInt();
		
		int productNo;
		while(true) {
			productNo = (int)(Math.random()*1000) + 1000; 
			if((false == codeList.containsKey(productNo))) {
				codeList.put(productNo, productNo);
				break;
			}// 랜덤으로 1000 ~ 2000 상품번호 생성
		}
		
		Product.productMap.put(productNo, new Product(productNo,price,count,productName));
		// hasnMap에 상품추가
		System.out.println("* 추가되었습니다.");
		
		menu.hostProductMenu();
		//메인 화면으로 돌아갑니다.
	}

	@Override
	public void productUpdate() {
	
		while(true) {
			System.out.print("* 수정하실 상품 번호를 입력해주세요.[0] 뒤로가기 :");
			int updateNo = menu.scan.nextInt();
			if(updateNo == 0) break;
			try {
				if(Product.productMap.containsKey(updateNo)) {
					//수정할 상품의 유/무 를 확인
				System.out.print("* [수정] 상품 이름 :");
				String updateName = menu.scan.next();
				System.out.print("* [수정] 가격 :");
				int price = menu.scan.nextInt();
				System.out.print("* [수정] 수량 :");
				int count = menu.scan.nextInt();
				
				Product.productMap.put(updateNo, new Product(updateNo,price,count,updateName));
				System.out.println("\n= = = = = = = = = = = = = = = = = = = =");
				System.out.println("-------------- 수정되었습니다 --------------");
				System.out.println("= = = = = = = = = = = = = = = = = = = =\n");
				System.out.println();
				}else {
					System.out.println("\n수정하실 상품 번호를 확인해주세요.\n");
				}
			}catch(Exception e) {
				System.out.println(" 다시 입력해주세요. ");
				menu.scan.next();
				productUpdate();
			}
			break;
		}
		
		menu.hostProductMenu();
		//메인 화면을 돌아갑니다.
	}
	
	@Override
	public void productRemove() {
	
		while(true) {
			System.out.print("* [삭제] 상품 번호를 입력해주세요.[0] 뒤로가기 :");
			int menuNo = menu.scan.nextInt();
			if(menuNo == 0) break;
			try	{
				if(Product.productMap.containsKey(menuNo)) {
					Product.productMap.remove(menuNo);
					System.out.println("\n= = = = = = = = = = = = = = = = = = = =");
					System.out.println("------------- 삭제 되었습니다. -------------");
					System.out.println("= = = = = = = = = = = = = = = = = = = =\n");
				}else {
					System.out.println("\n 삭제 하실 상품번호를 확인해주세요. \n");
				}
			} catch (Exception e) {
				System.out.println(" 다시 입력해주세요. ");
				productRemove();
			}
			break;	
		}
		menu.hostProductMenu();
		//메인 화면으로 돌아갑니다.
	}
}
