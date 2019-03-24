package org.iiitb.EC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.iiitb.EC.dbcon.DatabaseConnection;
import org.iiitb.EC.model.Category;
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
			String query = "select * from sub_category where id=?";
			
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, id);
			Sub_Category sub_category_object = new Sub_Category();
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
					sub_category_object.setCategory_id(rs.getInt("category_id"));
					sub_category_object.setSub_category_name(rs.getString("sub_category_name"));
					
					list.add(sub_category_object);
					sub_category_object = new Sub_Category();
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;		
	}
}
