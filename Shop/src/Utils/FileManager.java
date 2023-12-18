package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import dao.ItemDAO;
import dao.UserDAO;

public class FileManager {
	final private static String CUR_PATE = System.getProperty("user.dir") +"\\src\\"+new FileManager().getClass().getPackageName() + "\\";
	
	private static void save(String data,String filename) {
		try (FileWriter fw = new FileWriter(CUR_PATE+filename)){
			fw.write(data);
			System.out.printf("%s파일 저장 성공%n",filename);
		} catch (IOException e) {
			System.out.printf("%s파일 저장 실패%n",filename);
		}
	}
	
	private static boolean fileCheck(String filename) {
		File file = new File(CUR_PATE+filename);
		if(!file.exists()) {
			System.out.printf("%s 파일을 찾지 못했습니다.%n",filename);
			return false;
		}else {
			return true;
		}
	}
	
	private static String load(String filename) {
		if(!fileCheck(filename)) {
			return null;
		}
		
		String data = "";
		try(FileReader fr = new FileReader(CUR_PATE+filename); 
			BufferedReader br = new BufferedReader(fr)){
			while(true) {
				String read = br.readLine();
				if(read == null) break;
				data += read + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(data.equals("")) {
			System.out.println("%s 데이터가 없습니다.%n");
			return null;
		}
		System.out.printf("%s 불러오기 완료%n",filename);
		return data;
	}
	
	public static void fileSave(ItemDAO item, UserDAO user) {
		String itemData = item.dataListitem();
		String userData = user.dataList();
		String cartData = item.dataListcart();
		if (!itemData.equals("")) {
			save(itemData, "item.txt");
		}
		if (!userData.equals("")) {
			save(userData, "user.txt");
		}
		if (!cartData.equals("")) {
			save(cartData, "cart.txt");
		}
		
	}
	public static void fileLoad(ItemDAO item, UserDAO user) {
		String itemdata = load("item.txt");
		String userdata = load("user.txt");
		String cartdata = load("cart.txt");
		
		if(itemdata != null) {
			item.itemListload(itemdata);
		}
		if(userdata != null) {
			user.userdataListLoad(userdata);
		}
		if(cartdata != null) {
			item.cartListload(cartdata);
		}
	}
  // cart.txt
	// user.txt
	// item.txt
}
