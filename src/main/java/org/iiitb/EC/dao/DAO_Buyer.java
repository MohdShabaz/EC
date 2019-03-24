package org.iiitb.EC.dao;
import org.iiitb.EC.model.Buyer;

import java.sql.*;

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

}