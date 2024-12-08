package controller;

import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Transaction;

public class TransactionController {

	private static TransactionController tc = null;
	private Transaction transactionModel;
	private Alert alert = new Alert(AlertType.NONE);
	
	private TransactionController() {
		transactionModel = new Transaction();
	}
	
	public static TransactionController getInstance() {
		if (tc == null) {
			tc = new TransactionController();
		}
		
		return tc;
	}
	
	private void errorAlert(String message) {
		alert.setAlertType(AlertType.ERROR);
		alert.setContentText(message);
		alert.show();
	}
	
	public boolean createTransaction(int userId, int itemId) {
		
		return transactionModel.createTransaction(userId, itemId);
	}
	
	public ArrayList<Transaction> getTransaction(int userId) {
		// Validation
		return transactionModel.getTransaction(userId);
	}
	
}
