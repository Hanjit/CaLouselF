package controller;

import java.util.ArrayList;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import model.Item;

public class ItemController {
	
	private static ItemController ic = null;
	private Item itemModel;
	private Alert alert = new Alert(AlertType.NONE);
	
	private ItemController() {
		itemModel = new Item();
	}
	
	// Displays an error alert with the provided message
	private void errorAlert(String message) {
		alert.setAlertType(AlertType.ERROR);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	// Validates if a given string is numeric
	private boolean isNumeric(String string) {
		
		try {
			Integer.parseInt(string);
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}
	
	// Retrieves the singleton instance of ItemController
	public static ItemController getInstance() {
		if (ic == null) {
			ic = new ItemController();
		}
		
		return ic;
	}
	
	// Retrieves all items from the model
	public ArrayList<Item> getItems() {
		
		return itemModel.getItems();
	}
	
	// Retrieves an item by its ID
	public Item getItemById(int itemId) {
		
		return itemModel.getItemById(itemId);
	}
	
	// Creates a new item after validating the input
	public boolean createItem(String itemName, String itemSize, String itemPrice, String itemCategory,
			String itemWishlist, String itemOfferStatus, int sellerId) {
		if (itemName.length() < 3) {
			errorAlert("Item name must be at least 3 characters long!");
			return false;
		}
		
		if (itemCategory.length() < 3) {
			errorAlert("Item category must be at least 3 characters long!");
			return false;
		}
		
		if (itemSize.isEmpty()) {
			errorAlert("Item size cannot be empty!");
			return false;
		}
		
		if (isNumeric(itemPrice)) {
			errorAlert("Item price must be in number!");
			return false;
		} else if(Integer.parseInt(itemPrice) == 0) {
			errorAlert("Item price cannot be zero");
			return false;
		} else if(itemPrice.isEmpty()) {
			errorAlert("Item price cannot be empty!");
			return false;
		}
		
		return itemModel.createItem(itemName, itemSize, itemPrice, itemCategory, itemWishlist, itemOfferStatus, sellerId);
	}
	
	// Updates an existing item after validating the input
	public boolean updateItem(int itemId, String itemName, String itemSize, String itemPrice, 
			String itemCategory) {
		if (itemName.length() < 3) {
			errorAlert("Item name must be at least 3 characters long!");
			return false;
		}
		
		if (itemCategory.length() < 3) {
			errorAlert("Item category must be at least 3 characters long!");
			return false;
		}
		
		if (itemSize.isEmpty()) {
			errorAlert("Item size cannot be empty!");
			return false;
		}
		
		if (isNumeric(itemPrice)) {
			errorAlert("Item price must be in number!");
			return false;
		} else if(Integer.parseInt(itemPrice) == 0) {
			errorAlert("Item price cannot be zero");
			return false;
		} else if(itemPrice.isEmpty()) {
			errorAlert("Item price cannot be empty!");
			return false;
		}
		
		return itemModel.updateItem(itemId, itemName, itemSize, itemPrice, itemCategory);
	}
	
	// Deletes an item by its ID
	public boolean deleteItem(int itemId) {
		
		return itemModel.deleteItem(itemId);
	}
	
	// Retrieves all requested items
	public ArrayList<Item> getRequestedItem() {
		
		return itemModel.getRequestedItems();
	}
	
	//	Retrieves all items for a given seller
	public ArrayList<Item> getAllItems(int sellerId) {
		
		return itemModel.getAllItems(sellerId);
	}
	
	// Approves an item by its ID
	public boolean approveItem(int itemId) {
		
		return itemModel.approveItem(itemId);
	}
	
	// Declines an item by its ID, with a reason
	public boolean declineItem(int itemId) {
		
		TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Reason Required");
        dialog.setHeaderText("Enter your reason");
        
        Optional<String> result = dialog.showAndWait();
        
        String itemReason = result.get();
		
		if (itemReason.isEmpty()) {
			errorAlert("Reason cannot be empty");
			return false;
		}
		
		if (itemModel.declineItem(itemId, itemReason)) {
			return itemModel.deleteItem(itemId);
		}
		
		return false;
	}
	
	// Retrieves all declined items for a given seller
	public ArrayList<Item> getDeclinedItems(int sellerId) {
		
		return itemModel.getDeclinedItems(sellerId);
	}
}
