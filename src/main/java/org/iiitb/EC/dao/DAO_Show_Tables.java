package org.iiitb.EC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.iiitb.EC.dbcon.DatabaseConnection;
import org.iiitb.EC.model.BankAccount;
import org.iiitb.EC.model.Buyer;
import org.iiitb.EC.model.Item;
import org.iiitb.EC.model.Seller;

public class DAO_Show_Tables {

	//returns list of all items
	public static ArrayList<Item> get_All_Items() {
		ArrayList<Item> list = new ArrayList<Item>();
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;		
		
		try {
			String query = "select * from item_table;";
			
			preparedStatement = conn.prepareStatement(query);
			Item item_object = new Item();
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
					item_object.setItem_id(rs.getInt("item_id"));
					item_object.setDescription(rs.getString("description"));
					item_object.setName(rs.getString("name"));
					item_object.setPic_location(rs.getString("pic_location"));
					item_object.setCategory(rs.getInt("category"));
					item_object.setSub_category(rs.getInt("sub_category"));
					item_object.setBarcode(rs.getString("barcode"));
					item_object.setPrice(rs.getFloat("price"));
					item_object.setDiscount(rs.getFloat("discount"));
					
					list.add(item_object);
					item_object = new Item();
			}
			System.out.println("IM TOP 5 Items dskjc bjkdcnxs");
			System.out.println(list.size());
			for(int i=0;i<list.size();i++)
			{
				System.out.println(list.get(i).getItem_id());
			}
			System.out.println(list);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	//returns list of all buyer
	public static ArrayList<Buyer> get_All_Buyers() {
		ArrayList<Buyer> list = new ArrayList<Buyer>();
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;		
		
		try {
			String query = "select * from buyer_table;";
			
			preparedStatement = conn.prepareStatement(query);
			Buyer buyer_object = new Buyer();
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
					buyer_object.setBuyer_id(rs.getInt("buyer_id")); 
					buyer_object.setName(rs.getString("name"));  
					buyer_object.setMobile(rs.getString("mobile")); 
					buyer_object.setPassword(rs.getString("password"));
					buyer_object.setEmail(rs.getString("email")); 
					buyer_object.setDob(rs.getString("dob")); 
					buyer_object.setAddress_1("address_1"); 
					buyer_object.setAddress_2("address_2"); 
					
					list.add(buyer_object);
					buyer_object = new Buyer();
			}
			//System.out.println("IM TOP 5 Items dskjc bjkdcnxs");
			System.out.println(list.size());
			for(int i=0;i<list.size();i++)
			{
				System.out.println(list.get(i).getBuyer_id());
			}
			System.out.println(list);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	//returns list of all sellers
	public static ArrayList<Seller> get_All_Sellers() {
		ArrayList<Seller> list = new ArrayList<Seller>();
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;		
		
		try {
			String query = "select * from seller_table;";
			
			preparedStatement = conn.prepareStatement(query);
			Seller seller_object = new Seller();
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
					seller_object.setSeller_id(rs.getInt("seller_id"));  
					seller_object.setName(rs.getString("name")); 
					seller_object.setMobile(rs.getString("mobile")); 
					seller_object.setPassword(rs.getString("password"));
					seller_object.setEmail(rs.getString("email")); 
					seller_object.setAddress_1("address_1"); 
					seller_object.setAddress_2("address_2"); 
					
					list.add(seller_object);
					seller_object = new Seller();
			}
			//System.out.println("IM TOP 5 Items dskjc bjkdcnxs");
			System.out.println(list.size());
			for(int i=0;i<list.size();i++)
			{
				System.out.println(list.get(i).getSeller_id());
			}
			System.out.println(list);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	//returns list of all sellers_accounts
	public static ArrayList<BankAccount> get_All_Sellers_Accounts() {
		ArrayList<BankAccount> list = new ArrayList<BankAccount>();
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;		
		
		try {
			String query = "select * from seller_account_details;";
			
			preparedStatement = conn.prepareStatement(query);
			BankAccount bank_acc_object = new BankAccount();
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
					bank_acc_object.setHolderID(rs.getInt("seller_id"));   
					bank_acc_object.setAccountNumber(rs.getString("account_number"));   
					bank_acc_object.setCurrentBalance(rs.getInt("current_balance")); 
					
					list.add(bank_acc_object);
					bank_acc_object = new BankAccount();
			}
			//System.out.println("IM TOP 5 Items dskjc bjkdcnxs");
			System.out.println(list.size());
			for(int i=0;i<list.size();i++)
			{
				//System.out.println(list.get(i).getSeller_id());
			}
			System.out.println(list);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	//returns list of all sellers_accounts
	public static ArrayList<BankAccount> get_All_Buyer_Accounts() {
		ArrayList<BankAccount> list = new ArrayList<BankAccount>();
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;		
		
		try {
			String query = "select * from buyer_account_details;";
			
			preparedStatement = conn.prepareStatement(query);
			BankAccount bank_acc_object = new BankAccount();
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
					bank_acc_object.setHolderID(rs.getInt("buyer_id"));   
					bank_acc_object.setAccountNumber(rs.getString("account_number"));   
					bank_acc_object.setCurrentBalance(rs.getInt("current_balance")); 
					
					list.add(bank_acc_object);
					bank_acc_object = new BankAccount();
			}
			//System.out.println("IM TOP 5 Items dskjc bjkdcnxs");
			System.out.println(list.size());
			for(int i=0;i<list.size();i++)
			{
				//System.out.println(list.get(i).getSeller_id());
			}
			System.out.println(list);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	

	
}
