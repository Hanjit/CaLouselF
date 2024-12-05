package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.Database;

public class Transaction {
	private String userId;
	private String itemId;
	private String transactionId;
	private String itemName;
	private String itemPrice;
	
	public Transaction() {}
	
	public Transaction(String userId, String itemId, String transactionId, String itemName, String itemPrice) {
		this.userId = userId;
		this.itemId = itemId;
		this.transactionId = transactionId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}

	public boolean createTransaction(String userId, String itemId) {
		String query = "INSERT INTO `MsTransaction` VALUES('?', '?')";
		PreparedStatement ps = Database.getInstance().prepareStatement(query);
		
		try {
			ps.setString(1, userId);
			ps.setString(2, itemId);
			return ps.executeUpdate() == 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public ArrayList<Transaction> getTransaction(String userId) {
		ArrayList<Transaction> transactions = new ArrayList<>();
//		String query = "SELECT * FROM `MsTransaction` WHERE User_id LIKE ?";
		String query = String.format("SELECT Transaction_id, User_id, Item_id,  Item_name, Item_price "
				+ "FROM mstransaction mt"
				+ "JOIN msitem mi ON mt.Item_id = mi.Item_id"
				+ "WHERE mt.User_id = %s", userId);
		ResultSet rs = Database.getInstance().execQuery(query);
		
		try {
			while (rs.next()) {
				String transactionId = rs.getString("Transaction_id");
				String itemId = rs.getString("Item_id");
				String itemName = rs.getString("Item_name");
				String itemPrice = rs.getString("Item_price");
				transactions.add(new Transaction(userId, itemId, transactionId, itemName, itemPrice));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return transactions;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
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
