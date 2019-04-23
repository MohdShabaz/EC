package org.iiitb.EC.dao;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;

import org.iiitb.EC.dbcon.DatabaseConnection;
import org.iiitb.EC.model.Item;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DAO_Item {
	//String query = null;
	//ResultSet rs;
	public static boolean Add_Item(String description , float price, float discount, String name, String pic_location,
			int category, int sub_category, String barcode, String dummy_1, String dummy_2, String dummy_3, String dummy_4) {
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String query = "insert into item_table(description,price,discount,name,pic_location,category,sub_category,barcode)"+" VALUES "+
			"(?,?,?,?,?,?,?,?)";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, description);
			preparedStatement.setFloat(2, price);
			preparedStatement.setFloat(3, discount);
			preparedStatement.setString(4, name);
			preparedStatement.setString(5, pic_location);
			preparedStatement.setInt(6, category);
			preparedStatement.setInt(7, sub_category);
			preparedStatement.setString(8, barcode);
			
			int rs = preparedStatement.executeUpdate();
			if(rs==0) {
				return false;
			}
			else {
				return true;
			}

		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static int get_item_id(String barcode) {
		Item item = new Item();
		//Connection conn = null;
		ResultSet rs;
		try {
			Connection conn = DatabaseConnection.getConnection();
			java.sql.PreparedStatement preparedStatement = null;			
			String query = "select item_id from item_table where barcode=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, barcode);
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				int item_id = rs.getInt("item_id");
				return item_id;
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
	
	public static int Check_Item(String BarCode) {
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String query = "SELECT item_id FROM item_table where barcode=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, BarCode);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				return rs.getInt("item_id");
			}
			else {
				return -1;
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	public static JSONArray get_Seller_All_Items_Details(int seller_id) throws JSONException {
		 Connection conn=DatabaseConnection.getConnection();
		 PreparedStatement preparedStatement = null;  
		 System.out.println("In get_Seller_Items_Details");
		 JSONArray item_json_array = null;
		 try {
		  String query = "select item_table.item_id, item_table.name, item_table.discount, "
		    + "item_table.price, item_table.barcode, item_table.pic_location, "
		    + "item_table.description, category.category_name, "
		    + "sub_category.sub_category_name, item_seller.quantity from item_seller "
		    + "inner join seller_table on item_seller.seller_id = seller_table.seller_id "
		    + "and seller_table.seller_id = ? inner join item_table on"
		    + " item_seller.item_id = item_table.item_id inner join category on "
		    + "category.id = item_table.category inner join sub_category on "
		    + "sub_category.id = item_table.sub_category;";
		  
		  preparedStatement = conn.prepareStatement(query);
		  preparedStatement.setInt(1, seller_id);
		  Item item_object = new Item();
		  ResultSet rs = preparedStatement.executeQuery();
		  item_json_array = new JSONArray();
		//   for(int i=0;i<5;i++) {
		  while(rs.next()) {
//		    if(rs.next()) {
		    System.out.println("In get_Seller_Items_Details, in loop");
		    JSONObject item_json = new JSONObject();
		    item_json.put("item_id", rs.getInt("item_id"));
		    item_json.put("description", rs.getString("description"));
		    item_json.put("name", rs.getString("name"));
		    item_json.put("pic_location", rs.getString("pic_location"));
		    item_json.put("category", rs.getString("category_name"));
		    item_json.put("sub_category", rs.getString("sub_category_name"));
		    item_json.put("barcode", rs.getString("barcode"));
		    item_json.put("price", rs.getFloat("price"));
		    item_json.put("discount", rs.getFloat("discount"));
		    item_json.put("quantity", rs.getInt("quantity"));
		    item_json.put("seller_id", seller_id);
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
	
	public static JSONArray get_All_Items_Details() throws JSONException {
		 Connection conn=DatabaseConnection.getConnection();
		 PreparedStatement preparedStatement = null;  
		 System.out.println("In get_Seller_Items_Details");
		 JSONArray item_json_array = null;
		 try {
		  String query = "select  seller_table.name as seller_name, item_table.item_id, item_table.name, item_table.discount, "
		    + "item_table.price, item_table.barcode, item_table.pic_location, "
		    + "item_table.description, category.category_name, "
		    + "sub_category.sub_category_name, item_seller.quantity from item_seller "
		    + "inner join seller_table on item_seller.seller_id = seller_table.seller_id "
		    + " inner join item_table on"
		    + " item_seller.item_id = item_table.item_id inner join category on "
		    + "category.id = item_table.category inner join sub_category on "
		    + "sub_category.id = item_table.sub_category;";
		  
		  preparedStatement = conn.prepareStatement(query);
		  Item item_object = new Item();
		  ResultSet rs = preparedStatement.executeQuery();
		  item_json_array = new JSONArray();
		//   for(int i=0;i<5;i++) {
		  while(rs.next()) {
//		    if(rs.next()) {
		    System.out.println("In get_Seller_Items_Details, in loop");
		    JSONObject item_json = new JSONObject();
		    item_json.put("item_id", rs.getInt("item_id"));
		    item_json.put("seller_name", rs.getString("seller_name"));
		    item_json.put("description", rs.getString("description"));
		    item_json.put("name", rs.getString("name"));
		    item_json.put("pic_location", rs.getString("pic_location"));
		    item_json.put("category", rs.getString("category_name"));
		    item_json.put("sub_category", rs.getString("sub_category_name"));
		    item_json.put("barcode", rs.getString("barcode"));
		    item_json.put("price", rs.getFloat("price"));
		    item_json.put("discount", rs.getFloat("discount"));
		    item_json.put("quantity", rs.getInt("quantity"));
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
	

	public static JSONArray get_All_Brands() throws JSONException {
		 Connection conn=DatabaseConnection.getConnection();
		 PreparedStatement preparedStatement = null;  
		 System.out.println("Getting Brands");
		 JSONArray json_array = null;
		 try {
		  String query = "select distinct(dummy_1) from item_table";
		  //String query = "select distinct(status) from order_details";
		  preparedStatement = conn.prepareStatement(query);
		  ResultSet rs = preparedStatement.executeQuery();
		  json_array = new JSONArray();
		//   for(int i=0;i<5;i++) {
		  while(rs.next()) {
//		    if(rs.next()) {
			String brand = rs.getString("dummy_1");
			if(brand == null) {
				continue;
			}
		    System.out.println("In Brands, in loop");
		    JSONObject json = new JSONObject();
		    json.put("brand", brand);
		    System.out.println(json);
		    json_array.put(json);
		    
//		    }
		  }
		  return json_array;
		  
		 }catch (SQLException e) {
		  e.printStackTrace();
		 }
		 
		 return json_array;
		}
	
	public static JSONObject get_given_Items_Details(int item_id) throws JSONException {
		 Connection conn=DatabaseConnection.getConnection();
		 PreparedStatement preparedStatement = null;  
		 JSONObject item_json = new JSONObject();
		 System.out.println("In get_Seller_Items_Details");
		 JSONArray item_json_array = null;
		 try {
		  String query = "select  seller_table.name as seller_name, item_table.item_id, item_table.name, item_table.discount, "
		    + "item_table.price, item_table.barcode, item_table.pic_location, "
		    + "item_table.description, category.category_name, "
		    + "sub_category.sub_category_name, item_seller.quantity from item_seller "
		    + "inner join seller_table on item_seller.seller_id = seller_table.seller_id "
		    + " inner join item_table on"
		    + " item_seller.item_id = item_table.item_id inner join category on "
		    + "category.id = item_table.category inner join sub_category on "
		    + "sub_category.id = item_table.sub_category and item_table.item_id=?;";
		  
		  preparedStatement = conn.prepareStatement(query);
		  Item item_object = new Item();
		  preparedStatement.setInt(1,item_id);
		  ResultSet rs = preparedStatement.executeQuery();
		  item_json_array = new JSONArray();
//		  JSONObject item_json = new JSONObject();
		//   for(int i=0;i<5;i++) {
		  if(rs.next()) {
//		    if(rs.next()) {
		    System.out.println("In get_Seller_Items_Details, in loop");
//		    JSONObject item_json = new JSONObject();
		    item_json.put("item_id", rs.getInt("item_id"));
		    item_json.put("seller_name", rs.getString("seller_name"));
		    item_json.put("description", rs.getString("description"));
		    item_json.put("name", rs.getString("name"));
		    item_json.put("pic_location", rs.getString("pic_location"));
		    item_json.put("category", rs.getString("category_name"));
		    item_json.put("sub_category", rs.getString("sub_category_name"));
		    item_json.put("barcode", rs.getString("barcode"));
		    item_json.put("price", rs.getFloat("price"));
		    item_json.put("discount", rs.getFloat("discount"));
		    item_json.put("quantity", rs.getInt("quantity"));
		    System.out.println(item_json);
		    item_json_array.put(item_json);
		    

//		    }
		  }
		  return item_json;
		  
		 }catch (SQLException e) {
		  e.printStackTrace();
		 }
		 
		 return item_json;
		}
	
	
	
	public static int get_quantity(int item_id,int seller_id) {
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String query = "SELECT quantity FROM item_seller where item_id=? and seller_id=?";
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, item_id);
			preparedStatement.setInt(2, seller_id);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				return rs.getInt("quantity");
			}
			else {
				return -1;
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public static boolean Delete_Item(int item_id)
	{
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		String query = "Delete from item_table where item_id=?";
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, item_id);
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
	public static Item Get_Item(int Item_id) {
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String query = "SELECT * FROM item_table where item_id = ?";
			Item item_object = new Item();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, Item_id);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				item_object.setDescription(rs.getString("description"));
				item_object.setName(rs.getString("name"));
				item_object.setPic_location(rs.getString("pic_location"));
				item_object.setCategory(rs.getInt("category"));
				item_object.setSub_category(rs.getInt("sub_category"));
				item_object.setBarcode(rs.getString("barcode"));
				item_object.setPrice(rs.getFloat("price"));
				item_object.setDiscount(rs.getFloat("discount"));
				return item_object;
			}
			else {
				return null;
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static ArrayList<Item> get_Top5_Items() {
		ArrayList<Item> list = new ArrayList<Item>();
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;		
		
		try {
			String query = "select * from item_table;";
			
			preparedStatement = conn.prepareStatement(query);
			Item item_object = new Item();
			ResultSet rs = preparedStatement.executeQuery();
//			for(int i=0;i<5;i++) {
			while(rs.next()) {
//				if(rs.next()) {
					item_object.setItem_id(rs.getInt("item_id"));
					item_object.setDescription(rs.getString("description"));
					item_object.setName(rs.getString("name"));
					item_object.setPic_location(rs.getString("pic_location"));
					item_object.setCategory(rs.getInt("category"));
					item_object.setSub_category(rs.getInt("sub_category"));
					item_object.setBarcode(rs.getString("barcode"));
					item_object.setPrice(rs.getFloat("price"));
					item_object.setDiscount(rs.getFloat("discount"));
					
					list.add(item_object);
					item_object = new Item();

//				}
			}
			System.out.println("IM TOP 5 Items dskjc bjkdcnxs");
			System.out.println(list.size());
			for(int i=0;i<list.size();i++)
			{
				System.out.println(list.get(i).getItem_id());
			}
			System.out.println(list);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	

	public static ArrayList<Item> get_Top5_Discounted() {
		ArrayList<Item> list = new ArrayList<Item>();
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;		
		
		try {
			String query = "select * from item_table ORDER BY discount DESC LIMIT 5";
			
			preparedStatement = conn.prepareStatement(query);
			Item item_object = new Item();
			ResultSet rs = preparedStatement.executeQuery();
			for(int i=0;i<5;i++) {
				if(rs.next()) {
					item_object.setItem_id(rs.getInt("item_id"));
					item_object.setDescription(rs.getString("description"));
					item_object.setName(rs.getString("name"));
					item_object.setPic_location(rs.getString("pic_location"));
					item_object.setCategory(rs.getInt("category"));
					item_object.setSub_category(rs.getInt("sub_category"));
					item_object.setBarcode(rs.getString("barcode"));
					item_object.setPrice(rs.getFloat("price"));
					item_object.setDiscount(rs.getFloat("discount"));
					
					list.add(item_object);
					item_object = new Item();

				}
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	public static void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation)
	 {
	  try
	  {
	   OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
	   int read = 0;
	   byte[] bytes = new byte[1024];

	   out = new FileOutputStream(new File(uploadedFileLocation));
	   while ((read = uploadedInputStream.read(bytes)) != -1)
	   {
	    out.write(bytes, 0, read);
	   }
	   out.flush();
	   out.close();
	  }catch (IOException e)
	  {

	   e.printStackTrace();
	  }

	     /*
	      * Code to store image in path
	      */
	     //return null;
	    }
	
	
	
	//SELECT * FROM item_table ORDER BY discount DESC;
}