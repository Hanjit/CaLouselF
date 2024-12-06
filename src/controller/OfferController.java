package controller;

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
	
}
