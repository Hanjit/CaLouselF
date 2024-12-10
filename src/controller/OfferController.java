package controller;

import java.util.ArrayList;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import model.Offer;

public class OfferController {
	
	private static OfferController oc = null;
	private Offer offerModel;
	private Alert alert = new Alert(AlertType.NONE);
	
	public OfferController() {
		offerModel = new Offer();
	}
	
	private void errorAlert(String message) {
		alert.setAlertType(AlertType.ERROR);
		alert.setContentText(message);
		alert.show();
	}
	
	public static OfferController getInstance() {
		if (oc == null) {
			oc = new OfferController();
		}
		
		return oc;
	}
	
	public boolean createOffer(int userId, int itemId, int sellerId, int offerPrice, int highestOffer) {
		
		if (offerPrice == 0 ) {
			errorAlert("Offer cannot be empty or zero!");
			return false;
		} else if(offerPrice < highestOffer) {
			errorAlert(String.format("Offer must be higher than %d", highestOffer));
			return false;
		}
		
		return offerModel.createOffer(userId, itemId, sellerId, offerPrice);
	}
	
	public ArrayList<Offer> getOffer(int sellerId) {
		
		return offerModel.getOffer(sellerId);
	}
	
	public int getHighestOffer(int itemId) {
		
		return offerModel.getHighestOffer(itemId);
	}
	
	public ArrayList<Offer> getDeclinedOffer(int userId) {
		
		return offerModel.getDeclinedOffer(userId);
	}
	
	public boolean deleteOffer(int offerId) {
		
		return offerModel.deleteOffer(offerId);
	}
	
	public boolean acceptOffer(int offerId) {
		
		return offerModel.acceptOffer(offerId);
	}
	
	public boolean declineOffer(int offerId) {
		
		TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Reason Required");
        dialog.setHeaderText("Enter your reason");
        
        Optional<String> result = dialog.showAndWait();
        
        String offerReason = result.get();
		
		if (offerReason.isEmpty()) {
			errorAlert("Reason cannot be empty");
		}
		
		
		return offerModel.declineOffer(offerId, offerReason);
	}
}
