package Utils;

import java.util.Scanner;


public class InnputManger {
	private static Scanner scan = new Scanner(System.in);
	
	public static int getValue(String msg,int start,int end) {
		int num = 0;
		while(true) {
			System.out.println(msg);
			try {
				num = scan.nextInt();
				if(num < start || num > end) {
					System.out.printf("[%d ~ %d] 사이 입력!%n",start,end);
					continue;
				}
				return num;
			}catch (Exception e) {
				System.out.println("숫자만 입력!");
			}finally {
				scan.nextLine();
			}
		}
		
	}
	
	public static String getValueString(String msg) {
		System.out.println(msg);
		return scan.next();
	}
	
	public static void getclose() {
		scan.close();
	}
}
