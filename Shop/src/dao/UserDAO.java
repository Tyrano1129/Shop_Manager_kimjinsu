package dao;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Utils.InnputManger;
import vo.User;

public class UserDAO {
	private ArrayList<User> userList;
	
	public UserDAO() {
		userList = new ArrayList<User>();
		userList.add(new User("admin","1234","관리자"));
	}
	private int checkIdidx(String id) {
		for(int i =0; i < userList.size(); i+=1) {
			if(userList.get(i).getId().equals(id)) {
				return i;
			}
		}
		return -1;
	}
	private void userAllPrint() {
		for(int i = 0; i < userList.size(); i+=1) {
			System.out.printf("(%d) %s%n",i+1,userList.get(i));
		}
	}
	//로그인
	public User userlogin() {
		if(userList.size() == 0) {
			System.out.println("유저가 없습니다...");
			return null;
		}
		String id = InnputManger.getValueString("아이디 입력 : ");
		int idx = checkIdidx(id);
		if(idx == -1) {
			System.out.println("입력하신 아이디가 없습니다.");
			return null;
		}
		
		String pw = InnputManger.getValueString("비밀번호 입력 : ");
		if(!userList.get(idx).getPw().equals(pw)) {
			System.out.println("비밀번호가 틀렸습니다.");
			return null;
		}
		
		return userList.get(idx);
	}// 패턴 아이디
	private boolean idpattern(String id) {
		String idPatter = "^[a-z]{1}[a-z0-9]{5,10}$";
		Pattern p = Pattern.compile(idPatter);
		Matcher m = p.matcher(id);
		if(m.matches()) {
			System.out.println("id 맞는 표현입니다.");
			return true;
		}
		System.out.println("id 틀린표현 표현입니다.");
		return false;
	}
	// 패턴 비밀번호
	private boolean pwpattern(String pw) {
		String pwPatter = "^[a-z0-9]{4,10}$";
		Pattern p = Pattern.compile(pwPatter);
		Matcher m = p.matcher(pw);
		if(m.matches()) {
			System.out.println("pw 맞는 표현입니다.");
			return true;
		}
		System.out.println("pw 틀린표현 표현입니다.");
		return false;
	}
	//가입 및 유저관리 유저 추가
	public void userJoin() {
		String id = InnputManger.getValueString("아이디 입력 : ");
		int idx = checkIdidx(id);
		if(idx != -1) {
			System.out.println("중복된 아이디입니다.");
			return;
		}
		if(!idpattern(id)) {
			return;
		}
		String pw = InnputManger.getValueString("비밀번호 입력 : ");
		if(!pwpattern(pw)) {
			return;
		}
		String name = InnputManger.getValueString("이름 입력 : ");
		
		userList.add(new User(id,pw,name));
	}
	//로그인후 유저 탈퇴
	public void oneUserDelete(User log, ItemDAO item) {
		int idx = checkIdidx(log.getId());
		item.cartListOneUserDelect(log.getId());
		userList.remove(idx);
		System.out.printf("%s 님 탈퇴 되었습니다.%n",log.getId());
	}
	//유저관리 유저 삭제
	public void userDelete(ItemDAO item) {
		if(userList.size() == 0) {
			System.out.println("유저가 없습니다...");
			return;
		}
		userAllPrint();
		int idx = InnputManger.getValue("유저 선택 입력 : ",1,userList.size())-1;
		if (userList.get(idx).getId().equals("admin")) {
			System.out.println("관리자는 삭제 못합니다...");
			return;
		}
		item.cartListOneUserDelect(userList.get(idx).getId());
		userList.remove(idx);
	}
	//유저관리 유저 삽입
	public void userAdds() {
		if(userList.size() == 0) {
			System.out.println("유저가 없습니다...");
			return;
		}
		userAllPrint();
		String id = InnputManger.getValueString("아이디 입력 : ");
		int idx = checkIdidx(id);
		if(idx != -1) {
			System.out.println("중복된 아이디입니다.");
			return;
		}
		idx = InnputManger.getValue("삽일할 위치 선택 입력 : ",1,userList.size())-1;
		if (userList.get(idx).getId().equals("admin")) {
			System.out.println("관리자 위치에 삽입 못합니다...");
			return;
		}
		String pw = InnputManger.getValueString("비밀번호 입력 : ");
		String name = InnputManger.getValueString("이름 입력 : ");
		userList.add(idx,new User(id,pw,name));
	}
	//유저관리 유저 수정
	public void userSet(ItemDAO item) {
		if(userList.size() == 0) {
			System.out.println("유저가 없습니다...");
			return;
		}
		userAllPrint();
		int idx = InnputManger.getValue("유저 선택 입력 : ",1,userList.size())-1;
		if (userList.get(idx).getId().equals("admin")) {
			System.out.println("관리자는 수정 못합니다...");
			return;
		}
		System.out.println("[1. 아이디 수정]");
		System.out.println("[2. 비밀번호 수정]");
		System.out.println("[3. 이름 수정]");
		int sel = InnputManger.getValue("메뉴 선택 : ", 1, 3);
		if (sel == 1) {
			String id = InnputManger.getValueString("수정 아이디 입력 : ");
			item.cartListOneUserSet(id);
			userList.get(idx).setId(id);
		} else if (sel == 2) {
			String pw = InnputManger.getValueString("수정 아이디 입력 : ");
			userList.get(idx).setPw(pw);
		} else if (sel == 3) {
			String name = InnputManger.getValueString("수정 아이디 입력 : ");
			userList.get(idx).setName(name);
		}
	}
	// 저장
	public String dataList() {
		String data = "";
		for(User user : userList) {
			if(user.getName().equals("관리자")) continue;
			data += user.getId() + "/" + user.getPw() + "/" + user.getName() + "\n";
		}
		return data;
	}
	
	// 로드
	public void userdataListLoad(String data) {
		String[] temp = data.split("\n");
		for(int i = 0; i < temp.length; i+=1) {
			String[] info = temp[i].split("/");
			userList.add(new User(info[0],info[1],info[2]));
		}
	}
}
