package org.iiitb.EC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.iiitb.EC.dbcon.DatabaseConnection;
import org.iiitb.EC.model.BankAccount;
import org.iiitb.EC.model.Buyer;

public class DAO_BankAccount {
	public static int getBuyerAccountBalance(int buyer_id) {
		BankAccount bankAccount = new BankAccount();
		ResultSet rs;
		try {
			Connection conn = DatabaseConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = null;			
			String query = "select * from buyer_account_details where buyer_id=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, buyer_id);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				bankAccount.setAccountNumber(rs.getString("account_number"));
				bankAccount.setCurrentBalance(rs.getInt("current_balance"));
				bankAccount.setHolderID(rs.getInt("buyer_id"));
				return bankAccount.getCurrentBalance();
			}
			else {
				return 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static boolean setBuyerAccountBalance(int buyer_id, long newBalance) {
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		String query=null;
		query = "update buyer_account_details set current_balance = " + '"' + newBalance + '"' + " where buyer_id ="
				+ '"' + buyer_id + '"';
		try {
			preparedStatement = conn.prepareStatement(query);
			int rs = preparedStatement.executeUpdate();
			if(rs == 0) {
				return false;
			}
			else {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;		
		}
	}
	
	public static int getSellerAccountBalance(int seller_id) {
		BankAccount bankAccount = new BankAccount();
		ResultSet rs;
		try {
			Connection conn = DatabaseConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = null;			
			String query = "select * from seller_account_details where buyer_id=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, seller_id);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				bankAccount.setAccountNumber(rs.getString("account_number"));
				bankAccount.setCurrentBalance(rs.getInt("current_balance"));
				bankAccount.setHolderID(rs.getInt("seller_id"));
				return bankAccount.getCurrentBalance();
			}
			else {
				return 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static boolean setSellerAccountBalance(int seller_id, long newBalance) {
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		String query=null;
		query = "update seller_account_details set current_balance = " + '"' + newBalance + '"' + " where seller_id ="
				+ '"' + seller_id + '"';
		try {
			preparedStatement = conn.prepareStatement(query);
			int rs = preparedStatement.executeUpdate();
			if(rs == 0) {
				return false;
			}
			else {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;		
		}
	}
	
	public static int getEbayAccountBalance() {
		BankAccount bankAccount = new BankAccount();
		ResultSet rs;
		try {
			Connection conn = DatabaseConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = null;			
			String query = "select * from ebay_account_details";
			preparedStatement = conn.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				bankAccount.setAccountNumber(rs.getString("account_number"));
				bankAccount.setCurrentBalance(rs.getInt("current_balance"));
				bankAccount.setHolderID(rs.getInt("id"));
				return bankAccount.getCurrentBalance();
			}
			else {
				return 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static boolean setEbayAccountBalance(long newBalance) {
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		String query = null;
		query = "update ebay_account_details set current_balance = " + '"' + newBalance + '"' + " where account_name = " + '"' + "ebay_account" + '"';
		try {
			preparedStatement = conn.prepareStatement(query);
			int rs = preparedStatement.executeUpdate();
			if(rs == 0) {
				return false;
			}
			else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;		
		}
	}
	
	public static BankAccount getEbayAccount() {
		BankAccount bankAccount = new BankAccount();
		ResultSet rs;
		try {
			Connection conn = DatabaseConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = null;			
			String query = "select * from ebay_account_details";
			preparedStatement = conn.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				bankAccount.setAccountNumber(rs.getString("account_number"));
				bankAccount.setCurrentBalance(rs.getInt("current_balance"));
				bankAccount.setHolderID(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bankAccount;
	}
	
	public static boolean performTransaction(int transactionAmount, int order_id, int item_id, int buyer_id, int seller_id) {
		int buyerBalance = getBuyerAccountBalance(buyer_id);
		
		if (buyerBalance < transactionAmount) {
			System.out.println("Buyer balance too low to make the purchase.");
			return false;
		}
		
		String updateBuyerBalanceQuery = "update buyer_account_details set current_balance = " + '"' + (buyerBalance - transactionAmount) + '"' + " where buyer_id = " + '"' + buyer_id + '"' + " and item_id = " + '"' + item_id + '"';
		try {
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement preparedStatement = null;
			preparedStatement = conn.prepareStatement(updateBuyerBalanceQuery);
			int result = preparedStatement.executeUpdate();
			if(result == 0) {
				return false;
			}
			else {
				boolean res = DAO_Order_Details.change_status(order_id, "Order Placed");
				
				if (res) {
					BankAccount ebayAccount = DAO_BankAccount.getEbayAccount();
					String updateEbayBalanceQuery = "update ebay_account_details set current_balance = " + '"' + (ebayAccount.getCurrentBalance() + transactionAmount) + '"' + " where account_name = " + '"' + "ebay_account" + '"';
					preparedStatement = conn.prepareStatement(updateEbayBalanceQuery);
					int ebay_trans_res = preparedStatement.executeUpdate();
					if (ebay_trans_res == 0) {
						return false;
					} else {
						return true;
					}
				} else {
					return false;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;		
		}			
	}
	
	public static boolean performTransaction2(int transactionAmount, int order_id, int item_id, int buyer_id, int seller_id) {
		int sellerBalance = getSellerAccountBalance(seller_id);
		String updateSellerBalanceQuery = "update seller_account_details set current_balance = " + '"' + (sellerBalance + transactionAmount) + '"' + " where seller_id = " + '"' + seller_id + '"'  + " and item_id = " + '"' + item_id + "'";
		try {
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement preparedStatement = null;
			preparedStatement = conn.prepareStatement(updateSellerBalanceQuery);
			int result = preparedStatement.executeUpdate();
			if(result == 0) {
				return false;
			}
			else {
				// boolean res = DAO_Order_Details.change_status(order_id, "Dispatch");
				
				BankAccount ebayAccount = DAO_BankAccount.getEbayAccount();
				String updateEbayBalanceQuery = "update ebay_account_details set current_balance = " + '"' + (ebayAccount.getCurrentBalance() - transactionAmount) + '"' + " where account_name = " + '"' + "ebay_account" + '"';
				preparedStatement = conn.prepareStatement(updateEbayBalanceQuery);
				int ebay_trans_res = preparedStatement.executeUpdate();
				if (ebay_trans_res == 0) {
					return false;
				} else {
					return true;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;		
		}			
	}
	
	public static boolean checkAccountValidity(int buyer_id, String accountNumber) {
		ResultSet rs;
		try {
			Connection conn = DatabaseConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = null;			
			String query = "select * from buyer_account_details where buyer_id=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, buyer_id);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				if (accountNumber.equals(rs.getString("account_number"))) {
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}