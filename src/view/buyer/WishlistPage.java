package view.buyer;

import java.util.ArrayList;

import controller.WishlistController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import model.Wishlist;
import view.Main;

public class WishlistPage{

	Scene sc;
	BorderPane bp;
	GridPane gp;
	HBox hb;
	
	TableView<Wishlist> tvWishlist;
	
	TableColumn<Wishlist, String> nameColumn;
	TableColumn<Wishlist, String> sizeColumn;
	TableColumn<Wishlist, String> priceColumn;
	TableColumn<Wishlist, String> categoryColumn;
	
	ArrayList<Wishlist> wishlistItems;
	
	Label itemName, itemSize, itemPrice, itemCategory;
	
	Button removeBtn, backBtn;
	
	// Resets the visibility of buttons to their default state
	private void resetButtonVisibility() {
		backBtn.setVisible(true);
		removeBtn.setVisible(false);
	}
	
	// Initializes UI components and sets up their basic properties
	private void initialize() {
		bp = new BorderPane();
		sc = new Scene(bp, 800, 600);
		gp = new GridPane();
		hb = new HBox(10);
		
		wishlistItems = new ArrayList<>();
		
		tvWishlist= new TableView<>();
		nameColumn = new TableColumn<>("name");
		sizeColumn = new TableColumn<>("size");
		priceColumn = new TableColumn<>("price");
		categoryColumn = new TableColumn<>("category");
		
		removeBtn = new Button("Remove");
		backBtn = new Button("Back");
		removeBtn.setPrefWidth(100);
		backBtn.setPrefWidth(100);
	}
	
	// Configures the layout and positions the UI components in the scene
	private void layouting() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("itemSize"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));

        tvWishlist.getColumns().addAll(nameColumn, sizeColumn, priceColumn, categoryColumn);
        tvWishlist.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        GridPane.setHgrow(tvWishlist, Priority.ALWAYS); 
	    GridPane.setVgrow(tvWishlist, Priority.ALWAYS);
        
        hb.getChildren().addAll(removeBtn, backBtn);
        hb.setPadding(new Insets(10));

        bp.setCenter(tvWishlist);
        bp.setBottom(hb);
        bp.setPadding(new Insets(20));
	}
	
	//	Populates the TableView with wishlist items fetched from the controller
	private void view() {
		int userId = Main.getUser().getUserId(); 
        wishlistItems = WishlistController.getInstance().viewWishlist(userId);

        tvWishlist.getItems().clear();
        tvWishlist.getItems().addAll(wishlistItems);
	}
	
	// Deletes the selected wishlist item using the WishlistController.
	private void delete() {
		 Wishlist selectedItem = tvWishlist.getSelectionModel().getSelectedItem();
	        if (selectedItem != null) {
	            WishlistController.getInstance().deleteWishlist(selectedItem.getWishlistId());
	        }
	}
	
	//	Adds event listeners to the UI components to handle user interactions
    private void setAction() {
    	// Event for clicking the "Remove" button
    	removeBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				delete();
			}
		});
    	
    	// Event for clicking the "Back" button
    	backBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				HomePage homePage = new HomePage();
				Scene homeScene = homePage.getScene();
				Main.switchScene(homeScene);
			}
		});
    }
    
    // Constructor for Buyer Wishlist Page (call all the functions)
    public WishlistPage() {
    	initialize();
		layouting();
		view();
		setAction();
    }
	
    //	Returns the scene for this WishlistPage
	public Scene getScene() {
		return sc;
	}
}
