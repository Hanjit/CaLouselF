package view.buyer;

import java.util.ArrayList;

import controller.TransactionController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import model.Item;
import model.Transaction;
import view.Main;

public class TransactionPage {

	Scene sc;
	BorderPane bp;
	TableView tvTransactions;
	
	ArrayList<Transaction> transactions;

	TableColumn<Transaction, Integer> transactionIdClmn;
	TableColumn<Item, String> itemNameClmn;
	TableColumn<Item, Integer> itemCategoryClmn;
	TableColumn<Item, String> itemSizeClmn;
	TableColumn<Item, String> itemPriceClmn;
	
	GridPane gpForm;
	HBox hbButtons;
		
	Button backButton;
	
	// Initializes UI components and sets up their basic properties
	private void initialize() {
		transactions = new ArrayList<>();
		
		bp = new BorderPane();
		sc = new Scene(bp, 800, 600);
		
		tvTransactions = new TableView<>();
		transactionIdClmn = new TableColumn<>("Transaction ID");
		itemNameClmn = new TableColumn<>("Item Name");
		itemCategoryClmn = new TableColumn<>("Item Category");
		itemSizeClmn = new TableColumn<>("Item Size");
		itemPriceClmn = new TableColumn<>("Item Price");
		
		gpForm = new GridPane();
		hbButtons = new HBox(10);
		
		backButton = new Button("Back");
		backButton.setPrefWidth(100);
	}
	
	// Configures the layout and positions the UI components in the scene
	private void layouting() {
		hbButtons.getChildren().add(backButton);
		hbButtons.setPadding(new Insets(10));
		
		transactionIdClmn.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
		itemNameClmn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		itemCategoryClmn.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));
		itemSizeClmn.setCellValueFactory(new PropertyValueFactory<>("itemSize"));
		itemPriceClmn.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
		
		tvTransactions.getColumns().addAll(transactionIdClmn, itemNameClmn, itemCategoryClmn, itemSizeClmn, itemPriceClmn);
		tvTransactions.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
		GridPane.setHgrow(tvTransactions, Priority.ALWAYS); 
	    GridPane.setVgrow(tvTransactions, Priority.ALWAYS);
		
	    gpForm.setAlignment(Pos.CENTER);
	    gpForm.add(tvTransactions, 0, 0);
	    
		bp.setCenter(gpForm);
		bp.setBottom(hbButtons);
		bp.setPadding(new Insets(20));
	}
	
	// Fills the TableView with data fetched from the TransactionController
	private void fillTable() {
		int userId = Main.getUser().getUserId();
		transactions = TransactionController.getInstance().getTransaction(userId);

		tvTransactions.getItems().clear();
		tvTransactions.getItems().addAll(transactions);
	}
	
	//	Adds event listeners to the UI components to handle user interactions
	private void setAction() {
		// Event for clicking the "Back" button
		backButton.setOnMouseClicked(e -> {
			HomePage homePage = new HomePage();
			Scene homeScene = homePage.getScene();
			Main.switchScene(homeScene);
		});
	}
	
	// Constructor for Buyer History Transaction Page (call all the functions)
	public TransactionPage() {
		initialize();
		layouting();
		fillTable();
		setAction();
	}
	
	//	Returns the scene for this TransactionPage
	public Scene getScene() {
		return sc;
	}
}
