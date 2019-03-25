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
					category_object.setId(rs.getInt("id"));
					category_object.setCategory_name(rs.getString("category_name"));
					
					list.add(category_object);
					category_object = new Category();
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;		
	}
	
}
