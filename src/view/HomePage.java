package view;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;

import connection.Database;
import controller.ItemController;
import controller.TransactionController;
import controller.WishlistController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Item;

public class HomePage {
	
	Scene sc;
	BorderPane bp;
	GridPane gp;
	HBox hb;
	
	TableView<Item> tvItems;
	
	TableColumn<Item, String> nameColumn;
	TableColumn<Item, String> sizeColumn;
	TableColumn<Item, String> priceColumn;
	TableColumn<Item, String> categoryColumn;
	
	ArrayList<Item> items;
	
	Label itemName, itemSize, itemPrice, itemCategory;
	
	Button purchaseBtn, offerBtn, wishlistBtn, historyBtn, cancelBtn, viewWishlistBtn;
	
	Alert alert;
	
	private void resetButtonVisibility() {
		historyBtn.setVisible(true);
		viewWishlistBtn.setVisible(true);
		purchaseBtn.setVisible(false);
		offerBtn.setVisible(false);
		wishlistBtn.setVisible(false);
		cancelBtn.setVisible(false);
	}
	
	private void initialize() {
		bp = new BorderPane();
		sc = new Scene(bp, 600, 500);
		gp = new GridPane();
		hb = new HBox();
		
		items = new ArrayList<>();
		
		tvItems = new TableView<>();
		nameColumn = new TableColumn<>("name");
		sizeColumn = new TableColumn<>("size");
		priceColumn = new TableColumn<>("price");
		categoryColumn = new TableColumn<>("category");
		
		purchaseBtn = new Button("Purchase");
		offerBtn = new Button("Make Offer");
		wishlistBtn = new Button("Add to Wishlist");
		historyBtn = new Button("Purchase History");
		cancelBtn = new Button("Cancel");
		viewWishlistBtn = new Button("View WIshlist");
		
		alert = new Alert(AlertType.CONFIRMATION);
	}
	
	private void layouting() {
		resetButtonVisibility();
		hb.getChildren().addAll(historyBtn, viewWishlistBtn, purchaseBtn, offerBtn, wishlistBtn, cancelBtn);
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		sizeColumn.setCellValueFactory(new PropertyValueFactory<>("itemSize"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
		categoryColumn.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));
		
		tvItems.getColumns().addAll(nameColumn, sizeColumn, priceColumn, categoryColumn);
		
		bp.setTop(tvItems);
		bp.setCenter(hb);	
	}
	
	private void fillTable() {
		items = ItemController.getInstance().getItems();
		tvItems.getItems().clear();
		tvItems.getItems().addAll(items);
	}
	
	int tempId;
	
	private void addEvent() {
		tvItems.setOnMouseClicked(e -> {
			TableSelectionModel<Item> modelSelection = tvItems.getSelectionModel();
			modelSelection.setSelectionMode(SelectionMode.SINGLE);
			Item item = modelSelection.getSelectedItem();
			tempId = item.getItemId();
			purchaseBtn.setVisible(true);
			offerBtn.setVisible(true);
			wishlistBtn.setVisible(true);
			cancelBtn.setVisible(true);
			historyBtn.setVisible(false);
		});
		
		purchaseBtn.setOnMouseClicked(e -> {
			alert.setContentText("Confirm Purchase?");
			Optional<ButtonType> result = alert.showAndWait();
			
			if (result.isPresent() && result.get() == ButtonType.OK) {
				resetButtonVisibility();
				TransactionController.getInstance().createTransaction(Main.getUser().getUserId(), tempId);
				WishlistController.getInstance().deleteAllWishlist(tempId);
				fillTable();
				tempId = -1;				
			} else {
				return;
			}
		});
		
		viewWishlistBtn.setOnMouseClicked(e -> {
			WishlistPage wishlistPage = new WishlistPage();
			Scene wishlistScene = wishlistPage.getScene();
			Main.switchScene(wishlistScene);
		});
		
		cancelBtn.setOnMouseClicked(e -> {
			resetButtonVisibility();
			tempId = -1;
		});
		
		wishlistBtn.setOnMouseClicked(e -> {
			resetButtonVisibility();
			// Insert wishlist
			WishlistController.getInstance().addWishlist(tempId, Main.getUser().getUserId());
			fillTable();
			tempId = -1;
		});
		
		offerBtn.setOnMouseClicked(e -> {
			resetButtonVisibility();
			OfferPage offerPage = new OfferPage(tempId, Main.getUser().getUserId());
			Scene offerScene = offerPage.getScene();
			Main.switchScene(offerScene);
			tempId = -1;
		});
		
		historyBtn.setOnMouseClicked(e -> {
			resetButtonVisibility();			
			TransactionPage transactionPage = new TransactionPage();
			Scene transactionScene = transactionPage.getScene();
			Main.switchScene(transactionScene);
		});
	}
	
	public HomePage() {
		initialize();
		layouting();
		fillTable();
		addEvent();
	}
	
	public Scene getScene() {
		return sc;
	}
}
