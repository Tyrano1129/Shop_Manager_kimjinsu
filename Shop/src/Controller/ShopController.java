package Controller;

import Utils.FileManager;
import Utils.InnputManger;
import dao.ItemDAO;
import dao.UserDAO;
import vo.User;

public class ShopController {

	private UserDAO userDAO;
	private User log;
	private ItemDAO itemDAO;

	public ShopController() {
		userDAO = new UserDAO();
		itemDAO = new ItemDAO();
	}
	
	private void mainMenu() {
		while (true) {
			System.out.println("[1.로그인]");
			System.out.println("[2.가입]");
			System.out.println("[0.종료]");
			int sel = InnputManger.getValue("메뉴 입력 : ", 0, 2);
			if (sel == 1) {
				System.out.println("[로그인]");
				log = userDAO.userlogin();
				if(log == null) continue;
				if (log.getId().equals("admin")) {
					adminMenu();
				}else {
					userMenu();
				}
			} else if (sel == 2) {
				System.out.println("[가입]");
				userDAO.userJoin();
			} else if (sel == 0) {
				System.out.println("종료...");
				break;
			}
		}
	}
	// 관리자 메뉴
	private void adminMenu() {
		while (true) {
			System.out.println("[관리자 메뉴]");
			System.out.println("[1.아이템관리]");
			System.out.println("[2.카테고리관리]");
			System.out.println("[3.장바구니관리]");
			System.out.println("[4.유저관리]");
			System.out.println("[5.데이터저장]");
			System.out.println("[6.데이터불러오기]");
			System.out.println("[0.뒤로가기]");

			int sel = InnputManger.getValue("메뉴 입력 : ", 0, 6);
			if (sel == 0) {
				System.out.println("뒤로가기...");
				log = null;
				System.out.println(log);
				return;
			} else if (sel == 1) {
				System.out.println("[아이템 관리]");
				itemAdminMenu();
			} else if (sel == 2) {
				System.out.println("[카테고리 관리]");
				categoryAdminMenu();
			} else if (sel == 3) {
				System.out.println("[장바구니 관리]");
				if(itemDAO.cartListAllPrint().equals("")) {
					System.out.println("카트에 아무런 정보가 없습니다.");
				}else {
					System.out.println(itemDAO.cartListAllPrint());
				}
			} else if (sel == 4) {
				System.out.println("[유저관리]");
				userAdminMenu();
			} else if(sel == 5) {
				System.out.println("[데이터저장]");
				FileManager.fileSave(itemDAO, userDAO);
			}else if(sel == 6) {
				System.out.println("[데이터불러오기]");
				FileManager.fileLoad(itemDAO, userDAO);
			}
		}
	}
	// 아이템 관리
	private void itemAdminMenu() {
		while (true) {
			System.out.println("[1. 아이템 추가]");
			System.out.println("[2. 아이템 삭제]");
			System.out.println("[3. 아이템 삽입]");
			System.out.println("[4. 아이템 수정]");
			System.out.println("[0. 뒤로가기]");

			int sel = InnputManger.getValue("메뉴 선택 : ", 0, 4);
			if (sel == 1) {
				System.out.println("[아이템 추가]");
				itemDAO.itemAdd();
			} else if (sel == 2) {
				System.out.println("[아이템 삭제]");
				itemDAO.itemDelete();
			} else if (sel == 3) {
				System.out.println("[아이템 삽입]");
				itemDAO.itemAdds();
			} else if (sel == 4) {
				System.out.println("[아이템 수정]");
				itemDAO.itemSet();
			} else if (sel == 0) {
				System.out.println("뒤로가기...");
				return;
			}

		}
	}
	// 카테고리 관리
	private void categoryAdminMenu() {
		while (true) {
			System.out.println("[1. 카테고리 추가]");
			System.out.println("[2. 카테고리 삭제]");
			System.out.println("[3. 카테고리 삽입]");
			System.out.println("[4. 카테고리 수정]");
			System.out.println("[0. 뒤로가기]");

			int sel = InnputManger.getValue("메뉴 선택 : ", 0, 4);
			if (sel == 1) {
				System.out.println("[카테고리 추가]");
				itemDAO.cateAdd();
			} else if (sel == 2) {
				System.out.println("[카테고리 삭제]");
				itemDAO.cateDelete();
			} else if (sel == 3) {
				System.out.println("[카테고리 삽입]");
				itemDAO.cateAdds();
			} else if (sel == 4) {
				System.out.println("[카테고리 수정]");
				itemDAO.cateSet();
			} else if (sel == 0) {
				System.out.println("뒤로가기...");
				return;
			}

		}
	}
	//유저 관리
	private void userAdminMenu() {
		while (true) {
			System.out.println("[1. 유저 삭제]");
			System.out.println("[2. 유저 삽입]");
			System.out.println("[3. 유저 수정]");
			System.out.println("[0. 뒤로가기]");
			int sel = InnputManger.getValue("메뉴 선택 : ", 0, 3);
			if (sel == 1) {
				System.out.println("[유저 삭제]");
				userDAO.userDelete(itemDAO);
			} else if (sel == 2) {
				System.out.println("[유저 삽입]");
				userDAO.userAdds();
			} else if (sel == 3) {
				System.out.println("[유저 수정]");
				userDAO.userSet(itemDAO);
			} else if (sel == 0) {
				System.out.println("뒤로가기...");
				return;
			}

		}
	}
	private void userMenu() {
		System.out.printf("[%s 님의 로그인]%n",log.getName());
		while (true) {
			System.out.println("[1. 쇼핑]");
			System.out.println("[2. 탈퇴]");
			System.out.println("[0. 로그아웃]");
			
			int sel = InnputManger.getValue("메뉴 선택 : ",0,2);
			
			if(sel == 1) {
				System.out.println("[쇼핑]");
				userOneShopMenu();
			}else if(sel == 2) {
				System.out.println("[탈퇴]");
				userDAO.oneUserDelete(log, itemDAO);
				log = null;
				return;
			}else if(sel == 0) {
				System.out.println("로그아웃...");
				log = null;
				return;
			}
		}
	}
	
	private void userOneShopMenu() {
		System.out.printf("[%s 님의 쇼핑]%n",log.getName());
		while(true) {
			System.out.println("[1. 구입]");
			System.out.println("[2. 삭제]");
			System.out.println("[3. 내 장바구니 목록]");
			System.out.println("[0. 뒤로가기]");
			
			int sel = InnputManger.getValue("메뉴 선택 : ",0,3);
			
			if(sel == 1) {
				System.out.println("[구입]");
				itemDAO.userOnebuy(log);
			}else if(sel == 2) {
				System.out.println("[삭제]");
				itemDAO.userOneCartDelete(log);
			}else if(sel == 3) {
				System.out.println("[내 장바구니 목록]");
				itemDAO.cartListOneUserPrint(log.getId());
			}else if(sel == 0) {
				System.out.println("뒤로가기...");
				return;
			}
		}
	}

	public void run() {
		mainMenu();
		InnputManger.getclose();
	}
	// System.out.println("[1.가입] [2.탈퇴] [3.로그인] [4.로그아웃]" + "\n[100.관리자] [0.종료] ");

	// System.out.println("[1.쇼핑] [2.장바구니목록] [0.뒤로가기]");

	// System.out.println("[1.내 장바구니] [2.삭제] [3.구입] [0.뒤로가기]");
}
// 	System.out.println("[1.아이템관리] [2.카테고리관리] [3.장바구니관리] [4.유저관리] [0.뒤로가기] ");