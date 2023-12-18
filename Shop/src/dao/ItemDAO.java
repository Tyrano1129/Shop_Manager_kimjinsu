package dao;

import java.util.ArrayList;

import Utils.InnputManger;
import vo.Cart;
import vo.Item;
import vo.User;


public class ItemDAO {
	private ArrayList<Cart> cartList;
	private ArrayList<String> category;
	private ArrayList<Item> itemList;
	
	public ItemDAO(){
		cartList = new ArrayList<Cart>();
		itemList = new ArrayList<Item>();
		category = new ArrayList<String>();
	}
	
	private int checkitemIdx(String name) {
		for(int i = 0; i < itemList.size(); i+=1) {
			if(itemList.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
	private int checkcateidx(String name) {
		for(int i = 0; i < category.size(); i+=1) {
			if(category.get(i).equals(name)) {
				return i;
			}
		}
		return -1;
	}
	//카테고리 전체 출력
	private void printCategory() {
		for(int i = 0; i < category.size(); i+=1) {
			System.out.printf("(%d) %s%n",i+1,category.get(i));
		}
	}
	//카테고리 전체 아이템 전체출력
	private void categoryAllItemAllprint() {
		for(int i = 0; i < category.size(); i+=1) {
			System.out.printf("(%d) %s%n",i+1,category);
			for(int k = 0; k < itemList.size(); k+=1) {
				if(category.get(i).equals(itemList.get(k).getCategory())) {
				System.out.println(itemList.get(k));
				}
			}
		}
	}
	//카테고리 하나 아이템 전체출력
	private void categoryOneItemAllprint(int cate) {
		for(int k = 0; k < itemList.size(); k+=1) {
			if(category.get(cate).equals(itemList.get(k).getCategory())) {
				System.out.printf("%s %n",itemList.get(k));
			}
		}
	}
	//아이템 temp 출력
	private void itemListAllPrint(ArrayList<Item> itemlist) {
		for(int i = 0; i < itemlist.size(); i+=1) {
			System.out.printf("(%d) %s%n",i+1,itemlist.get(i));
		}
	}
	//아이템 temp 
	private ArrayList<Item> categoryOneItemList(int cate){
		ArrayList<Item> temp = new ArrayList<Item>();
		for(int k = 0; k < itemList.size(); k+=1) {
			if(category.get(cate).equals(itemList.get(k).getCategory())) {
				temp.add(itemList.get(k));
			}
		}
		return temp;
	}
	//카테고리 삭제후 아이템 전체삭제
	private void cateOneItemAllDelete(int cateidx) {
		for(int i = 0; i < itemList.size(); i+=1) {
			if(itemList.get(i).getCategory().equals(category.get(cateidx))) {
				cateOneCartAllDelete(itemList.get(i).getName());
				itemList.remove(i);
				i-=1;
			}
		}
	}
	//아이템 삭제후 카트 아이템 삭제
	private void cateOneCartAllDelete(String itemname) {
		for(int i = 0; i < cartList.size(); i+=1) {
			if(cartList.get(i).getItemName().equals(itemname)) {
				cartList.remove(i);
				i-=1;
			}
		}
	}
	//아이템 수정후 카트 수정
	private void cateOneCartAllSet(String name,String setName) {
		for(int i = 0; i < cartList.size(); i+=1) {
			if(cartList.get(i).getItemName().equals(name)) {
				cartList.get(i).setItemName(setName);
				i-=1;
			}
		}
	}
	//카테고리 수정후 같은 이름 바꾸기
	private void cateOneItemAllSet(String setName,String name) {
		for(int i = 0; i < itemList.size(); i+=1) {
			if(itemList.get(i).getCategory().equals(setName)) {
				itemList.get(i).setCategory(name);
				i-=1;
			}
		}
	}
	//장바구니 전체 출력
	public String cartListAllPrint() {
		String data = "";
		for(int i = 0; i < cartList.size(); i+=1) {
			data += cartList.get(i) + "\n";
		}
		return data;
	}
	//장바구니 유저 한명의 장바구니 출력
	public void cartListOneUserPrint(String name) {
		int cnt = 0;
		int sum = 0;
		for(int i = 0; i < cartList.size(); i+=1) {
			if(cartList.get(i).getUserId().equals(name)) {
				System.out.println(cartList.get(i));
				for(int k = 0; k < itemList.size(); k+=1) {
					if(cartList.get(i).getItemName().equals(itemList.get(k).getName())) {
						sum += itemList.get(k).getPrice();
						break;
					}
				}
				cnt+=1;
			}
		}
		if(cnt == 0) {
			System.out.println("장바구니가 비웠습니다.");
			return;
		}else {
			System.out.printf("총금액 : %d%n",sum);
		}
	}
	//유저 한명의 장바구니
	private ArrayList<Cart> userOneCart(String id){
		ArrayList<Cart> temp = new ArrayList<Cart>();
		for(int i =0 ;i < cartList.size(); i+=1) {
			if(cartList.get(i).getUserId().equals(id)) {
				temp.add(cartList.get(i));
			}
		}
		return temp;
	}
	//유저 한명의 장바구니 출력
	private void userOneCartPrint(ArrayList<Cart> cart){
		for(int i =0 ;i < cartList.size(); i+=1) {
			System.out.printf("(%d) %s%n",i+1,cart.get(i));
		}
	}
	//유저 장바구니 하나 지우기
	private void userOneCartDelect(Cart cart) {
		for(int i =0; i < cartList.size(); i+=1) {
			if(cart.getItemName().equals(cartList.get(i).getItemName()) && cart.getUserId().equals(cartList.get(i).getUserId())) {
				cartList.remove(i);
				return;
			}
		}
	}
	//장바구니 아이템 하나의 장바구니 출력
	private void cartListOneItemPrint(String name) {
		for(int i = 0; i < cartList.size(); i+=1) {
			if(cartList.get(i).getItemName().equals(name)) {
				System.out.println(cartList.get(i));
			}
		}
	}
	// 유저 탈퇴 및 삭제후 장바구니 삭제
	public void cartListOneUserDelect(String id) {
		for(int i = 0; i < cartList.size(); i+=1) {
			if(cartList.get(i).getUserId().equals(id)) {
				cartList.remove(i);
				i-=1;
			}
		}
		cartListAllPrint();
	}
	// 유저 수정 후 장바구니 수정
	public void cartListOneUserSet(String id) {
		for(int i = 0; i < cartList.size(); i+=1) {
			if(cartList.get(i).getUserId().equals(id)) {
				cartList.get(i).setUserId(id);
			}
		}
		cartListAllPrint();
	}
	//카테고리 추가
	public void cateAdd() {
		String catename = InnputManger.getValueString("카테고리 이름 입력 : ");
		int cateidx = checkcateidx(catename);
		if(cateidx != -1) {
			System.out.println("카테고리가 중복입니다.");
			return;
		}
		category.add(new String(catename));
		printCategory();
	}
	//카테고리 삭제
	public void cateDelete() {
		if(category.size() == 0) {
			System.out.println("카테고리 먼저 추가후에 이용해주세요.");
			return;
		}
		printCategory();
		int cateidx = InnputManger.getValue("카테고리 선택 : ",1,category.size())-1;
		cateOneItemAllDelete(cateidx);
		category.remove(cateidx);
	}
	//카테고리 삽입
	public void cateAdds() {
		if(category.size() == 0) {
			System.out.println("카테고리 먼저 추가후에 이용해주세요.");
			return;
		}
		printCategory();
		String catename = InnputManger.getValueString("카테고리 이름 입력 : ");
		int cateidx = checkcateidx(catename);
		if(cateidx != -1) {
			System.out.println("카테고리가 중복입니다.");
			return;
		}
		cateidx = InnputManger.getValue("카테고리 삽입위치 선택 : ",1,category.size())-1;
		category.add(cateidx,new String(catename));
		printCategory();
	}
	//카테고리 수정
	public void cateSet() {
		if(category.size() == 0) {
			System.out.println("카테고리 먼저 추가후에 이용해주세요.");
			return;
		}
		printCategory();
		int cateidx = InnputManger.getValue("카테고리 선택 : ",1,category.size())-1;
		if(cateidx != -1) {
			System.out.println("카테고리가 중복입니다.");
			return;
		}
		String name = InnputManger.getValueString("수정할 이름 입력 : ");
		if(category.get(cateidx).equals(name)) {
			System.out.println("같은이름 입니다.");
			return;
		}
		cateOneItemAllSet(category.get(cateidx),name);
		category.set(cateidx,name);
		printCategory();
	}
	//아이템 추가
	public void itemAdd() {
		if(category.size() == 0) {
			System.out.println("카테고리 먼저 추가후에 이용해주세요.");
			return;
		}
		printCategory();
		int cateidx = InnputManger.getValue("카테고리 선택 : ",1,category.size())-1;
		String catename = category.get(cateidx);
		String name = InnputManger.getValueString("아이템 이름 입력 : ");
		int idx = checkitemIdx(name);
		if(idx != -1) {
			System.out.println("중복된 아이템 이름입니다.");
			return;
		}
		int price = InnputManger.getValue("아이템 가격 입력 : ",100,10000);
		itemList.add(new Item(name,price,catename));
		categoryOneItemAllprint(cateidx);
	}
	//아이템 삭제
	public void itemDelete() {
		if(category.size() == 0) {
			System.out.println("카테고리 먼저 추가후에 이용해주세요.");
			return;
		}
		printCategory();
		int cateidx = InnputManger.getValue("카테고리 선택 : ",1,category.size())-1;
		categoryOneItemAllprint(cateidx);
		String name = InnputManger.getValueString("삭제할 아이템 입력 : ");
		int idx = checkitemIdx(name);
		if(idx == -1) {
			System.out.println("입력한 아이템이 없습니다.");
			return;
		}
		cateOneCartAllDelete(itemList.get(idx).getName());
		itemList.remove(idx);
	}
	//아이템 삽입
	public void itemAdds() {
		if(category.size() == 0) {
			System.out.println("카테고리 먼저 추가후에 이용해주세요.");
			return;
		}
		printCategory();
		int cateidx = InnputManger.getValue("카테고리 선택 : ",1,category.size())-1;
		categoryOneItemAllprint(cateidx);
		String catename = category.get(cateidx);
		String name = InnputManger.getValueString("삽일할 아이템 이름 입력 : ");
		int price = InnputManger.getValue("아이템 가격 입력 : ",100,10000);
		String addsname = InnputManger.getValueString("삽일할위치 아이템 이름 입력 : ");
		int idx = checkitemIdx(addsname);
		if(idx == -1) {
			System.out.println("입력한 아이템이 없습니다.");
			return;
		}
		itemList.add(idx,new Item(name,price,catename));
		categoryOneItemAllprint(cateidx);
	}
	//아이템 수정
	public void itemSet() {
		if(category.size() == 0) {
			System.out.println("카테고리 먼저 추가후에 이용해주세요.");
			return;
		}
		printCategory();
		int cateidx = InnputManger.getValue("카테고리 선택 : ",1,category.size())-1;
		categoryOneItemAllprint(cateidx);
		String setname = InnputManger.getValueString("수정할 아이템 이름 입력 : ");
		int idx = checkitemIdx(setname);
		if(idx == -1) {
			System.out.println("입력한 아이템이 없습니다.");
			return;
		}
		System.out.println("[1. 이름수정]");
		System.out.println("[2. 가격수정]");
		int sel = InnputManger.getValue("메뉴 입력 : ", 1, 2);
		if (sel == 1) {
			String name = InnputManger.getValueString("수정할 아이템 이름 입력 : ");
			cateOneCartAllSet(name,itemList.get(idx).getName());
			itemList.get(idx).setName(name);
		} else if (sel == 2) {
			int price = InnputManger.getValue("수정할아이템 가격 입력 : ",100,10000);
			itemList.get(idx).setPrice(price);
		}
		categoryOneItemAllprint(cateidx);
		
	}
	// 아이템 구입
	public void userOnebuy(User id) {
		printCategory();
		int cateidx = InnputManger.getValue("카테고리 선택 : ",1,category.size())-1;
		ArrayList<Item> temp = categoryOneItemList(cateidx);
		while(true) {
			itemListAllPrint(temp);
			int sel = InnputManger.getValue("아이템 선택 : (0.뒤로가기)",0,temp.size())-1;
			if(sel == -1) {
				System.out.println("나가기");
				return;
			}
			cartList.add(new Cart(id.getId(),temp.get(sel).getName()));
		}
	}
	// 장바구니 삭제
	public void userOneCartDelete(User id) {
		ArrayList<Cart> temp = userOneCart(id.getId());
		userOneCartPrint(temp);
		int idx = InnputManger.getValue("장바구니 삭제 선택 : ",1,temp.size())-1;
		Cart carttemp = temp.get(idx);
		userOneCartDelect(carttemp);
	}
	// item data 저장
	public String dataListitem() {
		String data = "";
		for(Item item : itemList) {
			data += item.getName() + "/" + item.getPrice() + "/" + item.getCategory() + "\n";
		}
		return data;
	}
	// cart data 저장
	public String dataListcart() {
		String data = "";
		for(Cart cart : cartList) {
			data += cart.getUserId() + "/" + cart.getItemName() + "\n";
		}
		return data;
	}
	// item data 로드
	public void itemListload(String data) {
		String[] temp = data.split("\n");
		for(int i = 0; i < temp.length; i+=1) {
			String[] info = temp[i].split("/");
			itemList.add(new Item(info[0],Integer.parseInt(info[1]),info[2]));
			category.add(info[2]);
		}
		//중복되지않게 카테고리 넣기
		for(int i = 0; i < category.size(); i+=1) {
			for(int k = i; k < category.size(); k+=1) {
				if(category.get(i).equals(category.get(k)) && i != k) {
					category.remove(k);
					k-=1;
				}
			}
		}
	}
	// cart data 로드
	public void cartListload(String data) {
		String[] temp = data.split("\n");
		for(int i = 0; i < temp.length; i+=1) {
			String[] info = temp[i].split("/");
			cartList.add(new Cart(info[0],info[1]));
		}
	}
}
