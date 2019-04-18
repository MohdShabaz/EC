package org.iiitb.EC.dao;
import org.iiitb.EC.model.Buyer;
import org.iiitb.EC.model.Item;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;

import org.iiitb.EC.dbcon.DatabaseConnection;

public class DAO_Buyer {
	public static boolean add_Buyer(String name,String dob,String mobile,String email,String address_1,String address_2,String password)
	{
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		String query=null;
		query = "insert into buyer_table(name,dob,mobile,address_1,address_2,email,password) VALUES " +
				 "(?,?,?,?,?,?,?);";
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, dob);
			preparedStatement.setString(3, mobile);
			preparedStatement.setString(4, address_1);
			preparedStatement.setString(5, address_2);
			preparedStatement.setString(6, email);
			preparedStatement.setString(7, password);
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
			return false;
		}
		
		
	}
	
	
	public static int get_buyer_id(String mobile) {
		Buyer buy = new Buyer();
		//Connection conn = null;
		ResultSet rs;
		try {
			Connection conn = DatabaseConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = null;			
			String query = "select buyer_id from buyer_table where mobile=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, mobile);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				int buyer_id = rs.getInt("buyer_id");
				return buyer_id;
			}
			else {
				//item.put("result", "fail");
				return -1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return item.toString();
		return -1;		
	}
	
	public static int Authenticate(String mobile,String Password) {
		ResultSet rs;
		try {
			Connection conn = DatabaseConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = null;			
			String query = "select buyer_id from buyer_table where mobile=? and password=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, mobile);
			preparedStatement.setString(2, Password);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				int buyer_id = rs.getInt("buyer_id");
				return buyer_id;
			}
			else {
				//item.put("result", "fail");
				return -1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return item.toString();
		return -1;
	}
	
	public static Buyer get_buyer_details(int buyer_id) {
		Buyer buy = new Buyer();
		//Connection conn = null;
		ResultSet rs;
		try {
			Connection conn = DatabaseConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = null;			
			String query = "select * from buyer_table where buyer_id=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, buyer_id);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				buy.setName(rs.getString("name"));
				buy.setDob(rs.getString("dob"));
				buy.setMobile(rs.getString("mobile"));
				buy.setAddress_1(rs.getString("address_1"));
				buy.setAddress_2(rs.getString("address_2"));
				buy.setEmail(rs.getString("email"));
				return buy;
			}
			else {
				//item.put("result", "fail");
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return item.toString();
		return null;
	}
	
	public static boolean update_Buyer(int buyer_id,String name,String dob,String mobile,String email,String address_1,String address_2) {
		 Buyer buy = new Buyer();
		 try {
		  Connection conn = DatabaseConnection.getConnection();
		  //java.sql.PreparedStatement preparedStatement = null;  
		  //String query = "update buyer_table set name=?,dob=?,mobile=?,email=?,address_1=?,address_2=? where buyer_id=?";
		 
		  PreparedStatement ps = conn.prepareStatement(
		         "UPDATE buyer_table SET name = ?, dob = ?,mobile=?,address_1=?,address_2=?,email=? WHERE buyer_id = ?");

		 
		  ps.setString(1, name);
		  ps.setString(2, dob);
		  ps.setString(3, mobile);
		  ps.setString(4, address_1);
		  ps.setString(5, address_2);
		  ps.setString(6, email);
		  ps.setInt(7, buyer_id);
		 
		  int rs = ps.executeUpdate();
		  if(rs==0) return false;
		  else return true;

		 } catch (SQLException e) {
		  System.out.println(e.getMessage());
		 }
		 //return item.toString();
		 return false;
		}
	public static boolean update_Buyer_Address(int buyer_id,String address_2) {
		 Buyer buy = new Buyer();
		 try {
		  Connection conn = DatabaseConnection.getConnection();
		  //java.sql.PreparedStatement preparedStatement = null;  
		  //String query = "update buyer_table set name=?,dob=?,mobile=?,email=?,address_1=?,address_2=? where buyer_id=?";
		 
		  PreparedStatement ps = conn.prepareStatement(
		         "UPDATE buyer_table SET address_2=? WHERE buyer_id = ?");

		 
		  
		  ps.setString(1, address_2);
		  ps.setInt(2, buyer_id);
		 
		  int rs = ps.executeUpdate();
		  if(rs==0) return false;
		  else return true;

		 } catch (SQLException e) {
		  System.out.println(e.getMessage());
		 }
		 //return item.toString();
		 return false;
		}
	
	public static boolean Delete_Buyer(int buyer_id)
	{
	 Connection conn=DatabaseConnection.getConnection();
	 PreparedStatement preparedStatement = null;
	 String query = "Delete from buyer_table where buyer_id=?";
	 try {
	  preparedStatement = conn.prepareStatement(query);
	  preparedStatement.setInt(1, buyer_id);
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
	
	public static JSONArray get_All_Buyer_Details() throws JSONException {
		 Connection conn=DatabaseConnection.getConnection();
		 PreparedStatement preparedStatement = null;  
		 System.out.println("In get_Seller_Items_Details");
		 JSONArray item_json_array = null;
		 try {
//		  String query = "select item_table.item_id, item_table.name, item_table.discount, "
//		    + "item_table.price, item_table.barcode, item_table.pic_location, "
//		    + "item_table.description, category.category_name, "
//		    + "sub_category.sub_category_name, item_seller.quantity from item_seller "
//		    + "inner join seller_table on item_seller.seller_id = seller_table.seller_id "
//		    + "and seller_table.seller_id = ? inner join item_table on"
//		    + " item_seller.item_id = item_table.item_id inner join category on "
//		    + "category.id = item_table.category inner join sub_category on "
//		    + "sub_category.id = item_table.sub_category;";
			 String query="select * from buyer_table,buyer_account_details where buyer_table.buyer_id=buyer_account_details.buyer_id";
		  
		  preparedStatement = conn.prepareStatement(query);
//		  preparedStatement.setInt(1, seller_id);
//		  Item item_object = new Item();
		  ResultSet rs = preparedStatement.executeQuery();
		  item_json_array = new JSONArray();
		//   for(int i=0;i<5;i++) {
		  while(rs.next()) {
//		    if(rs.next()) {
		    System.out.println("In get_All_Buyer_Details, in loop");
		    JSONObject item_json = new JSONObject();
		    item_json.put("buyer_id", rs.getString("mobile"));
		    item_json.put("buyer_name", rs.getString("name"));
		    item_json.put("buyer_balance", rs.getInt("current_balance"));
		    System.out.println(item_json);
		    item_json_array.put(item_json);
		    

//		    }
		  }
		  return item_json_array;
		  
		 }catch (SQLException e) {
		  e.printStackTrace();
		 }
		 
		 return item_json_array;
		}


public static JSONArray get_All_Buyer_Order_Details() throws JSONException {
	 Connection conn=DatabaseConnection.getConnection();
	 PreparedStatement preparedStatement = null;  
	 System.out.println("In get_Seller_Items_Details");
	 JSONArray item_json_array = null;
	 try {
//	  String query = "select item_table.item_id, item_table.name, item_table.discount, "
//	    + "item_table.price, item_table.barcode, item_table.pic_location, "
//	    + "item_table.description, category.category_name, "
//	    + "sub_category.sub_category_name, item_seller.quantity from item_seller "
//	    + "inner join seller_table on item_seller.seller_id = seller_table.seller_id "
//	    + "and seller_table.seller_id = ? inner join item_table on"
//	    + " item_seller.item_id = item_table.item_id inner join category on "
//	    + "category.id = item_table.category inner join sub_category on "
//	    + "sub_category.id = item_table.sub_category;";
		 String query="select DISTINCT item_table.item_id as ite_id, item_table.barcode as it_id,item_table.price as it_price,seller_table.mobile as s_id ,buyer_table.mobile as b_id,item_table.pic_location as pic_location from item_table,buyer_table,seller_table,item_seller,order_details where item_table.item_id=order_details.item_id and buyer_table.buyer_id=order_details.buyer_id and item_table.item_id=item_seller.item_id and item_seller.seller_id=seller_table.seller_id";
		 
	  preparedStatement = conn.prepareStatement(query);
//	  preparedStatement .setString(1, buyer_id);
//	  preparedStatement.setInt(1, seller_id);
//	  Item item_object = new Item();
	  ResultSet rs = preparedStatement.executeQuery();
	  item_json_array = new JSONArray();
	//   for(int i=0;i<5;i++) {
	  while(rs.next()) {
//	    if(rs.next()) {
	    System.out.println("In get_All_Buyer_Details, in loop");
	    JSONObject item_json = new JSONObject();
	    item_json.put("item_id", rs.getString("it_id"));
	    item_json.put("item_price", rs.getFloat("it_price"));
	    item_json.put("seller_id", rs.getString("s_id"));
	    item_json.put("buyer_id", rs.getString("b_id"));
	    
	    int ite_id=rs.getInt("ite_id");
//	    int rating=DAO_Orde
	    
	    
	    item_json.put("pic_location", rs.getString("pic_location"));
	    System.out.println(item_json);
	    item_json_array.put(item_json);
	    

//	    }
	  }
	  return item_json_array;
	  
	 }catch (SQLException e) {
	  e.printStackTrace();
	 }
	 
	 return item_json_array;
	}

}