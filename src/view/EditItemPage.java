package view;

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
import model.Item;

public class EditItemPage {
	Scene sc;
	BorderPane bp;
	GridPane gpForm;
	HBox hbButtons;
	
	Label titleLabel, nameLabel, categoryLabel, sizeLabel, priceLabel;
	TextField nameField, categoryField, sizeField, priceField;
	
	Button confirmBtn, cancelButton;
	
	Alert alert;
	int itemId;
	Item item;
	
	private void initialize() {		
		item = ItemController.getInstance().getItemById(itemId);
		
		bp = new BorderPane();
		sc = new Scene(bp, 800, 600);
		
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
		
		nameField = new TextField(item.getItemName()); 
		categoryField = new TextField(item.getItemCategory()); 
		sizeField = new TextField(item.getItemSize()); 
		priceField = new TextField(item.getItemPrice()); 
		nameField.setPrefWidth(200);
		categoryField.setPrefWidth(200);
		sizeField.setPrefWidth(200);
		priceField.setPrefWidth(200);
		
		confirmBtn = new Button("Confirm");
		cancelButton = new Button("Back");
		confirmBtn.setPrefWidth(100);
		cancelButton.setPrefWidth(100);
		
		alert = new Alert(AlertType.NONE, "", ButtonType.OK);
		
	}
	
	private void layouting() {
		gpForm.add(nameLabel, 0, 0);
		gpForm.add(categoryLabel, 0, 1);
		gpForm.add(sizeLabel, 0, 2);
		gpForm.add(priceLabel, 0, 3);
		
		gpForm.add(nameField, 1, 0);
		gpForm.add(categoryField, 1, 1);
		gpForm.add(sizeField, 1, 2);
		gpForm.add(priceField, 1, 3);
		
		hbButtons.getChildren().add(confirmBtn);
		hbButtons.getChildren().add(cancelButton);
		gpForm.add(hbButtons, 1, 4);
		
		bp.setTop(titleLabel);
		bp.setCenter(gpForm);
		BorderPane.setAlignment(titleLabel, Pos.CENTER);
		BorderPane.setMargin(gpForm, new Insets(0, 0, 80, 0));
		BorderPane.setMargin(titleLabel, new Insets(80, 0, 0, 0));
	}
	
	private boolean edit() {
		String name = nameField.getText().toString();
		String category = categoryField.getText().toString();
		String size = sizeField.getText().toString();
		String price = priceField.getText().toString();
		
		return ItemController.getInstance().updateItem(itemId, name, size, price, category);
	}
	
	private void addEvent() {
		confirmBtn.setOnMouseClicked(e -> {
			if (edit()) {
				alert.setContentText("Edit Successful!");
				alert.showAndWait();
				SellerHome sellerPage = new SellerHome();
				Scene sellerScene = sellerPage.getScene();
				Main.switchScene(sellerScene);
			}
		});
		
		cancelButton.setOnMouseClicked(e -> {
			SellerHome sellerPage = new SellerHome();
			Scene sellerScene = sellerPage.getScene();
			Main.switchScene(sellerScene);
		});
	}
	
	public EditItemPage(int itemId) {
		this.itemId = itemId;
		initialize();
		layouting();
		addEvent();
	}
	
	public Scene getScene() {
		return sc;
	}
}
