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
	
}
