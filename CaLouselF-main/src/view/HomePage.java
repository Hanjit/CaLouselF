package view;

import java.sql.ResultSet;
import java.util.ArrayList;

import connection.Database;
import controller.ItemController;
import controller.TransactionController;
import controller.WishlistController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class HomePage extends Application{
	
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
	
	Button purchaseBtn, offerBtn, wishlistBtn, historyBtn, cancelBtn;
	
	private void resetButtonVisibility() {
		historyBtn.setVisible(true);
		purchaseBtn.setVisible(false);
		offerBtn.setVisible(false);
		wishlistBtn.setVisible(false);
		cancelBtn.setVisible(false);
	}
	
	private void initialize() {
		bp = new BorderPane();
		sc = new Scene(bp);
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
	}
	
	private void layouting() {
		resetButtonVisibility();
		hb.getChildren().addAll(historyBtn, purchaseBtn, offerBtn, wishlistBtn, cancelBtn);
		
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
	
	String tempId;
	
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
			resetButtonVisibility();
			// Create new transaction
//			TransactionController.createTransaction(user.getUserId, tempId);
			fillTable();
			tempId = null;
		});
		
		cancelBtn.setOnMouseClicked(e -> {
			resetButtonVisibility();
			tempId = null;
		});
		
		wishlistBtn.setOnMouseClicked(e -> {
			resetButtonVisibility();
			// Insert wishlist
//			WishlistController.createWishlist(user.getUserId, tempId);
			fillTable();
			tempId = null;
		});
		
		offerBtn.setOnMouseClicked(e -> {
			resetButtonVisibility();
			// pindah ke page offer
			tempId = null;
		});
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initialize();
		layouting();
		fillTable();
		addEvent();
		
		primaryStage.setScene(sc);
		primaryStage.show();
	}

}
