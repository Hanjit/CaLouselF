package view;

import java.util.ArrayList;
import java.util.Vector;

import controller.WishlistController;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Item;
import model.Wishlist;

public class WishlistPage extends Application{

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
		sc = new Scene(bp);
		gp = new GridPane();
		hb = new HBox();
		
		wishlistItems = new ArrayList<>();
		
		tvWishlist= new TableView<>();
		nameColumn = new TableColumn<>("name");
		sizeColumn = new TableColumn<>("size");
		priceColumn = new TableColumn<>("price");
		categoryColumn = new TableColumn<>("category");
		
		removeBtn = new Button("Remove");
		backBtn = new Button("Back");
	}
	
	private void layouting() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        tvWishlist.getColumns().addAll(nameColumn, sizeColumn, priceColumn, categoryColumn);

        hb.getChildren().addAll(removeBtn, backBtn);
        hb.setSpacing(10);

        bp.setTop(tvWishlist);
        bp.setBottom(hb);
	}

	private void view() {
		int userId = 2; // gak paham buat ngambil userIdnya
        wishlistItems = WishlistController.getInstance().viewWishlist(userId);

        tvWishlist.getItems().clear();
        tvWishlist.getItems().addAll(wishlistItems);
	}
	
	private void delete() {
		 Wishlist selectedItem = tvWishlist.getSelectionModel().getSelectedItem();
	        if (selectedItem != null) {
	            boolean result = WishlistController.getInstance().deleteWishlist(selectedItem.getItemId());
	            if (result) {
	                System.out.println("Remove success!");
	                view();
	            } else {
	                System.out.println("Remove failed!");
	            }
	        } else {
	            System.out.println("No item selected");
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
				
			}
		});
    }
	
	public void start(Stage primaryStage) throws Exception {
		initialize();
		layouting();
		view();
		setAction();
		
		primaryStage.setScene(sc);
		primaryStage.show();
	}
	
}
