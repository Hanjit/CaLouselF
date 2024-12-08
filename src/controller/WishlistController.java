package controller;

import java.util.ArrayList;
import java.util.Vector;

import model.Item;
import model.Wishlist;

public class WishlistController {
	
	private static WishlistController wc = null;
	private Wishlist wishlistModel;
	
	public WishlistController() {
		wishlistModel = new Wishlist();
	}
	
	public static WishlistController getInstance() {
		if (wc == null) {
			wc = new WishlistController();
		}
		
		return wc;
	}
	
	public boolean addWishlist(int itemId, int userId) {
		return wishlistModel.addWishlist(itemId, userId);
	}
	
	public boolean deleteWishlist(int wishlistId) {
		return wishlistModel.deleteWishlist(wishlistId);
	}
	
	public ArrayList<Wishlist> viewWishlist(int userId){
		return wishlistModel.viewWishlist(userId);
	}
}
