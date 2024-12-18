package view.seller;

import controller.ItemController;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import model.User;
import view.Main;

public class UploadItemPage {
	Scene sc;
	BorderPane bp;
	GridPane gpForm;
	HBox hbButtons;
	
	Label titleLabel, nameLabel, categoryLabel, sizeLabel, priceLabel;
	TextField nameField, categoryField, sizeField, priceField;
	
	Button submitBtn, backButton;
	
	Alert alert;
	User user;
	
	// Initializes UI components and sets up their basic properties
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
		
		titleLabel = new Label("Upload a New Item");
		titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24)); 
		titleLabel.setTextAlignment(TextAlignment.CENTER); 
		
		nameLabel = new Label("Item Name: ");
		categoryLabel = new Label("Item Category: ");
		sizeLabel = new Label("Item Size: ");
		priceLabel = new Label("Item Price: ");
		
		nameField = new TextField(); 
		categoryField = new TextField(); 
		sizeField = new TextField(); 
		priceField = new TextField(); 
		nameField.setPrefWidth(200);
		categoryField.setPrefWidth(200);
		sizeField.setPrefWidth(200);
		priceField.setPrefWidth(200);
		
		submitBtn = new Button("Submit");
		backButton = new Button("Back");
		submitBtn.setPrefWidth(100);
		backButton.setPrefWidth(100);
		
		alert = new Alert(AlertType.NONE, "", ButtonType.OK);
		user = Main.getUser();
	}
	
	// Configures the layout and positions the UI components in the scene
	private void layouting() {
		gpForm.add(nameLabel, 0, 0);
		gpForm.add(categoryLabel, 0, 1);
		gpForm.add(sizeLabel, 0, 2);
		gpForm.add(priceLabel, 0, 3);
		
		gpForm.add(nameField, 1, 0);
		gpForm.add(categoryField, 1, 1);
		gpForm.add(sizeField, 1, 2);
		gpForm.add(priceField, 1, 3);
		
		hbButtons.getChildren().add(submitBtn);
		hbButtons.getChildren().add(backButton);
		gpForm.add(hbButtons, 1, 4);
		
		bp.setTop(titleLabel);
		bp.setCenter(gpForm);
		BorderPane.setAlignment(titleLabel, Pos.CENTER);
		BorderPane.setMargin(gpForm, new Insets(0, 0, 80, 0));
		BorderPane.setMargin(titleLabel, new Insets(80, 0, 0, 0));
	}
	
	// Uploads the item to the database using the ItemController
	private boolean upload() {
		String name = nameField.getText().toString();
		String category = categoryField.getText().toString();
		String size = sizeField.getText().toString();
		String price = priceField.getText().toString();
		
		return ItemController.getInstance().createItem(name, size, price, category, "-", "NULL", user.getUserId());
	}
	
	//	Adds event listeners to the UI components to handle user interactions
	private void addEvent() {
		// Event for clicking the "Submit" button
		submitBtn.setOnMouseClicked(e -> {
			if (upload()) {
				alert.setContentText("Upload Successful!");
				alert.showAndWait();
				SellerHome sellerPage = new SellerHome();
				Scene sellerScene = sellerPage.getScene();
				Main.switchScene(sellerScene);
			} 
		});
		
		// Event for clicking the "Back" button
		backButton.setOnMouseClicked(e -> {
			SellerHome sellerPage = new SellerHome();
			Scene sellerScene = sellerPage.getScene();
			Main.switchScene(sellerScene);
		});
	}
	
	// Constructor for Seller Upload Item Page (call all the functions)
	public UploadItemPage() {
		initialize();
		layouting();
		addEvent();
	}
	
	//	Returns the scene for this UploadItemPage
	public Scene getScene() {
		return sc;
	}	
}
