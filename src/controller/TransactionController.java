package controller;

import java.util.ArrayList;

import model.Transaction;

public class TransactionController {

	private static TransactionController tc = null;
	private Transaction transactionModel;
	
	private TransactionController() {
		transactionModel = new Transaction();
	}
	
	public static TransactionController getInstance() {
		if (tc == null) {
			tc = new TransactionController();
		}
		
		return tc;
	}
	
	public boolean createTransaction(String userId, String itemId) {
		
		return transactionModel.createTransaction(userId, itemId);
	}
	
	public ArrayList<Transaction> getTransaction(String userId) {
		// Validation
		return transactionModel.getTransaction(userId);
	}
	
}