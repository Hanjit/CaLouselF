package view.admin;

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

public class AdminHome {

	Scene sc;
	BorderPane bp;
	GridPane gp;
	HBox hb;
	
	TableView<Item> tvRequestItems;
	
	TableColumn<Item, String> nameColumn;
	TableColumn<Item, String> categoryColumn;
	TableColumn<Item, String> sizeColumn;
	TableColumn<Item, String> priceColumn;
	
	ArrayList<Item> items;
	
	Button acceptBtn, declineBtn;
	
	// Resets the visibility of buttons to their default state
	private void resetButtonVisibility() {
		acceptBtn.setVisible(false);
		declineBtn.setVisible(false);
	}
	
	// Initializes UI components and sets up their basic properties
	private void initialize() {
		bp = new BorderPane();
		sc = new Scene(bp, 800, 600);
		gp = new GridPane();
		hb = new HBox(10);
		
		items = new ArrayList<>();
		tvRequestItems = new TableView<>();
		
		nameColumn = new TableColumn<>("Name");
		categoryColumn = new TableColumn<>("Category");
		sizeColumn = new TableColumn<>("Size");
		priceColumn = new TableColumn<>("Price");
		
		acceptBtn = new Button("Accept");
		declineBtn = new Button("Decline");
		acceptBtn.setMinWidth(100);
	    declineBtn.setMinWidth(100);
	}
	
	// Configures the layout and positions the UI components in the scene
	private void layouting() {
		resetButtonVisibility();
		
		hb.getChildren().addAll(acceptBtn, declineBtn);
		hb.setAlignment(Pos.CENTER);
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		categoryColumn.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));
		sizeColumn.setCellValueFactory(new PropertyValueFactory<>("itemSize"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
		
		tvRequestItems.getColumns().addAll(nameColumn, categoryColumn, sizeColumn, priceColumn);
		tvRequestItems.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
	    GridPane.setHgrow(tvRequestItems, Priority.ALWAYS); 
	    GridPane.setVgrow(tvRequestItems, Priority.ALWAYS);
		
		gp.add(tvRequestItems, 0, 0);
	    gp.setAlignment(Pos.CENTER); 
		
		bp.setCenter(gp);
		bp.setBottom(hb);
		BorderPane.setMargin(hb, new Insets(10)); 
	    bp.setPadding(new Insets(20));
	}
	
	// Fills the TableView with data fetched from the ItemController
	private void fillTable() {
		items = ItemController.getInstance().getRequestedItem();
		tvRequestItems.getItems().clear();
		tvRequestItems.getItems().addAll(items);
	}
	
	// Temporary variable to store the selected item's ID
	int tempId; 
	
	//	Adds event listeners to the UI components to handle user interactions
	private void addEvent() {
		// Event for selecting an item in the TableView
		tvRequestItems.setOnMouseClicked(e -> {
			TableSelectionModel<Item> modelSelection = tvRequestItems.getSelectionModel();
			modelSelection.setSelectionMode(SelectionMode.SINGLE);
			Item item = modelSelection.getSelectedItem();
			tempId = item.getItemId();
			acceptBtn.setVisible(true);
			declineBtn.setVisible(true);
		});
		
		// Event for clicking the "Accept" button
		acceptBtn.setOnMouseClicked(e -> {
			ItemController.getInstance().approveItem(tempId);
			fillTable();
			tempId = -1;
		});
		
		// Event for clicking the "Decline" button
		declineBtn.setOnMouseClicked(e -> {
			ItemController.getInstance().declineItem(tempId);
			fillTable();
			tempId = -1;
		});
	}
	
	// Constructor for Admin Home Page (call all the functions)
	public AdminHome() {
		initialize();
		layouting();
		fillTable();
		addEvent();
	}
	
	//	Returns the scene for this AdminHome
	public Scene getScene() {
		return sc;
	}
}
