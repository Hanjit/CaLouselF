package view;

import controller.OfferController;
import controller.TransactionController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Item;

public class OfferPage {
	private int itemId, sellerId;
	
	Scene sc;
	BorderPane bp;
	GridPane gpForm;
	HBox hbButtons;
	
	Label priceLabel;
	TextField priceField;
	
	Button offerButton, backButton;
	
	private void initialize() {
		bp = new BorderPane();
		sc = new Scene(bp, 600, 400);
		
		gpForm = new GridPane();
		gpForm.setHgap(10);
	    gpForm.setVgap(10);
	    gpForm.setPadding(new Insets(20));
	    gpForm.setAlignment(Pos.CENTER);
		
		hbButtons = new HBox(10);
		hbButtons.setAlignment(Pos.CENTER);
		
		priceLabel = new Label("Offer Price: ");
		
		priceField = new TextField();
		priceField.setPrefWidth(200);
		
		offerButton = new Button("Make Offer");
		backButton = new Button("Back");
		offerButton.setPrefWidth(100);
		backButton.setPrefWidth(100);
	}
	
	private void layouting() {
		gpForm.add(priceLabel, 0, 0);
		
		gpForm.add(priceField, 1, 0);
		
		hbButtons.getChildren().add(offerButton);
		hbButtons.getChildren().add(backButton);
		gpForm.add(hbButtons, 1, 1);
		
		bp.setCenter(gpForm);
		BorderPane.setMargin(gpForm, new Insets(100, 0, 100, 0));
	}
	
//	private boolean makeOffer() {
//		String offerPrice = priceField.getText().toString();
//		
////		return OfferController.getInstance().createOffer(userId, itemId, sellerId, offerPrice, highestOffer);
//	}
	
//	private void setAction() {
//		Item item = getItemById(tempId);
//		
//		offerButton.setOnMouseClicked(e -> {
//			if (condition) {
//				
//			}
//		});
//	}
	
	public OfferPage(int itemId, int sellerId) {
		this.itemId = itemId;
		this.sellerId = sellerId;
		
		initialize();
		layouting();
//		setAction();
	}
	
	public Scene getScene() {
		return sc;
	}
}
