package org.iiitb.EC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.iiitb.EC.dbcon.DatabaseConnection;
import org.iiitb.EC.model.Item;
import org.iiitb.EC.model.Item_Seller;
import org.iiitb.EC.model.Seller;

public class DAO_Item_Seller {
	public static boolean add_Item_Seller(int seller_id,int item_id,int quantity,int address)
	{
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		String query=null;
		query = "insert into item_seller(seller_id,item_id,quantity,address) VALUES " +
				 "(?,?,?,?);";
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, seller_id);
			preparedStatement.setInt(2, item_id);
			preparedStatement.setInt(3, quantity);
			preparedStatement.setInt(4, address);
			int rs = preparedStatement.executeUpdate();
			if(rs==0) {
				return false;
			}
			else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
		
		
	}
	
	public static boolean update_Item_Seller(int quantity,int item_id,int seller_id) {
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		String query=null;
		query = "update item_seller set quantity=? where item_id=? and seller_id=? ;";
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, quantity);
			preparedStatement.setInt(2, item_id);
			preparedStatement.setInt(3, seller_id);
			int rs = preparedStatement.executeUpdate();
			if(rs==0) {
				return false;
			}
			else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
				
	}
	
	public static boolean update_Order_Item_Seller(int order_id,int item_id) {
		int seller_id=get_seller_id(item_id);
		int initial_quantity=DAO_Item.get_quantity(item_id, seller_id);
		int quantity=DAO_Order_Details.get_quantity(order_id, item_id);
		
		int final_quantity=initial_quantity-quantity;
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		String query=null;
		query = "update item_seller set quantity=? where item_id=? and seller_id=? ;";
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, final_quantity);
			preparedStatement.setInt(2, item_id);
			preparedStatement.setInt(3, seller_id);
			int rs = preparedStatement.executeUpdate();
			if(rs==0) {
				return false;
			}
			else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
				
	}
	
	//meth can also be used to check if the entry is present or not
	public static int get_quantity(int item_id,int seller_id) {
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String query = "SELECT quantity FROM item_seller where item_id=? and seller_id=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, item_id);
			preparedStatement.setInt(2, seller_id);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				return rs.getInt("quantity");
			}
			else {
				return -1;
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static ArrayList<Item_Seller> get_all_item_seller(int seller_id){
		ArrayList<Item_Seller> is = new ArrayList<Item_Seller>();
		
		Item_Seller item_sell = new Item_Seller();

		ResultSet rs;
		try {
			Connection conn = DatabaseConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = null;			
			String query = "select * from item_seller where seller_id=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, seller_id);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				item_sell.setQuantity(rs.getInt("quantity"));
				item_sell.setAddress(rs.getInt("address"));
				item_sell.setItem_id(rs.getInt("item_id"));
				
				int item_id = rs.getInt("item_id");
				Item item = new Item();
				
				item = DAO_Item.Get_Item(item_id);
				
				item_sell.setItem(item);
				
				is.add(item_sell);
			}
			return is;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return item.toString();
		//return sell;
		
		
		return null;
	}
	
	
	public static boolean delete_item_Seller(int item_id,int seller_id){
				
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String query = "Delete FROM item_seller where item_id=? and seller_id=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, item_id);
			preparedStatement.setInt(2, seller_id);
			int rs = preparedStatement.executeUpdate();
			if(rs==0) {
				return false;
			}
			else {
				return true;
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	public static int get_seller_id(int item_id) {
//		Seller sell = new Seller();
		//Connection conn = null;
		ResultSet rs;
		int seller_id=-1;
		try {
			Connection conn = DatabaseConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = null;			
			String query = "select seller_id from item_seller where item_id=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, item_id);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				seller_id=rs.getInt("seller_id");
				
			}
			else {
				//item.put("result", "fail");
				return -1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return item.toString();
		return seller_id;
	}
	public static Seller get_seller_details(int item_id) {
		int seller_id=get_seller_id(item_id);
		
		Seller sell = new Seller();
		//Connection conn = null;
		ResultSet rs;
		try {
			Connection conn = DatabaseConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = null;			
			String query = "select * from seller_table where seller_id=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, seller_id);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				//added seller id
				sell.setSeller_id(seller_id);
				sell.setName(rs.getString("name"));
				sell.setMobile(rs.getString("mobile"));
				sell.setEmail(rs.getString("email"));
				sell.setPassword(rs.getString("password"));
				sell.setAddress_1(rs.getString("address_1"));
				sell.setAddress_2(rs.getString("address_2"));
				
			}
			else {
				//item.put("result", "fail");
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return item.toString();
		return sell;
	}
}