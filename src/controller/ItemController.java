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
			String itemStatus, String itemWishlist, String itemOfferStatus) {
		
		return itemModel.createItem(itemName, itemSize, itemPrice, itemCategory, itemStatus, itemWishlist, itemOfferStatus);
	}
	
}
