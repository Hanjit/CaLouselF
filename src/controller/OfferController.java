package controller;

import java.util.ArrayList;

import model.Offer;

public class OfferController {
	
	private static OfferController oc = null;
	private Offer offerModel;
	
	public OfferController() {
		offerModel = new Offer();
	}
	
	public static OfferController getInstance() {
		if (oc == null) {
			oc = new OfferController();
		}
		
		return oc;
	}
	
	public boolean createOffer(int userId, int itemId, int sellerId, int offerPrice) {
		
		return offerModel.createOffer(userId, itemId, sellerId, offerPrice);
	}
	
	public ArrayList<Offer> getOffer(int sellerId) {
		
		return offerModel.getOffer(sellerId);
	}
	
	public boolean acceptOffer(int offerId) {
		
		return offerModel.acceptOffer(offerId);
	}
	
	public boolean declineOffer(int offerId, String offerReason) {
		
		return offerModel.declineOffer(offerId, offerReason);
	}
}
