package org.iiitb.EC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.iiitb.EC.dbcon.DatabaseConnection;
import org.iiitb.EC.model.Item;
import org.iiitb.EC.model.Order_Details;
import org.iiitb.EC.model.Shopping_Cart;
import org.json.JSONException;
import org.json.JSONObject;

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
				String barcode=item.getBarcode();
				total_amount += (price-(price*discount))*(quantity);
			}

			if(last_order_id==-1) new_order_id=1;
				System.out.println("last_order_id "+last_order_id);
			for(int i=0;i<n;i++) {
				item_id = list.get(i).getItem_id();
				quantity = list.get(i).getQuantity();
//				item = DAO_Item.Get_Item(item_id);
				int seller_id=DAO_Item_Seller.get_seller_id(item_id);
				
				
				String query = "insert into order_details(order_id,item_id,buyer_id,shipping_address,status,order_date,total_amount,payment_type,quantity,seller_id)"+" VALUES "+
						"(?,?,?,?,?,?,?,?,?,?)";
						preparedStatement = conn.prepareStatement(query);
						preparedStatement.setLong(1, new_order_id);						
						preparedStatement.setInt(2, item_id);
						item = DAO_Item.Get_Item(item_id);
						float price = item.getPrice();
						float discount = item.getDiscount();
						int final_price = (int)(price-(price*discount));
						if(DAO_ClearanceSale.item_exist_in_clearance_sale(item_id)) {
							final_price = (int)(final_price*(1-DAO_ClearanceSale.get_clearance_sale_discount()));
						}
						preparedStatement.setInt(3, buyer_id);
						preparedStatement.setString(4, shipping_address);
						preparedStatement.setString(5, status);
						preparedStatement.setString(6, order_date);
						preparedStatement.setInt(7, final_price);
						preparedStatement.setInt(8, payment_type);
						if(get_deal(item_id, seller_id) == 1) {
							quantity *= 2;
						}
						preparedStatement.setInt(9, quantity);
						preparedStatement.setInt(10, seller_id);
						
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
	
	public static boolean Add_Order_Shop_Now(int buyer_id, int item_id, int quantity, int amount, String shipping_address,String status,String order_date,int payment_type,float cashback){
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		ArrayList<Shopping_Cart> list = new ArrayList<Shopping_Cart>();
		
		list = DAO_Shopping_cart.get_Shopping_Cart_details(buyer_id);
		int n = list.size();
		try {
			long last_order_id = get_last_order_id();
			long new_order_id = last_order_id+1;

			if(last_order_id==-1) new_order_id=1;
			System.out.println("last_order_id "+last_order_id);
//			item = DAO_Item.Get_Item(item_id);
			int seller_id=DAO_Item_Seller.get_seller_id(item_id);
			
			
			String query = "insert into order_details(order_id,item_id,buyer_id,"
					+ "shipping_address,status,order_date,total_amount,payment_type,"
					+ "quantity,seller_id,cashback)"+" VALUES "+"(?,?,?,?,?,?,?,?,?,?,?)";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setLong(1, new_order_id);						
			preparedStatement.setInt(2, item_id);
			preparedStatement.setInt(3, buyer_id);
			preparedStatement.setString(4, shipping_address);
			preparedStatement.setString(5, status);
			preparedStatement.setString(6, order_date);
			preparedStatement.setInt(7, amount);
			preparedStatement.setInt(8, payment_type);
			preparedStatement.setInt(9, quantity);
			preparedStatement.setInt(10, seller_id);
			preparedStatement.setFloat(11,cashback);
			
			int rs = preparedStatement.executeUpdate();
			return true;

		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	
	
	
	public static ArrayList<Order_Details> get_Seller_Buyer_Orders(int order_id) {
		ArrayList<Order_Details> list = new ArrayList<Order_Details>();
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;		
		
		try {
			String query = "select * from order_details where order_id=?;";
			
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, order_id);
			Order_Details order_details_object = new Order_Details();
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
					order_details_object.setItem_id(rs.getInt("item_id"));
					order_details_object.setSeller_id(rs.getInt("seller_id"));
					order_details_object.setBuyer_id(rs.getInt("buyer_id"));
					order_details_object.setOrder_id(rs.getInt("order_id"));
					order_details_object.setId(rs.getInt("id"));
					order_details_object.setShipping_address(rs.getString("shipping_address"));
					order_details_object.setOrder_date(rs.getString("order_date"));
					order_details_object.setTotal_amount(rs.getInt("total_amount"));
					order_details_object.setPayment_type(rs.getInt("payment_type"));
					order_details_object.setQuantity(rs.getInt("quantity"));
					
					order_details_object.setStatus(rs.getString("status"));
					
					list.add(order_details_object);
					order_details_object = new Order_Details();

		}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
		
	}
	
	
	
	
	public static int get_deal(int item_id, int seller_id) {
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			int item_seller_id = DAO_Item_Seller.GetItemSellerId(seller_id, item_id);
			String query = "select deal_id from deals_item_seller where item_seller_id = " + "'" + item_seller_id + "'";
			preparedStatement = conn.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			return rs.getInt("deal_id");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
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
	public static ArrayList<Order_Details> get_Seller_Orders(int seller_id) {
		ArrayList<Order_Details> list = new ArrayList<Order_Details>();
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;		
		
		try {
			String query = "select * from order_details where seller_id=?;";
			
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, seller_id);
			Order_Details order_details_object = new Order_Details();
			ResultSet rs = preparedStatement.executeQuery();
			for(int i=0;i<5;i++) {
				if(rs.next()) {
					order_details_object.setItem_id(rs.getInt("item_id"));
					order_details_object.setSeller_id(rs.getInt("seller_id"));
					order_details_object.setBuyer_id(rs.getInt("buyer_id"));
					order_details_object.setOrder_id(rs.getInt("order_id"));
					order_details_object.setId(rs.getInt("id"));
					order_details_object.setShipping_address(rs.getString("shipping_address"));
					order_details_object.setOrder_date(rs.getString("order_date"));
					order_details_object.setTotal_amount(rs.getInt("total_amount"));
					order_details_object.setPayment_type(rs.getInt("payment_type"));
					order_details_object.setQuantity(rs.getInt("quantity"));
					
					order_details_object.setStatus(rs.getString("status"));
					
					list.add(order_details_object);
					order_details_object = new Order_Details();

				}
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
		
	}
	public static ArrayList<Order_Details> get_Buyer_Orders(int buyer_id) {
		ArrayList<Order_Details> list = new ArrayList<Order_Details>();
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;		
		
		try {
			String query = "select * from order_details where buyer_id=?;";
			
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, buyer_id);
			Order_Details order_details_object = new Order_Details();
			ResultSet rs = preparedStatement.executeQuery();
			for(int i=0;i<5;i++) {
				if(rs.next()) {
					order_details_object.setItem_id(rs.getInt("item_id"));
					order_details_object.setSeller_id(rs.getInt("seller_id"));
					order_details_object.setBuyer_id(rs.getInt("buyer_id"));
					order_details_object.setOrder_id(rs.getInt("order_id"));
					order_details_object.setId(rs.getInt("id"));
					order_details_object.setShipping_address(rs.getString("shipping_address"));
					order_details_object.setOrder_date(rs.getString("order_date"));
					order_details_object.setTotal_amount(rs.getInt("total_amount"));
					order_details_object.setPayment_type(rs.getInt("payment_type"));
					order_details_object.setQuantity(rs.getInt("quantity"));
					
					order_details_object.setStatus(rs.getString("status"));
					
					list.add(order_details_object);
					order_details_object = new Order_Details();

				}
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
		
	}
	public static boolean change_status(int order_id, String new_status) {
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		String query=null;
		System.out.println("entered DAO");
		query = "update order_details set status = " + '"' + new_status + '"' + "where order_id =" + order_id;
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
	
	public static ArrayList<Order_Details> get_Status_Orders(String status_id) {
		ArrayList<Order_Details> list = new ArrayList<Order_Details>();
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		
		System.out.println("entered DAO");
		
		try {
			String query = "select * from order_details where status=?;";
			
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, status_id);

			Order_Details order_details_object = new Order_Details();
			ResultSet rs = preparedStatement.executeQuery();
			
			while(true) {
				if(rs.next()) {
					order_details_object.setItem_id(rs.getInt("item_id"));
					order_details_object.setSeller_id(rs.getInt("seller_id"));
					order_details_object.setBuyer_id(rs.getInt("buyer_id"));
					order_details_object.setOrder_id(rs.getInt("order_id"));
					order_details_object.setId(rs.getInt("id"));
					order_details_object.setShipping_address(rs.getString("shipping_address"));
					order_details_object.setOrder_date(rs.getString("order_date"));
					order_details_object.setTotal_amount(rs.getInt("total_amount"));
					order_details_object.setPayment_type(rs.getInt("payment_type"));
					order_details_object.setQuantity(rs.getInt("quantity"));
					
					
					list.add(order_details_object);
					order_details_object = new Order_Details();

				}
				else break;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public static boolean update_status(int order_id,String status) {
		 Connection conn = DatabaseConnection.getConnection();
		 PreparedStatement preparedStatement = null;
		 try {

		  String query = "update order_details set status = ? where id=?";
		  preparedStatement = conn.prepareStatement(query);
		  preparedStatement.setString(1, status);      
		  preparedStatement.setInt(2, order_id);
		  System.out.println(preparedStatement);
		  
		  int rs = preparedStatement.executeUpdate();
		  if(rs==0) {
		   System.out.println("1");
		   return false;
		  }
		  return true;

		 }catch (SQLException e) {
		  System.out.println("3");
		  e.printStackTrace();
		 }
		 System.out.println("2");
		 return false;
		}
	public static boolean update_status_order_item(int order_id,int item_id,String status) {
		 Connection conn = DatabaseConnection.getConnection();
		 PreparedStatement preparedStatement = null;
		 try {

		  String query = "update order_details set status = ? where id=? and item_id=?";
		  preparedStatement = conn.prepareStatement(query);
		  preparedStatement.setString(1, status);      
		  preparedStatement.setInt(2, order_id);
		  preparedStatement.setInt(3, item_id);
		  System.out.println(preparedStatement);
		  
		  int rs = preparedStatement.executeUpdate();
		  if(rs==0) {
		   System.out.println("1");
		   return false;
		  }
		  return true;

		 }catch (SQLException e) {
		  System.out.println("3");
		  e.printStackTrace();
		 }
		 System.out.println("2");
		 return false;
		}
	public static boolean UpdateCashback(int item_id,int order_id,float cashback)
	{
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String query = "update order_details set cashback=? where item_id=? and order_id=?";
			  preparedStatement = conn.prepareStatement(query);
			  preparedStatement.setFloat(1, cashback);
			  preparedStatement.setInt(2, item_id);
			  preparedStatement.setInt(3, order_id);
			  System.out.println(preparedStatement);
			  
			  int rs = preparedStatement.executeUpdate();
			  if(rs==0) {
			   System.out.println("1");
			   return false;
			  }
			  return true;

			 }catch (SQLException e) {
			  System.out.println("3");
			  e.printStackTrace();
			 }
			 System.out.println("2");
			 return false;	
	}
	public static ArrayList<Order_Details> getOrder(int order_id) {
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		ArrayList<Order_Details> order_details = new ArrayList<Order_Details>();

		try {
			String query = "select * from order_details where order_id=?;";
			
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, order_id);

			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				Order_Details order_details_object = new Order_Details();
				order_details_object.setItem_id(rs.getInt("item_id"));
				order_details_object.setSeller_id(rs.getInt("seller_id"));
				order_details_object.setBuyer_id(rs.getInt("buyer_id"));
				order_details_object.setOrder_id(rs.getInt("order_id"));
				order_details_object.setId(rs.getInt("id"));
				order_details_object.setShipping_address(rs.getString("shipping_address"));
				order_details_object.setOrder_date(rs.getString("order_date"));
				order_details_object.setTotal_amount(rs.getInt("total_amount"));
				order_details_object.setPayment_type(rs.getInt("payment_type"));
				order_details_object.setQuantity(rs.getInt("quantity"));
				
				order_details.add(order_details_object);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return order_details;
	}
	public static int get_quantity(int order_id,int item_id) {
//		Seller sell = new Seller();
		//Connection conn = null;
		ResultSet rs;
		int quan=-1;
		try {
			Connection conn = DatabaseConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = null;			
			String query = "select quantity from order_details where item_id=? and id=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, item_id);
			preparedStatement.setInt(2, order_id);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				quan=rs.getInt("quantity");
				
			}
			else {
				//item.put("result", "fail");
				return -1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return item.toString();
		return quan;
	}
	
	public static Order_Details getOrderWithItemId(int order_id, int item_id) {
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		Order_Details order_details_object = new Order_Details();

		try {
			String query = "select * from order_details where id=? and item_id=?";
			System.out.println(query);
			
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, order_id);
			preparedStatement.setInt(2, item_id);

			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
			
				order_details_object.setItem_id(rs.getInt("item_id"));
				System.out.println("IN ORDbrcjc");
				System.out.println(rs.getInt("item_id"));
				order_details_object.setSeller_id(rs.getInt("seller_id"));
				order_details_object.setBuyer_id(rs.getInt("buyer_id"));
				order_details_object.setOrder_id(rs.getInt("order_id"));
				order_details_object.setId(rs.getInt("id"));
				order_details_object.setShipping_address(rs.getString("shipping_address"));
				order_details_object.setOrder_date(rs.getString("order_date"));
				order_details_object.setTotal_amount(rs.getInt("total_amount"));
				order_details_object.setPayment_type(rs.getInt("payment_type"));
				order_details_object.setQuantity(rs.getInt("quantity"));
				return order_details_object;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return order_details_object;
	}
	
	public static int get_total_stars(int item_id) {
		ResultSet rs;
		try {
			Connection conn = DatabaseConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = null;			
			String query = "select SUM(rating) from order_details where item_id=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, item_id);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				//seller_id=rs.getInt("seller_id");
				return rs.getInt("SUM(rating)");
				
			}
			else {
				//item.put("result", "fail");
				return -1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return item.toString();
		return 0;
	}
	public static String get_BuyerId(int order_id,int item_id) throws JSONException
	{
		ResultSet rs;
		JSONObject json = new JSONObject();
		try {
			Connection conn = DatabaseConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = null;
			
			String query = "select buyer_id,cashback,quantity from order_details where item_id=? and id=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, item_id);
			preparedStatement.setInt(2,order_id);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				 json.put("buyer_id",rs.getInt("buyer_id"));
				 json.put("cashback",rs.getString("cashback"));
				 json.put("quantity", rs.getInt("quantity"));
				 json.put("result", "success");
			}
			else {
				json.put("result", "fail");
			
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		//return item.toString();
		return json.toString();
	}
	
	//shabaz
	public static int get_total_users_rated(int item_id) {
		ResultSet rs;
		try {
			Connection conn = DatabaseConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = null;			
			String query = "select COUNT(rating) from order_details where item_id=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, item_id);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				//seller_id=rs.getInt("seller_id");
				return rs.getInt("COUNT(rating)");
				
			}
			else {
				//item.put("result", "fail");
				return -1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return item.toString();
		return 0;
	}
	
	//shabaz
	public static boolean update_rating_order_item(int order_id,int item_id,int stars) {
		 Connection conn = DatabaseConnection.getConnection();
		 PreparedStatement preparedStatement = null;
		 try {

		  String query = "update order_details set rating = ? where id=? and item_id=?";
		  preparedStatement = conn.prepareStatement(query);
		  preparedStatement.setInt(1, stars);      
		  preparedStatement.setInt(2, order_id);      
		  preparedStatement.setInt(3, item_id);      

		  System.out.println(preparedStatement);
		  
		  int rs = preparedStatement.executeUpdate();
		  if(rs==0) {
		   System.out.println("not updated");
		   return false;
		  }
		  return true;

		 }catch (SQLException e) {
		  System.out.println("3");
		  e.printStackTrace();
		 }
		 System.out.println("2");
		 return false;
		}
}