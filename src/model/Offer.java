package model;

public class Offer {
	private int offerId;
	private int userId;
	private int itemid;
	private int sellerId;
	private String offerStatus;
	private String offerReason = "";
	
	public Offer() {}
	
	
	
	public Offer(int offerId, int userId, int itemid, int sellerId, String offerStatus, String offerReason) {
		this.offerId = offerId;
		this.userId = userId;
		this.itemid = itemid;
		this.sellerId = sellerId;
		this.offerStatus = offerStatus;
		this.offerReason = offerReason;
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
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
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
