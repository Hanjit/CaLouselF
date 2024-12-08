package controller;

import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
		alert.show();
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
	
	public boolean createItem(String itemName, String itemSize, String itemPrice, String itemCategory,
			String itemWishlist, String itemOfferStatus) {
		if (itemName.length() < 3) {
			errorAlert("Item name must be at least 3 characters long!");
		}
		
		if (itemCategory.length() < 3) {
			errorAlert("Item category must be at least 3 characters long!");
		}
		
		if (itemSize.isEmpty()) {
			errorAlert("Item size cannot be empty!");
		}
		
		if (isNumeric(itemPrice)) {
			errorAlert("Item price must be in number!");
		} else if(Integer.parseInt(itemPrice) == 0) {
			errorAlert("Item price cannot be zero");
		} else if(itemPrice.isEmpty()) {
			errorAlert("Item price cannot be empty!");
		}
		
		return itemModel.createItem(itemName, itemSize, itemPrice, itemCategory, itemWishlist, itemOfferStatus);
	}
	
	public boolean updateItem(int itemId, String itemName, String itemSize, String itemPrice, 
			String itemCategory, String itemWishlist, String itemOfferStatus) {
		if (itemName.length() < 3) {
			errorAlert("Item name must be at least 3 characters long!");
		}
		
		if (itemCategory.length() < 3) {
			errorAlert("Item category must be at least 3 characters long!");
		}
		
		if (itemSize.isEmpty()) {
			errorAlert("Item size cannot be empty!");
		}
		
		if (isNumeric(itemPrice)) {
			errorAlert("Item price must be in number!");
		} else if(Integer.parseInt(itemPrice) == 0) {
			errorAlert("Item price cannot be zero");
		} else if(itemPrice.isEmpty()) {
			errorAlert("Item price cannot be empty!");
		}
		
		return itemModel.updateItem(itemId, itemName, itemSize, itemPrice, itemCategory, itemWishlist, itemOfferStatus);
	}
	
	public boolean deleteItem(int itemId) {
		
		return itemModel.deleteItem(itemId);
	}
	
	public ArrayList<Item> getRequestedItem() {
		
		return itemModel.getRequestedItems();
	}
	
	public boolean approveItem(int itemId) {
		
		return itemModel.approveItem(itemId);
	}
	
	public boolean declineItem(int itemId, String itemReason) {
		
		if (itemReason.isEmpty()) {
			errorAlert("Reason cannot be empty");
		}
		
		return itemModel.declineItem(itemId, itemReason);
	}
	
	public ArrayList<Item> getDeclinedItems(int sellerId) {
		
		return itemModel.getDeclinedItems(sellerId);
	}
}
