package view;

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
	
	private void fillTable() {
		int userId = Main.getUser().getUserId();
		transactions = TransactionController.getInstance().getTransaction(userId);

		tvTransactions.getItems().clear();
		tvTransactions.getItems().addAll(transactions);
	}
	
	private void setAction() {
		backButton.setOnMouseClicked(e -> {
			HomePage homePage = new HomePage();
			Scene homeScene = homePage.getScene();
			Main.switchScene(homeScene);
		});
	}
	
	public TransactionPage() {
		initialize();
		layouting();
		fillTable();
		setAction();
	}
	
	public Scene getScene() {
		return sc;
	}
}
