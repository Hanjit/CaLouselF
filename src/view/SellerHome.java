package view;

import java.util.ArrayList;

import controller.ItemController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Item;

public class SellerHome {
	Scene sc;
	BorderPane bp;
	GridPane gp;
	HBox hb;
	
	TableView<Item> tvSellerItems;
	
	TableColumn<Item, String> nameColumn;
	TableColumn<Item, String> categoryColumn;
	TableColumn<Item, String> sizeColumn;
	TableColumn<Item, String> priceColumn;
	TableColumn<Item, String> statusColumn;
	TableColumn<Item, String> reasonColumn;
	
	ArrayList<Item> items;
	
	boolean onMyItems;
	
	Button myItemsBtn, uploadItemBtn, backBtn, editItemBtn, deleteItemBtn, offerBtn;
	
	private void resetButtonVisibility() {
		myItemsBtn.setVisible(true);
		uploadItemBtn.setVisible(true);
		offerBtn.setVisible(true);
		backBtn.setVisible(false);
		editItemBtn.setVisible(false);
		deleteItemBtn.setVisible(false);
	}
	
	private void initialize() {
		bp = new BorderPane();
		sc = new Scene(bp);
		gp = new GridPane();
		hb = new HBox();
		
		items = new ArrayList<>();
		tvSellerItems = new TableView<>();
		
		nameColumn = new TableColumn<>("Name");
		categoryColumn = new TableColumn<>("Category");
		sizeColumn = new TableColumn<>("Size");
		priceColumn = new TableColumn<>("Price");
		statusColumn = new TableColumn<>("Status");
		reasonColumn = new TableColumn<>("Reason");
		
		myItemsBtn = new Button("My Items");
		uploadItemBtn = new Button("Upload Item");
		offerBtn = new Button("View Offers");
		backBtn = new Button("Back");
		editItemBtn = new Button("Edit Item");
		deleteItemBtn = new Button("Delete Item");
		
		onMyItems = false;
		
	}
	
	private void layouting() {	
		resetButtonVisibility();
		
		hb.getChildren().addAll(myItemsBtn, uploadItemBtn, offerBtn, editItemBtn, deleteItemBtn, backBtn);;
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		categoryColumn.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));
		sizeColumn.setCellValueFactory(new PropertyValueFactory<>("itemSize"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("itemStatus"));
		reasonColumn.setCellValueFactory(new PropertyValueFactory<>("itemReason"));
		
		tvSellerItems.getColumns().addAll(nameColumn, categoryColumn, sizeColumn, priceColumn, statusColumn, reasonColumn);
		
		bp.setTop(tvSellerItems);
		bp.setBottom(hb);
	}
	
	private void fillTableSeller(int sellerId) {
		items = ItemController.getInstance().getAllItems(sellerId);
		tvSellerItems.getItems().clear();
		tvSellerItems.getItems().addAll(items);
	}
	
	private void fillTable() {
		items = ItemController.getInstance().getItems();
		tvSellerItems.getItems().clear();
		tvSellerItems.getItems().addAll(items);
	}
	
	int tempId;
	
	private void addEvent() {
		myItemsBtn.setOnMouseClicked(e -> {
			fillTableSeller(Main.getUser().getUserId());
			onMyItems = true;
			myItemsBtn.setVisible(false);
			uploadItemBtn.setVisible(false);
			offerBtn.setVisible(false);
			backBtn.setVisible(true);
		});
		
		tvSellerItems.setOnMouseClicked(v -> {
			TableSelectionModel<Item> modelSelection = tvSellerItems.getSelectionModel();
			modelSelection.setSelectionMode(SelectionMode.SINGLE);
			Item item = modelSelection.getSelectedItem();
			tempId = item.getItemId();
			
			if (onMyItems) {
				editItemBtn.setVisible(true);
				deleteItemBtn.setVisible(true);				
			}
		});
		
		backBtn.setOnMouseClicked(e -> {
			fillTable();
			resetButtonVisibility();
			onMyItems = false;
		});
		
		deleteItemBtn.setOnMouseClicked(e -> {
			ItemController.getInstance().deleteItem(tempId);
			fillTableSeller(Main.getUser().getUserId());
			tempId = -1;
		});
		
		editItemBtn.setOnMouseClicked(e -> {
			EditItemPage editPage = new EditItemPage(tempId);
			Scene editScene = editPage.getScene();
			Main.switchScene(editScene);
		});
		
		uploadItemBtn.setOnMouseClicked(e -> {
			UploadItemPage uploadPage = new UploadItemPage();
			Scene uploadScene = uploadPage.getScene();
			Main.switchScene(uploadScene);
		});
		
		offerBtn.setOnMouseClicked(e -> {
			// to view my offer page for seller
		});
	}
	
	public SellerHome() {
		initialize();
		layouting();
		fillTable();
		addEvent();
	}
	
	public Scene getScene() {
		return sc;
	}
	
	
	
	
	
}