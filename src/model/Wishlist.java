package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.Database;

public class Wishlist {
	private int wishlistId;
	private int itemId;
	private int userId;
	private String itemName, itemSize, itemPrice, itemCategory;
		
	public Wishlist() {}
	
	public Wishlist(int itemId, int userId) {
		super();
		this.itemId = itemId;
		this.userId = userId;
	}

	public Wishlist(int wishlistId, int itemId, int userId) {
		super();
		this.wishlistId = wishlistId;
		this.itemId = itemId;
		this.userId = userId;
	}
	
	public Wishlist(int userId, int itemId, int wishlistId, String itemName, String itemSize, String itemPrice, String itemCategory) {
		this.userId = userId;
		this.itemId = itemId;
		this.wishlistId = wishlistId;
		this.itemName = itemName;
		this.itemSize = itemSize;
		this.itemPrice = itemPrice;
		this.itemCategory = itemCategory;
	}
	
    // Method yang digunakan untuk menambahkan wishlist
	public boolean addWishlist(int itemId, int userId) {
		String query = "INSERT INTO `mswishlist`(`Item_id`, `User_id`) VALUES (?, ?)";
		PreparedStatement ps = Database.getInstance().prepareStatement(query);
		try {
			ps.setInt(1, itemId);
			ps.setInt(2, userId);
    		return ps.executeUpdate() == 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	// Method yang digunakan untuk menghapus wishlist baik dihapus sendiri maupun sudah di checkout
	public boolean deleteWishlist(int wishlistId) {
		
		String query = "DELETE FROM `mswishlist` WHERE `Wishlist_id` = ?";
		PreparedStatement ps = Database.getInstance().prepareStatement(query);
		
		try {
			ps.setInt(1, wishlistId);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	// Method yang digunakan untuk menampilkan item apa saja yang di masuk kedalam wishlist
	public ArrayList<Wishlist> viewWishlist(int userId) {
		String query = String.format("SELECT msitem.Item_id, msitem.Item_name, msitem.Item_size, msitem.Item_price, msitem.Item_category FROM `mswishlist` JOIN msitem ON mswishlist.Item_id = msitem.Item_id WHERE 'User_id' = %d" , userId);
		ArrayList<Wishlist> wishlist = new ArrayList<>();
		ResultSet rs = Database.getInstance().execQuery(query);
		try {
			while(rs.next()) {
				int tid = rs.getInt("item_id");
				int wid = rs.getInt("Wishlist_id");
				String name = rs.getString("item_name");
				String size = rs.getString("item_size");
				String price = rs.getString("item_price");
				String category = rs.getString("item_category");
				wishlist.add(new Wishlist(userId, tid, wid, name, size, price, category));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return wishlist;
	}
	
	public int getWishlistId() {
		return wishlistId;
	}
	public void setWishlistId(int wishlistId) {
		this.wishlistId = wishlistId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
