package org.iiitb.EC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.iiitb.EC.dbcon.DatabaseConnection;
import org.iiitb.EC.model.Category;
import org.iiitb.EC.model.Item;

public class DAO_Category {
	public static  boolean addCategory(String name) {
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		String query=null;
		query = "insert into category(category_name) VALUES " +
				 "(?);";
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, name);
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
	
	public static int getCategoryId(String categoryName) {
		int categoryId = -1;
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String query = "select id from category where category_name =" + '"' + categoryName + '"';
			preparedStatement = conn.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {;
				categoryId = rs.getInt("id");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryId;
	}
	
	public static ArrayList<Category> get_All_Category(){
		ArrayList<Category> list = new ArrayList<Category>();
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;		
		
		try {
			String query = "select * from category";
			
			preparedStatement = conn.prepareStatement(query);
			Category category_object = new Category();
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
					category_object.setCategory_name(rs.getString("category_name"));
					category_object.setId(rs.getInt("id"));
					list.add(category_object);
					category_object = new Category();
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;		
	}
	
	
	public static boolean renameCategory(String oldName, String newName) {
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		String query=null;
		query = "update category set category_name = " + '"' + newName + '"' + "where category_name ="
				+ '"' + oldName + '"';
		try {
			preparedStatement = conn.prepareStatement(query);
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
	public static boolean deleteCategory(String name) {
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		String query=null;
		query = "delete  from category where category_name = " + '"' + name + '"';
		try {
			preparedStatement = conn.prepareStatement(query);
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
	
}
