package org.iiitb.EC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.iiitb.EC.dbcon.DatabaseConnection;
import org.iiitb.EC.model.Category;
import org.iiitb.EC.model.Item;
import org.iiitb.EC.model.Sub_Category;

public class DAO_Sub_Category {
	public static  boolean addSub_Category(int category_id,String name) {
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		String query=null;
		query = "insert into sub_category(category_id,sub_category_name) VALUES " +
				 "(?,?);";
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, category_id);
			preparedStatement.setString(2, name);
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
	
	public static ArrayList<Sub_Category> get_All_Sub_Category(int id){
		ArrayList<Sub_Category> list = new ArrayList<Sub_Category>();
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;		
		
		try {
			String query = "select * from sub_category where category_id=?";
			
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, id);
			Sub_Category sub_category_object = new Sub_Category();
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
					sub_category_object.setCategory_id(rs.getInt("category_id"));
					sub_category_object.setSub_category_name(rs.getString("sub_category_name"));
					sub_category_object.setId(rs.getInt("id"));
					list.add(sub_category_object);
					sub_category_object = new Sub_Category();
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;		
	}
	public static ArrayList<Item> get_All_Items_Sub_Category(int sub_category_id){
		  ArrayList<Item> list = new ArrayList<Item>();
		  Connection conn=DatabaseConnection.getConnection();
		  PreparedStatement preparedStatement = null;  
		  
		  try {
		   String query = "select * from item_table where sub_category=?";
		  
		   preparedStatement = conn.prepareStatement(query);
		   preparedStatement.setInt(1, sub_category_id);
		   Item item_object = new Item();
		   ResultSet rs = preparedStatement.executeQuery();
		   while(rs.next()) {
		     item_object.setItem_id(rs.getInt("item_id"));
		     item_object.setBarcode(rs.getString("barcode"));
		     item_object.setCategory(rs.getInt("category"));
		     item_object.setDescription(rs.getString("description"));
		     item_object.setDiscount(rs.getFloat("discount"));
		     item_object.setName(rs.getString("name"));
		     item_object.setPic_location(rs.getString("pic_location"));
		     item_object.setPrice(rs.getFloat("price"));
		     item_object.setSub_category(rs.getInt("sub_category"));
		    
		    
		     list.add(item_object);
		     item_object = new Item();
		   }
		  
		  }catch (SQLException e) {
		   e.printStackTrace();
		  }
		  
		  return list;  
	 }
}
