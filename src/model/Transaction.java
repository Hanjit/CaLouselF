package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.Database;

public class Transaction {
	private int userId;
	private int itemId;
	private int transactionId;
	private String itemName;
	private String itemPrice;
	
	public Transaction() {}
	
	public Transaction(int userId, int itemId, int transactionId, String itemName, String itemPrice) {
		this.userId = userId;
		this.itemId = itemId;
		this.transactionId = transactionId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}

	public boolean createTransaction(int userId, int itemId) {
		String query = "INSERT INTO `MsTransaction` VALUES('?', '?')";
		PreparedStatement ps = Database.getInstance().prepareStatement(query);
		
		try {
			ps.setInt(1, userId);
			ps.setInt(2, itemId);
			return ps.executeUpdate() == 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public ArrayList<Transaction> getTransaction(int userId) {
		ArrayList<Transaction> transactions = new ArrayList<>();
//		String query = "SELECT * FROM `MsTransaction` WHERE User_id LIKE ?";
		String query = String.format("SELECT Transaction_id, User_id, Item_id,  Item_name, Item_price "
				+ "FROM mstransaction mt"
				+ "JOIN msitem mi ON mt.Item_id = mi.Item_id"
				+ "WHERE mt.User_id = %d", userId);
		ResultSet rs = Database.getInstance().execQuery(query);
		
		try {
			while (rs.next()) {
				int transactionId = rs.getInt("Transaction_id");
				int itemId = rs.getInt("Item_id");
				String itemName = rs.getString("Item_name");
				String itemPrice = rs.getString("Item_price");
				transactions.add(new Transaction(userId, itemId, transactionId, itemName, itemPrice));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return transactions;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	};
	
}
