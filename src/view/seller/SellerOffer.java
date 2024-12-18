package view.seller;

import java.util.ArrayList;

import controller.ItemController;
import controller.OfferController;
import controller.TransactionController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import model.Item;
import model.Offer;
import view.Main;

public class SellerOffer {
	private Item item;
	private Offer offer;
	
	Scene sc;
	BorderPane bp;
	HBox hb;
	GridPane gp;

	ArrayList<Offer> offers;
	TableView<Offer> tvRequests;
	
	TableColumn<Offer, String> nameColumn;
	TableColumn<Offer, String> categoryColumn;
	TableColumn<Offer, String> sizeColumn;
	TableColumn<Offer, String> priceColumn;
	TableColumn<Offer, String> offerPriceColumn;
	TableColumn<Offer, String> offerStatus;
	
	Button acceptBtn, declineBtn, backBtn;
	Alert alert;
	
	// Initializes UI components and sets up their basic properties
	private void initialize() {
		bp = new BorderPane();
		sc = new Scene(bp, 800, 600);
		gp = new GridPane();
		hb = new HBox(10);
		
		item = new Item();
		offer = new Offer();
		
		offers = new ArrayList<>();
		tvRequests = new TableView<>();
		
		nameColumn = new TableColumn<>("Name");
		categoryColumn = new TableColumn<>("Category");
		sizeColumn = new TableColumn<>("Size");
		priceColumn = new TableColumn<>("Price");
		offerPriceColumn = new TableColumn<>("Offer");
		offerStatus = new TableColumn<>("Status");
		
		acceptBtn = new Button("Accept Offer");
		declineBtn = new Button("Decline Offer");
		backBtn = new Button("Back");
		acceptBtn.setPrefWidth(100);
		declineBtn.setPrefWidth(100);
		backBtn.setPrefWidth(100);
		
		alert = new Alert(AlertType.NONE, "", ButtonType.OK);
	}
	
	// Configures the layout and positions the UI components in the scene
	private void layouting() {
		hb.getChildren().addAll(acceptBtn, declineBtn, backBtn);
		hb.setPadding(new Insets(10));
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		categoryColumn.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));
		sizeColumn.setCellValueFactory(new PropertyValueFactory<>("itemSize"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
		offerPriceColumn.setCellValueFactory(new PropertyValueFactory<>("offerPrice"));
		offerStatus.setCellValueFactory(new PropertyValueFactory<>("offerStatus"));
		
		tvRequests.getColumns().addAll(nameColumn, categoryColumn, sizeColumn, priceColumn, offerPriceColumn, offerStatus);
		tvRequests.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		GridPane.setHgrow(tvRequests, Priority.ALWAYS);
	    GridPane.setVgrow(tvRequests, Priority.ALWAYS);
	    
	    gp.add(tvRequests, 0, 0);
	    gp.setAlignment(Pos.CENTER);
		
		bp.setCenter(gp);
		bp.setBottom(hb);
		bp.setPadding(new Insets(20));
	}
	
	// Fills the TableView with data fetched from the OfferController
	private void fillTable() {
		offers = OfferController.getInstance().getOffer(Main.getUser().getUserId());
		tvRequests.getItems().clear();
		tvRequests.getItems().addAll(offers);
	}
	
	// Temporary variable to store the selected item's ID
	int tempId;
	
	//	Adds event listeners to the UI components to handle user interactions
	private void setAction() {
		// Event for selecting items offer request in the TableView
		tvRequests.setOnMouseClicked(v -> {
			TableSelectionModel<Offer> modelSelection = tvRequests.getSelectionModel();
			modelSelection.setSelectionMode(SelectionMode.SINGLE);
			offer = modelSelection.getSelectedItem();
			tempId = offer.getItemId();
			item = ItemController.getInstance().getItemById(tempId);
		});
		
		// Event for clicking the "Accept" button
		acceptBtn.setOnMouseClicked(e -> {
			OfferController.getInstance().acceptOffer(tempId);
			TransactionController.getInstance().createTransaction(offer.getUserId(), tempId);
			fillTable();
			tempId = -1;
		});
		
		// Event for clicking the "Decline" button
		declineBtn.setOnMouseClicked(e -> {
			System.out.println(tempId);
			OfferController.getInstance().declineOffer(tempId);
			fillTable();
			tempId = -1;
		});
		
		// Event for clicking the "Back" button
		backBtn.setOnMouseClicked(e -> {
			fillTable();
			SellerHome sellerPage = new SellerHome();
			Scene sellerScene = sellerPage.getScene();
			Main.switchScene(sellerScene);
		});
	}
	
	// Constructor for Seller Offer Page (call all the functions)
	public SellerOffer() {
		initialize();
		layouting();
		setAction();
		fillTable();
	}
	
	//	Returns the scene for this SellerOffer
	public Scene getScene() {
		return sc;
	}
}
