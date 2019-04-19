package org.iiitb.EC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.iiitb.EC.dbcon.DatabaseConnection;
import org.iiitb.EC.model.Buyer;
import org.iiitb.EC.model.wishlist;

public class DAO_wishlist {

	public static boolean add_To_wishlist(int buyer_id,int item_id)
	{
		System.out.println("In add to wishlist");
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		String query=null;
		query = "insert into wishlist(buyer_id,item_id) VALUES " +
				 "(?,?);";
		String check_query=null;
		
		//check_query = "select quantity from wishlist where buyer_id=? and item_id=?";
		
		check_query = "select * from wishlist where buyer_id=? and item_id=?";
		ResultSet rs;
		try {
			System.out.println("IN TRY---------------------->");
			preparedStatement = conn.prepareStatement(check_query);
			preparedStatement.setInt(1, buyer_id);
			preparedStatement.setInt(2, item_id);
//			//preparedStatement.setInt(3, quantity);
//			
//			int quan=-1;
			System.out.println(preparedStatement);
			rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				System.out.println("IN IF ------------------------>");
				//quan = rs.getInt("quantity");
				
				System.out.println("alreagy in wishlist");
			}		
			else {
				System.out.println("IN ELSE ------------------------>");
				preparedStatement = conn.prepareStatement(query);
				preparedStatement.setInt(1, buyer_id);
				preparedStatement.setInt(2, item_id);	
				
				int result = preparedStatement.executeUpdate();
				if(result==0) return false;
				else return true;
			}

			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("IN Catch---------------------->");
			e.printStackTrace();
		}		
		return true;
	}
	

	public static ArrayList<wishlist> get_wishlist_details(int buyer_id) {
		wishlist sc = new wishlist();
		ArrayList<wishlist> list = new ArrayList<wishlist>();
		//Connection conn = null;
		ResultSet rs;
		try {
			Connection conn = DatabaseConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = null;			
			String query = "select * from wishlist where buyer_id=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, buyer_id);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				sc.setId(rs.getInt("id"));
				sc.setBuyer_id(buyer_id);
				sc.setItem_id(rs.getInt("item_id"));
				
				list.add(sc);
				sc = new wishlist();
			}
			
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return item.toString();
		return list;
	}
	
	
//	public static boolean update_Itemof_Shopping_Cart(int buyer_id,int item_id,int quantity) {
//		wishlist sc = new wishlist();
//		 try {
//		  Connection conn = DatabaseConnection.getConnection();
//
//		 
//		  PreparedStatement ps = conn.prepareStatement(
//		         "UPDATE wishlist SET quantity = ? WHERE buyer_id = ? and item_id=?");
//
//		  PreparedStatement ps1 = conn.prepareStatement(
//			         "Delete from wishlist  WHERE buyer_id = ? and item_id=?");	
//		  
//		  if(quantity==0) {
//			  ps1.setInt(1, buyer_id);
//			  ps1.setInt(2, item_id);
//			 
//			  int rs = ps1.executeUpdate();
//			  if(rs==0) return false;
//			  else return true;
//		  }
//		  else {
//			  ps.setInt(1, quantity);
//			  ps.setInt(2, buyer_id);
//			  ps.setInt(3, item_id);
//			 
//			  int rs = ps.executeUpdate();
//			  if(rs==0) return false;
//			  else return true;			  
//		  }
//
//
//		 } catch (SQLException e) {
//		  System.out.println(e.getMessage());
//		 }
//		 //return item.toString();
//		 return false;
//		}
	
	public static boolean delete_Itemof_wishlist(int buyer_id,int item_id) {
		 try {
			  Connection conn = DatabaseConnection.getConnection();

			 
			  PreparedStatement ps = conn.prepareStatement(
			         "Delete from wishlist WHERE buyer_id = ? and item_id=?");

			 
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
	
	public static boolean move_Item_to_cart(int buyer_id,int item_id) {
	//	 try {
		//	  Connection conn = DatabaseConnection.getConnection();
			  int seller_id=DAO_Item_Seller.get_seller_id(item_id);
			  if(DAO_Item.get_quantity(item_id,seller_id)<0)
			  {
				  return false;
			  }
			  boolean result1=DAO_Shopping_cart.add_To_Shopping_Cart(buyer_id,item_id,1);
			  boolean result2=false;
			  if(result1)
			  {
				  result2= delete_Itemof_wishlist(buyer_id,item_id);
			  }
			  return (result1 && result2);

//			 
//			  PreparedStatement ps = conn.prepareStatement(
//			         "Delete from wishlist WHERE buyer_id = ? and item_id=?");
//
//			 
//			  ps.setInt(1, buyer_id);
//			  ps.setInt(2, item_id);
//			 
//			  int rs = ps.executeUpdate();
//			  if(rs==0) return false;
//			  else return true;

	//
			 //return item.toString();
		//	 return false;		
	}
	
	public static boolean clear_cart(int buyer_id) {
		 try {
			  Connection conn = DatabaseConnection.getConnection();

			 
			  PreparedStatement ps = conn.prepareStatement(
			         "Delete from wishlist WHERE buyer_id = ?");

			 
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
