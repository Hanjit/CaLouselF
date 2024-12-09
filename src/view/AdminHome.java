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
	
	private void resetButtonVisibility() {
		acceptBtn.setVisible(false);
		declineBtn.setVisible(false);
	}
	
	private void initialize() {
		bp = new BorderPane();
		sc = new Scene(bp);
		gp = new GridPane();
		hb = new HBox();
		
		items = new ArrayList<>();
		tvRequestItems = new TableView<>();
		
		nameColumn = new TableColumn<>("Name");
		categoryColumn = new TableColumn<>("Category");
		sizeColumn = new TableColumn<>("Size");
		priceColumn = new TableColumn<>("Price");
		
		acceptBtn = new Button("Accept");
		declineBtn = new Button("Decline");
	}
	
	private void layouting() {
		resetButtonVisibility();
		
		hb.getChildren().addAll(acceptBtn, declineBtn);
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		categoryColumn.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));
		sizeColumn.setCellValueFactory(new PropertyValueFactory<>("itemSize"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
		
		tvRequestItems.getColumns().addAll(nameColumn, categoryColumn, sizeColumn, priceColumn);
		
		bp.setTop(tvRequestItems);
		bp.setBottom(hb);
	}
	
	private void fillTable() {
		items = ItemController.getInstance().getRequestedItem();
		tvRequestItems.getItems().clear();
		tvRequestItems.getItems().addAll(items);
	}
	
	int tempId;
	
	private void addEvent() {
		tvRequestItems.setOnMouseClicked(e -> {
			TableSelectionModel<Item> modelSelection = tvRequestItems.getSelectionModel();
			modelSelection.setSelectionMode(SelectionMode.SINGLE);
			Item item = modelSelection.getSelectedItem();
			tempId = item.getItemId();
			acceptBtn.setVisible(true);
			declineBtn.setVisible(true);
		});
		
		acceptBtn.setOnMouseClicked(e -> {
			ItemController.getInstance().approveItem(tempId);
			fillTable();
			tempId = -1;
		});
		
		declineBtn.setOnMouseClicked(e -> {
			ItemController.getInstance().declineItem(tempId);
			fillTable();
			tempId = -1;
		});
	}
	
	public AdminHome() {
		initialize();
		layouting();
		fillTable();
		addEvent();
	}
	
	public Scene getScene() {
		return sc;
	}
	
}
