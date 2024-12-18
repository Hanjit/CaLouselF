package view.seller;

import java.util.ArrayList;

import controller.ItemController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.layout.Priority;
import model.Item;
import view.Main;

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
	
	// Resets the visibility of buttons to their default state
	private void resetButtonVisibility() {
		myItemsBtn.setVisible(true);
		uploadItemBtn.setVisible(true);
		offerBtn.setVisible(true);
		backBtn.setVisible(false);
		editItemBtn.setVisible(false);
		deleteItemBtn.setVisible(false);
	}
	
	// Initializes UI components and sets up their basic properties
	private void initialize() {
		bp = new BorderPane();
		sc = new Scene(bp, 800, 600);
		gp = new GridPane();
		hb = new HBox(10);
		
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
		myItemsBtn.setMinWidth(100);
	    uploadItemBtn.setMinWidth(100);
	    offerBtn.setMinWidth(100);
	    backBtn.setMinWidth(100);
	    editItemBtn.setMinWidth(100);
	    deleteItemBtn.setMinWidth(100);
		
		onMyItems = false;
	}
	
	// Configures the layout and positions the UI components in the scene
	private void layouting() {	
		resetButtonVisibility();
		
		hb.getChildren().addAll(myItemsBtn, uploadItemBtn, offerBtn, editItemBtn, deleteItemBtn, backBtn);; 
	    hb.setPadding(new Insets(10));
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		categoryColumn.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));
		sizeColumn.setCellValueFactory(new PropertyValueFactory<>("itemSize"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("itemStatus"));
		reasonColumn.setCellValueFactory(new PropertyValueFactory<>("itemReason"));
		
		tvSellerItems.getColumns().addAll(nameColumn, categoryColumn, sizeColumn, priceColumn, statusColumn, reasonColumn);
		tvSellerItems.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
	    GridPane.setHgrow(tvSellerItems, Priority.ALWAYS); 
	    GridPane.setVgrow(tvSellerItems, Priority.ALWAYS);
		
	    gp.setAlignment(Pos.CENTER);
	    gp.add(tvSellerItems, 0, 0);
	    
		bp.setCenter(gp);
		bp.setBottom(hb);
		bp.setPadding(new Insets(20));
	}
	
	// Fills the TableView with items belonging to the current seller.
	private void fillTableSeller(int sellerId) {
		items = ItemController.getInstance().getAllItems(sellerId);
		tvSellerItems.getItems().clear();
		tvSellerItems.getItems().addAll(items);
	}
	
	// Fills the TableView with all items.
	private void fillTable() {
		items = ItemController.getInstance().getItems();
		tvSellerItems.getItems().clear();
		tvSellerItems.getItems().addAll(items);
	}
	
	// Temporary variable to store the selected item's ID
	int tempId;
	
	//	Adds event listeners to the UI components to handle user interactions
	private void addEvent() {
		// Event for selecting an items owned by the seller in the TableView
		myItemsBtn.setOnMouseClicked(e -> {
			fillTableSeller(Main.getUser().getUserId());
			onMyItems = true;
			myItemsBtn.setVisible(false);
			uploadItemBtn.setVisible(false);
			offerBtn.setVisible(false);
			backBtn.setVisible(true);
		});
		
		// Event for handle table row selection
		tvSellerItems.setOnMouseClicked(v -> {
			TableSelectionModel<Item> modelSelection = tvSellerItems.getSelectionModel();
			modelSelection.setSelectionMode(SelectionMode.SINGLE);
			Item item = modelSelection.getSelectedItem();
			tempId = item.getItemId();
			
			if (onMyItems) {
				editItemBtn.setVisible(true);
				deleteItemBtn.setVisible(true);				
			}
			if (item.getItemStatus().equals("Declined") || item.getItemStatus().equals("Waiting")) {
				deleteItemBtn.setVisible(false);
				editItemBtn.setVisible(false);
			}
		});
		
		// Event for clicking the "Back" button
		backBtn.setOnMouseClicked(e -> {
			fillTable();
			resetButtonVisibility();
			onMyItems = false;
		});
		
		// Event for clicking the "Delete Item" button
		deleteItemBtn.setOnMouseClicked(e -> {
			ItemController.getInstance().deleteItem(tempId);
			fillTableSeller(Main.getUser().getUserId());
			tempId = -1;
		});
		
		// Event for clicking the "Edit Item" button
		editItemBtn.setOnMouseClicked(e -> {
			EditItemPage editPage = new EditItemPage(tempId);
			Scene editScene = editPage.getScene();
			Main.switchScene(editScene);
		});
		
		// Event for clicking the "Upload Item" button
		uploadItemBtn.setOnMouseClicked(e -> {
			UploadItemPage uploadPage = new UploadItemPage();
			Scene uploadScene = uploadPage.getScene();
			Main.switchScene(uploadScene);
		});
		
		// Event for clicking the "View Offers" button
		offerBtn.setOnMouseClicked(e -> {
			SellerOffer sellerOffer = new SellerOffer();
			Scene offerScene = sellerOffer.getScene();
			Main.switchScene(offerScene);
		});
	}
	
	// Constructor for Seller Home Page (call all the functions)
	public SellerHome() {
		initialize();
		layouting();
		fillTable();
		addEvent();
	}
	
	//	Returns the scene for this SellerHome
	public Scene getScene() {
		return sc;
	}
}
