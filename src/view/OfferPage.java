package view;

import controller.ItemController;
import controller.OfferController;
import controller.TransactionController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Item;

public class OfferPage {
	private Item item;;
	
	Scene sc;
	BorderPane bp;
	GridPane gpForm;
	HBox hbButtons;
	
	Label priceLabel;
	TextField priceField;
	
	Button offerButton, backButton;
	Alert alert;
	
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
		
		alert = new Alert(AlertType.NONE, "", ButtonType.OK);
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
	
	private boolean makeOffer() {
		String input = priceField.getText().toString().trim();
		int offerPrice;
		if (input.isEmpty()) {
		    offerPrice = 0;
		} else {
		    offerPrice = Integer.parseInt(input);
		}
		
		return OfferController.getInstance().createOffer(Main.getUser().getUserId(), item.getItemId(), 
				item.getSellerId(), offerPrice, OfferController.getInstance().getHighestOffer(item.getItemId()));
	}
	
	private void setAction() {
		offerButton.setOnMouseClicked(e -> {
			if (makeOffer()) {
				alert.setContentText("Offer Submitted!");
				alert.showAndWait();
				HomePage homePage = new HomePage();
				Scene homeScene = homePage.getScene();
				Main.switchScene(homeScene);	
			}
		});
		
		backButton.setOnMouseClicked(e -> {
			HomePage homePage = new HomePage();
			Scene homeScene = homePage.getScene();
			Main.switchScene(homeScene);
		});
	}
	
	public OfferPage(int itemId, int sellerId) {
		item = ItemController.getInstance().getItemById(itemId);
		System.out.println(item.getSellerId());
		initialize();
		layouting();
		setAction();
	}
	
	public Scene getScene() {
		return sc;
	}
}
