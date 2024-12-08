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
	
	private void errorAlert(String message) {
		alert.setAlertType(AlertType.ERROR);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	private boolean isNumeric(String string) {
		
		try {
			Integer.parseInt(string);
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}
	
	public static ItemController getInstance() {
		if (ic == null) {
			ic = new ItemController();
		}
		
		return ic;
	}
	
	public ArrayList<Item> getItems() {
		
		return itemModel.getItems();
	}
	
	public Item getItemById(int itemId) {
		
		return itemModel.getItemById(itemId);
	}
	
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
	
	public boolean deleteItem(int itemId) {
		
		return itemModel.deleteItem(itemId);
	}
	
	public ArrayList<Item> getRequestedItem() {
		
		return itemModel.getRequestedItems();
	}
	
	public ArrayList<Item> getAllItems(int sellerId) {
		
		return itemModel.getAllItems(sellerId);
	}
	
	public boolean approveItem(int itemId) {
		
		return itemModel.approveItem(itemId);
	}
	
	public boolean declineItem(int itemId) {
		
		TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Reason Required");
        dialog.setHeaderText("Enter your reason");
        
        Optional<String> result = dialog.showAndWait();
        
        String itemReason = result.get();
		
		if (itemReason.isEmpty()) {
			errorAlert("Reason cannot be empty");
		}
		
		return itemModel.declineItem(itemId, itemReason);
	}
	
	public ArrayList<Item> getDeclinedItems(int sellerId) {
		
		return itemModel.getDeclinedItems(sellerId);
	}
}
