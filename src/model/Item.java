package model;

import java.sql.ResultSet;
import java.util.ArrayList;

import connection.Database;

public class Item {
	private int itemId;
	private String itemName;
	private String itemSize;
	private String itemPrice;
	private String itemCategory;
	private String itemStatus;
	private String itemWishlist;
	private String itemOfferStatus;
	
	public Item() {}
	
	public Item(int itemId, String itemName, String itemSize, String itemPrice, String itemCategory) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemSize = itemSize;
		this.itemPrice = itemPrice;
		this.itemCategory = itemCategory;
	}

	public Item(int itemId, String itemName, String itemSize, String itemPrice, String itemCategory,
			String itemStatus, String itemWishlist, String itemOfferStatus) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemSize = itemSize;
		this.itemPrice = itemPrice;
		this.itemCategory = itemCategory;
		this.itemStatus = itemStatus;
		this.itemWishlist = itemWishlist;
		this.itemOfferStatus = itemOfferStatus;
	}
	
	public ArrayList<Item> getItems() {
		ArrayList<Item> items = new ArrayList<>();
		String query = "SELECT * FROM `MsItem` WHERE item_status LIKE 'Approved'";
		ResultSet rs = Database.getInstance().execQuery(query);
		try {
			while (rs.next()) {
				int id = rs.getInt("item_id");
				String name = rs.getString("item_name");
				String size = rs.getString("item_size");
				String price = rs.getString("item_price");
				String category = rs.getString("item_category");
				items.add(new Item(id, name, size, price, category));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return items;
	}
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemSize() {
		return itemSize;
	}
	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	public String getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	public String getItemWishlist() {
		return itemWishlist;
	}
	public void setItemWishlist(String itemWishlist) {
		this.itemWishlist = itemWishlist;
	}
	public String getItemOfferStatus() {
		return itemOfferStatus;
	}
	public void setItemOfferStatus(String itemOfferStatus) {
		this.itemOfferStatus = itemOfferStatus;
	}
	
}
