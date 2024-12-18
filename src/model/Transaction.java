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
	private String itemCategory;
	private String itemSize;

	private String itemPrice;
	
	public Transaction() {}
	
	// Constructor to initialize Transaction object with details
	public Transaction(int userId, int transactionId, String itemName, String itemCategory, String itemSize, String itemPrice) {
		this.userId = userId;
		this.transactionId = transactionId;
		this.itemName = itemName;
		this.itemCategory = itemCategory;
		this.itemSize = itemSize;
		this.itemPrice = itemPrice;
	}

	// Method to create a new transaction record in the database
	public boolean createTransaction(int userId, int itemId) {
		String query = "INSERT INTO `MsTransaction` (`User_id`, `Item_id`) VALUES(?, ?)";
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
	
	// Method to retrieve a list of transactions for a specific user
	public ArrayList<Transaction> getTransaction(int userId) {
		ArrayList<Transaction> transactions = new ArrayList<>();
//		String query = "SELECT * FROM `MsTransaction` WHERE User_id LIKE ?";
		String query = String.format("SELECT `Transaction_id`, `Item_name`, `Item_category`, `Item_size`, `Item_price` "
				+ "FROM mstransaction mt "
				+ "JOIN msitem mi ON mt.Item_id = mi.Item_id "
				+ "WHERE mt.User_id = %d", userId);
		ResultSet rs = Database.getInstance().execQuery(query);
		
		try {
			while (rs.next()) {
				int transactionId = rs.getInt("Transaction_id");
				String itemName = rs.getString("Item_name");
				String itemCategory = rs.getString("Item_category");
				String itemSize = rs.getString("Item_size");
				String itemPrice = rs.getString("Item_price");
				transactions.add(new Transaction(userId, transactionId, itemName, itemCategory, itemSize, itemPrice));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return transactions;
	}
	
	// Setter-Getter methods for the transaction class fields
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
	}
	
	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getItemSize() {
		return itemSize;
	}

	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}
	
}
