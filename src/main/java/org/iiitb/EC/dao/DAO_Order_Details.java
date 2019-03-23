package org.iiitb.EC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.iiitb.EC.dbcon.DatabaseConnection;
import org.iiitb.EC.model.Item;
import org.iiitb.EC.model.Shopping_Cart;

public class DAO_Order_Details {
	public static boolean Add_Order(int buyer_id,String shipping_address,String status,String order_date,int payment_type){
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		ArrayList<Shopping_Cart> list = new ArrayList<Shopping_Cart>();
		
		list = DAO_Shopping_cart.get_Shopping_Cart_details(buyer_id);
		int n = list.size();
		try {
			long last_order_id = get_last_order_id();
			long new_order_id = last_order_id+1;
			int total_amount=0;
			int item_id=0,quantity=0;
			Item item = new Item();
			for(int i=0;i<n;i++) {
				item_id = list.get(i).getItem_id();
				item = DAO_Item.Get_Item(item_id);
				quantity = list.get(i).getQuantity();
				float discount = item.getDiscount();
				float price = item.getPrice();
				total_amount += (price-(price*discount))*(quantity);
			}

			if(last_order_id==-1) new_order_id=1;
				System.out.println("last_order_id "+last_order_id);
			for(int i=0;i<n;i++) {
				item_id = list.get(i).getItem_id();
				quantity = list.get(i).getQuantity();
				
				
				String query = "insert into order_details(order_id,item_id,buyer_id,shipping_address,status,order_date,total_amount,payment_type,quantity)"+" VALUES "+
						"(?,?,?,?,?,?,?,?,?)";
						preparedStatement = conn.prepareStatement(query);
						preparedStatement.setLong(1, new_order_id);						
						preparedStatement.setInt(2, item_id);
						preparedStatement.setInt(3, buyer_id);
						preparedStatement.setString(4, shipping_address);
						preparedStatement.setString(5, status);
						preparedStatement.setString(6, order_date);
						preparedStatement.setInt(7, total_amount);
						preparedStatement.setInt(8, payment_type);
						preparedStatement.setInt(9, quantity);
						
						int rs = preparedStatement.executeUpdate();
//						if(rs==0) {
//							return false;
//						}			
			}
			
			boolean ans = DAO_Shopping_cart.clear_cart(buyer_id);
			
				return true;

		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static long get_last_order_id() {
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		try {

			String query = "select max(order_id) from order_details;";
			preparedStatement = conn.prepareStatement(query);

			ResultSet rs = preparedStatement.executeQuery();
			
			long order_id = -1;
			while(rs.next()) {
				order_id = rs.getLong("max(order_id)");
				
			}

		return order_id;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
