package org.iiitb.EC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.iiitb.EC.dbcon.DatabaseConnection;
import org.iiitb.EC.model.Buyer;
import org.iiitb.EC.model.Shopping_Cart;

public class DAO_Shopping_cart {

	public static boolean add_To_Shopping_Cart(int buyer_id,int item_id,int quantity)
	{
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		String query=null;
		query = "insert into shopping_cart(buyer_id,item_id,quantity) VALUES " +
				 "(?,?,?);";
		String check_query=null;
		
		check_query = "select quantity from shopping_cart where buyer_id=? and item_id=?";
		
		//check_query = "select * from shopping_cart where buyer_id=? and item_id=?)";
		ResultSet rs;
		try {
			preparedStatement = conn.prepareStatement(check_query);
			preparedStatement.setInt(1, buyer_id);
			preparedStatement.setInt(2, item_id);
			//preparedStatement.setInt(3, quantity);
			
			int quan=-1;
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				quan = rs.getInt("quantity");
				
				System.out.println("quan is "+quan);
			}
			
			if(quan>0) {
				update_Itemof_Shopping_Cart(buyer_id,item_id,quantity+quan);
			}
			
			else {
				preparedStatement = conn.prepareStatement(query);
				preparedStatement.setInt(1, buyer_id);
				preparedStatement.setInt(2, item_id);	
				preparedStatement.setInt(3, quantity);
				
				int result = preparedStatement.executeUpdate();
				if(result==0) return false;
				else return true;
			}

			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return true;
	}
	

	public static ArrayList<Shopping_Cart> get_Shopping_Cart_details(int buyer_id) {
		Shopping_Cart sc = new Shopping_Cart();
		ArrayList<Shopping_Cart> list = new ArrayList<Shopping_Cart>();
		//Connection conn = null;
		ResultSet rs;
		try {
			Connection conn = DatabaseConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = null;			
			String query = "select * from shopping_cart where buyer_id=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, buyer_id);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				sc.setId(rs.getInt("id"));
				sc.setBuyer_id(buyer_id);
				sc.setItem_id(rs.getInt("item_id"));
				sc.setQuantity(rs.getInt("quantity"));
				
				list.add(sc);
				sc = new Shopping_Cart();
			}
			
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return item.toString();
		return list;
	}
	
	
	public static boolean update_Itemof_Shopping_Cart(int buyer_id,int item_id,int quantity) {
		Shopping_Cart sc = new Shopping_Cart();
		 try {
		  Connection conn = DatabaseConnection.getConnection();

		 
		  PreparedStatement ps = conn.prepareStatement(
		         "UPDATE shopping_cart SET quantity = ? WHERE buyer_id = ? and item_id=?");

		  PreparedStatement ps1 = conn.prepareStatement(
			         "Delete from shopping_cart  WHERE buyer_id = ? and item_id=?");	
		  
		  if(quantity==0) {
			  ps1.setInt(1, buyer_id);
			  ps1.setInt(2, item_id);
			 
			  int rs = ps1.executeUpdate();
			  if(rs==0) return false;
			  else return true;
		  }
		  else {
			  ps.setInt(1, quantity);
			  ps.setInt(2, buyer_id);
			  ps.setInt(3, item_id);
			 
			  int rs = ps.executeUpdate();
			  if(rs==0) return false;
			  else return true;			  
		  }


		 } catch (SQLException e) {
		  System.out.println(e.getMessage());
		 }
		 //return item.toString();
		 return false;
		}
	
	public static boolean delete_Itemof_Shopping_Cart(int buyer_id,int item_id) {
		 try {
			  Connection conn = DatabaseConnection.getConnection();

			 
			  PreparedStatement ps = conn.prepareStatement(
			         "Delete from shopping_cart WHERE buyer_id = ? and item_id=?");

			 
			  ps.setInt(1, buyer_id);
			  ps.setInt(2, item_id);
			 
			  int rs = ps.executeUpdate();
			  if(rs==0) return false;
			  else return true;

			 } catch (SQLException e) {
			  System.out.println(e.getMessage());
			 }
			 //return item.toString();
			 return false;		
	}
	
	public static boolean clear_cart(int buyer_id) {
		 try {
			  Connection conn = DatabaseConnection.getConnection();

			 
			  PreparedStatement ps = conn.prepareStatement(
			         "Delete from shopping_cart WHERE buyer_id = ?");

			 
			  ps.setInt(1, buyer_id);
			 
			  int rs = ps.executeUpdate();
			  if(rs==0) return false;
			  else return true;

			 } catch (SQLException e) {
			  System.out.println(e.getMessage());
			 }
			 //return item.toString();
			 return false;		
	}
	
}
