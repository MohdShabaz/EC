package org.iiitb.EC.dao;
import org.iiitb.EC.model.Buyer;
import org.iiitb.EC. model.Seller;
import java.sql.*;

import javax.ws.rs.core.HttpHeaders;

import org.iiitb.EC.dbcon.DatabaseConnection;

public class DAO_Seller {
	public static boolean add_Seller(String name,String mobile,String email,String password,String address_1,String address_2)
	{
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		String query=null;
		query = "insert into seller_table(name,mobile,email,password,address_1,address_2) VALUES " +
				 "(?,?,?,?,?,?);";
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, mobile);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, password);
			preparedStatement.setString(5, address_1);
			preparedStatement.setString(6, address_2);
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
		
		
		return true;
	}
	public static Seller get_seller_details(int seller_id) {
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
	
	//just dummy, replace with login

	
	public static int get_seller_id(String mobile) {
		ResultSet rs;
		try {
			Connection conn = DatabaseConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = null;			
			String query = "select seller_id from seller_table where mobile=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, mobile);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				int buyer_id = rs.getInt("seller_id");
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
	
	public static boolean add_item(int seller_id,String name,String barcode,String description,float price,float discount,String pic_location,int category,int sub_category,int quantity,int address,String dummy_1,String dummy_2,String dummy_3,String dummy_4) {
		
//		int seller_id = get_seller_id();
		
		try {
			
			if(DAO_Item.Check_Item(barcode)==-1) {
				
				boolean ans = DAO_Item.Add_Item(description, price, discount, name, pic_location,category, sub_category, barcode, dummy_1, dummy_2, dummy_3, dummy_4);
				
				if(ans==false) {
					return false;
				}
				else {
					
					int item_id = DAO_Item.Check_Item(barcode);
					
					if(DAO_Item_Seller.get_quantity(item_id, seller_id)==-1) {
						return DAO_Item_Seller.add_Item_Seller(seller_id, item_id, quantity, address);
					}
					else {
						int prequan=DAO_Item_Seller.get_quantity(item_id, seller_id);
						return DAO_Item_Seller.update_Item_Seller(prequan+quantity,item_id, seller_id);
					}
					
				}				
			}
			
			else {
				int item_id = DAO_Item.Check_Item(barcode);
				
				if(DAO_Item_Seller.get_quantity(item_id, seller_id)==-1) {
					return DAO_Item_Seller.add_Item_Seller(seller_id, item_id, quantity, address);					
				}
				else {
					int prequan=DAO_Item_Seller.get_quantity(item_id, seller_id);
					return DAO_Item_Seller.update_Item_Seller(prequan+quantity,item_id,seller_id);					
				}
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return true;
	}
	
	
	public static boolean add_quantity_to_existing_item(int seller_id,String barcode,int quantity) {
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			int check = DAO_Item.Check_Item(barcode);

			if(check!=-1) {
				int item_id = DAO_Item.get_item_id(barcode);
				int prequan = DAO_Item.get_quantity(item_id, seller_id);
				
				String query = "update item_seller set quantity=? where seller_id=? and item_id=?;";
				preparedStatement = conn.prepareStatement(query);
				preparedStatement.setInt(1, quantity+prequan);
				preparedStatement.setInt(2, seller_id);
				preparedStatement.setInt(3, item_id);
				
				
				int rs = preparedStatement.executeUpdate();
				if(rs==0) {
					return false;
				}
				else {
					return true;
				}				
			}
			return false;

		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	

	public static int Authenticate(String mobile,String Password) {
		ResultSet rs;
		try {
			Connection conn = DatabaseConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = null;			
			String query = "select seller_id from seller_table where mobile=? and password=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, mobile);
			preparedStatement.setString(2, Password);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				int seller_id = rs.getInt("seller_id");
				return seller_id;
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
	
	public static boolean update_Seller(int seller_id,String name,String mobile,String email,String password,String address_1,String address_2) {
		 Seller sell = new Seller();
		 try {
		  Connection conn = DatabaseConnection.getConnection();
		  //java.sql.PreparedStatement preparedStatement = null;  
		  //String query = "update buyer_table set name=?,dob=?,mobile=?,email=?,address_1=?,address_2=? where buyer_id=?";
		 
		  PreparedStatement ps = conn.prepareStatement(
		         "UPDATE seller_table SET name = ?,mobile=?,address_1=?,address_2=?,email=?,password=? WHERE seller_id = ?");

		 
		  ps.setString(1, name);
		  ps.setString(2, mobile);
		  ps.setString(3, address_1);
		  ps.setString(4, address_2);
		  ps.setString(5, email);
		  ps.setString(6, password);
		  ps.setInt(7, seller_id);
		 
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