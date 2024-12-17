package view;

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
	
	private void resetButtonVisibility() {
		backBtn.setVisible(true);
		removeBtn.setVisible(false);
	}
	
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

	private void view() {
		int userId = Main.getUser().getUserId(); 
        wishlistItems = WishlistController.getInstance().viewWishlist(userId);

        tvWishlist.getItems().clear();
        tvWishlist.getItems().addAll(wishlistItems);
	}
	
	private void delete() {
		 Wishlist selectedItem = tvWishlist.getSelectionModel().getSelectedItem();
	        if (selectedItem != null) {
	            WishlistController.getInstance().deleteWishlist(selectedItem.getWishlistId());
	        }
	}

    private void setAction() {
    	removeBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				delete();
			}
		});
    	
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
    
    public WishlistPage() {
    	initialize();
		layouting();
		view();
		setAction();
    }
	
	public Scene getScene() {
		return sc;
	}
	
}
