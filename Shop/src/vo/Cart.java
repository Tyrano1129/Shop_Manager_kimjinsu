package vo;

public class Cart {
	String userId; // 구입한 유저 id
	String itemName; // 구입한 아이템
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@Override
	public String toString() {
		return userId + " " + itemName;
	}
	public Cart(String userId, String itemName) {
		this.userId = userId;
		this.itemName = itemName;
	}
	
}
