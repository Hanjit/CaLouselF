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
	private String itemName;
	private String offerStatus;
	private String offerReason;
	private int offerPrice;
	
	public Offer() {}
	
	public Offer(int offerId, int userId, int itemId, String itemName, int itemPrice, int offerPrice, String offerStatus, String offerReason) {
		super();
		this.offerId = offerId;
		this.userId = userId;
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.offerPrice = offerPrice;
		this.offerStatus = offerStatus;
		this.offerReason = "";
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
	
	public ArrayList<Offer> getOffer(int sellerId) {
		ArrayList<Offer> offers = new ArrayList<>();
		String query = String.format("SELECT `Offer_id`, `User_id`, `Item_id`, `Item_price`, `Offer_price`, `Offer_status`, `Offer_reason`"
				+ "FROM `MsOffer` mo"
				+ "JOIN `MsItem` ON mo.Item_id = mi.Item_id"
				+ "WHERE mo.Seller_id = %d"
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
				offers.add(new Offer(offerId, userId, itemId, itemName, itemPrice, offerPrice, offerStatus, offerReason));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return offers;
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
		
		String query = "UPDATE `MsOffer`"
				+ "SET `Offer_status` = 'Declined', `Offer_reason` = '?'"
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
	
	
}
