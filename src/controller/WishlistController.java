package controller;

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
		Wishlist wishlist = new Wishlist(itemId, userId);
		return wishlistModel.addWishlist(wishlist);
	}
	
	public boolean deleteWishlist(int wishlistId) {
		return wishlistModel.deleteWishlist(wishlistId);
	}
	
	public Vector<Wishlist> viewWishlist(int userId){
		return wishlistModel.viewWishlist(userId);
	}
}
