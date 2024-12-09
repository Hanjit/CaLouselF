package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.Database;

public class Offer {
	private int offerId;
	private int userId;
	private int itemId;
	private int sellerId;
	private int itemPrice;
	private String itemCategory;
	private String itemSize;
	private String itemName;
	private String offerStatus;
	private String offerReason;
	private int offerPrice;
	
	public Offer() {}
	
	public Offer(int offerId, String offerReason) {
		this.offerId = offerId;
		this.offerReason = offerReason;
	}
	
	public Offer(int offerId, int userId, int itemId, String itemName, int itemPrice, int offerPrice, String offerStatus, String offerReason, 
			String itemCategory, String itemSize) {
		super();
		this.offerId = offerId;
		this.userId = userId;
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.offerPrice = offerPrice;
		this.offerStatus = offerStatus;
		this.offerReason = "";
		this.itemCategory = itemCategory;
		this.itemSize = itemSize;
	}
	
	public boolean createOffer(int userId, int itemId, int sellerId, int offerPrice) {
		
		String query = "INSERT INTO `MsOffer` (`User_id`, `Item_id`, `Seller_id`, `Offer_status`, `Offer_reason`, `Offer_price`) "
				+ "VALUES (?,?,?,?,?,?)";
		PreparedStatement ps = Database.getInstance().prepareStatement(query);
		
		try {
			ps.setInt(1, userId);
			ps.setInt(2, itemId);
			ps.setInt(3, sellerId);
			ps.setString(4, "Waiting");
			ps.setString(5, "");
			ps.setInt(6, offerPrice);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	// for seller
	public ArrayList<Offer> getOffer(int sellerId) {
		ArrayList<Offer> offers = new ArrayList<>();
		String query = String.format("SELECT `Offer_id`, `User_id`, mo.`Item_id`, `Item_price`, `Offer_price`, `Offer_status`, `Offer_reason`, `Item_name`, `Item_category`, `Item_size` "
				+ "FROM `MsOffer` mo "
				+ "JOIN `MsItem` mi ON mo.Item_id = mi.Item_id "
				+ "WHERE mo.Seller_id = %d "
				+ "ORDER BY `Offer_status`", sellerId);
		ResultSet rs = Database.getInstance().execQuery(query);
		
		try {
			while (rs.next()) {
				int offerId = rs.getInt("Offer_id");
				int userId = rs.getInt("User_id");
				int itemId = rs.getInt("Item_id");
				int itemPrice = rs.getInt("item_price");
				int offerPrice = rs.getInt("Offer_price");
				String itemName = rs.getString("Item_name");
				String offerStatus = rs.getString("Offer_status");
				String offerReason = rs.getString("Offer_reason");
				String itemCategory = rs.getString("Item_category");
				String itemSize = rs.getString("Item_size");
				offers.add(new Offer(offerId, userId, itemId, itemName, itemPrice, offerPrice, offerStatus, offerReason, itemCategory, itemSize));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return offers;
	}
	
	public int getHighestOffer(int itemId) {
		int highestOffer = 0;
		String query = String.format("SELECT `Offer_price` FROM `MsOffer` "
				+ "WHERE `Item_id` = %d "
				+ "ORDER BY `Offer_price` DESC"
				+ "LIMIT 1", itemId);
		ResultSet rs = Database.getInstance().execQuery(query);
		
		try {
			if (rs.next()) {
				highestOffer = rs.getInt("Offer_price");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return highestOffer;
	}
	
	// for user declined offer alert
	public ArrayList<Offer> getDeclinedOffer(int userId) {
		ArrayList<Offer> offers = new ArrayList<>();
		String query = String.format("SELECT * FROM `MsOffer` WHERE `User_id` = %d AND `Offer_status` LIKE 'Declined'", userId);
		ResultSet rs = Database.getInstance().execQuery(query);
		
		try {
			while (rs.next()) {
				int offerId = rs.getInt("Offer_id");
				String offerReason = rs.getString("Offer_reason");
				offers.add(new Offer(offerId, offerReason));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return offers;
	}
	
	public boolean deleteOffer(int offerId) {
			
			String query = "DELETE FROM `MsOffer` WHERE `Offer_id` = ?";
			PreparedStatement ps = Database.getInstance().prepareStatement(query);
			
			try {
				ps.setInt(1, offerId);
				return ps.executeUpdate() == 1;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return false;
		}
	
	public boolean acceptOffer(int offerId) {
		
		String query = "UPDATE `MsOffer`"
				+ "SET `Offer_status` = 'Accepted'"
				+ "WHERE `Offer_id` = ?";
		PreparedStatement ps = Database.getInstance().prepareStatement(query);
		
		try {
			ps.setInt(1, offerId);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean declineOffer(int offerId, String offerReason) {
		
		String query = "UPDATE `MsOffer` "
				+ "SET `Offer_status` = 'Declined', `Offer_reason` = ? "
				+ "WHERE `Offer_id` = ?";
		PreparedStatement ps = Database.getInstance().prepareStatement(query);
		
		try {
			ps.setString(1, offerReason);
			ps.setInt(2, offerId);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public int getOfferId() {
		return offerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
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
	public void setItemid(int itemId) {
		this.itemId = itemId;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public String getOfferStatus() {
		return offerStatus;
	}
	public void setOfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
	}
	public String getOfferReason() {
		return offerReason;
	}
	public void setOfferReason(String offerReason) {
		this.offerReason = offerReason;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(int offerPrice) {
		this.offerPrice = offerPrice;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
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
