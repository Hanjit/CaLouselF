package controller;

import java.util.ArrayList;

import model.Item;

public class ItemController {
	
	private static ItemController ic = null;
	private Item itemModel;
	
	private ItemController() {
		itemModel = new Item();
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
		
		return itemModel.createItem(itemName, itemSize, itemPrice, itemCategory, itemWishlist, itemOfferStatus);
	}
	
	public boolean updateItem(int itemId, String itemName, String itemSize, String itemPrice, 
			String itemCategory, String itemWishlist, String itemOfferStatus) {
		
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
	
	public boolean declineItem(int itemId) {
		
		return itemModel.declineItem(itemId);
	}
}
