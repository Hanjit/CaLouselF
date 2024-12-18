package controller;

import java.util.ArrayList;
import model.Wishlist;

public class WishlistController {
	
	private static WishlistController wc = null;
	private Wishlist wishlistModel;
	
	public WishlistController() {
		wishlistModel = new Wishlist();
	}
	
	// Retrieves the singleton instance of WishlistController
	public static WishlistController getInstance() {
		if (wc == null) {
			wc = new WishlistController();
		}
		
		return wc;
	}
	
	// Add an item to the wishlist
	public boolean addWishlist(int itemId, int userId) {
		return wishlistModel.addWishlist(itemId, userId);
	}
	
	// Delete an item from the wishlist by wishlist ID
	public boolean deleteWishlist(int wishlistId) {
		return wishlistModel.deleteWishlist(wishlistId);
	}
	
	// Delete all wishlist entries for a specific item
	public boolean deleteAllWishlist(int itemId) {
		return wishlistModel.deleteAllWishlist(itemId);
	}
	
	// View all items in a user's wishlist
	public ArrayList<Wishlist> viewWishlist(int userId){
		return wishlistModel.viewWishlist(userId);
	}
}
