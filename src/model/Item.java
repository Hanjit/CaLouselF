package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.Database;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Item {
	private int itemId;
	private int sellerId;
	private String itemName;
	private String itemSize;
	private String itemPrice;
	private String itemCategory;
	private String itemStatus;
	private String itemWishlist;
	private String itemOfferStatus;
	private String itemReason = "";
	
	public Item() {}
	
	public Item(int itemId, String itemReason) {
		this.itemId = itemId;
		this.itemReason = itemReason;
	}
	
	public Item(int itemId, String itemName, String itemSize, String itemPrice, String itemCategory) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemSize = itemSize;
		this.itemPrice = itemPrice;
		this.itemCategory = itemCategory;
	}
	
	public Item(int itemId, String itemName, String itemSize, String itemPrice, String itemCategory, String itemStatus, String itemReason) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemSize = itemSize;
		this.itemPrice = itemPrice;
		this.itemCategory = itemCategory;
		this.itemStatus = itemStatus;
		this.itemReason = itemReason;
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
	
	public boolean createItem(String itemName, String itemSize, String itemPrice, String itemCategory, String itemWishlist, String itemOfferStatus, int sellerId) {
		
		String query = "INSERT INTO `MsItem` (`Item_name`, `Item_size`, `Item_price`, `Item_category`, `Item_status`, `Item_wishlist`, `Item_offer_status`, `Seller_id`) "
				+ "VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement ps = Database.getInstance().prepareStatement(query);
		
		try {
			ps.setString(1, itemName);
			ps.setString(2, itemSize);
			ps.setString(3, itemPrice);
			ps.setString(4, itemCategory);
			ps.setString(5, "Pending");
			ps.setString(6, itemWishlist);
			ps.setString(7, itemOfferStatus);
			ps.setInt(8, sellerId);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	// for seller
	public ArrayList<Item> getAllItems(int sellerId) {
		ArrayList<Item> items = new ArrayList<>();
		String query = String.format("SELECT * FROM `MsItem` WHERE `Seller_id` = %d", sellerId);
		ResultSet rs = Database.getInstance().execQuery(query);
		try {
			while (rs.next()) {
				int id = rs.getInt("item_id");
				String name = rs.getString("item_name");
				String size = rs.getString("item_size");
				String price = rs.getString("item_price");
				String category = rs.getString("item_category");
				String status = rs.getString("Item_status");
				String reason = rs.getString("Item_reason");
				items.add(new Item(id, name, size, price, category, status, reason));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return items;
	}
	
	// for buyer
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
	
	// For admin
	public ArrayList<Item> getRequestedItems() {
		ArrayList<Item> items = new ArrayList<>();
		String query = "SELECT * FROM `MsItem` WHERE `Item_status` LIKE 'Pending'";
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
	
	// for seller declined items alert
	public ArrayList<Item> getDeclinedItems(int sellerId) {
		ArrayList<Item> items = new ArrayList<>();
		String query = String.format("SELECT * FROM `MsItem` WHERE `Seller_id` = %d AND `Item_status` LIKE 'Declined'", sellerId);
		ResultSet rs = Database.getInstance().execQuery(query);
		
		try {
			while (rs.next()) {
				int itemId = rs.getInt("Item_id");
				String itemReason = rs.getString("Item_reason");
				items.add(new Item(itemId, itemReason));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return items;
	}
	
	public Item getItemById(int itemId) {
		Item item = new Item();
		String query = String.format("SELECT * "
				+ "FROM `MsItem` "
				+ "WHERE `Item_id` LIKE %d", itemId);
		ResultSet rs = Database.getInstance().execQuery(query);
		
		try {
			if (rs.next()) {
				String itemName = rs.getString("Item_name");
				String itemSize = rs.getString("Item_size");
				String itemPrice = rs.getString("Item_price");
				String itemCategory = rs.getString("Item_category");
				item.setItemName(itemName);
				item.setItemPrice(itemPrice);
				item.setItemCategory(itemCategory);
				item.setItemSize(itemSize);
				return item;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean updateItem(int itemId, String itemName, String itemSize, String itemPrice, 
			String itemCategory) {
		
		String query = "UPDATE `MsItem`"
				+ "SET `Item_name` = ?, `Item_size` = ?, `Item_price` = ?, `Item_category` = ? "
				+ "WHERE `Item_id` = ?";
		PreparedStatement ps = Database.getInstance().prepareStatement(query);
		
		try {
			ps.setString(1, itemName);
			ps.setString(2, itemSize);
			ps.setString(3, itemPrice);
			ps.setString(4, itemCategory);
			ps.setInt(5, itemId);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean deleteItem(int itemId) {
		
		String query = "DELETE FROM `msitem` WHERE `item_id` = ?";
		PreparedStatement ps = Database.getInstance().prepareStatement(query);
		
		try {
			ps.setInt(1, itemId);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean approveItem(int itemId) {
		String query = "UPDATE `MsItem`"
				+ "SET `Item_status` = 'Approved'"
				+ "WHERE `Item_id` = ?";
		PreparedStatement ps = Database.getInstance().prepareStatement(query);
		
		try {
			ps.setInt(1, itemId);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean declineItem(int itemId, String itemReason) {
		String query = "UPDATE `MsItem`"
				+ "SET `Item_status` = 'Declined', `Item_reason` = ?"
				+ "WHERE `Item_id` = ?";
		PreparedStatement ps = Database.getInstance().prepareStatement(query);
		
		try {
			ps.setString(1, itemReason);
			ps.setInt(2, itemId);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
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

	public String getItemReason() {
		return itemReason;
	}

	public void setItemReason(String itemReason) {
		this.itemReason = itemReason;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	
}
