package org.iiitb.EC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.iiitb.EC.dbcon.DatabaseConnection;
import org.iiitb.EC.model.Buyer;
import org.iiitb.EC.model.Item;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DAO_ClearanceSale {
//Function to check if the Deal is still valid based on dates.
public static boolean check_deal_date() {
	 String pattern = "yyyy-MM-dd";
	 SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	 
	 String date = sdf.format(new Date());
	 System.out.println(date);
	 Connection conn=DatabaseConnection.getConnection();
	  PreparedStatement preparedStatement = null;
	  try {
	   String query = "select start_date,end_date from deals where deal_id=3";
	   preparedStatement = conn.prepareStatement(query);
	    ResultSet rs = preparedStatement.executeQuery();
	    if(rs.next()) {
	    if(date.compareTo(rs.getString("start_date"))>=0 && date.compareTo(rs.getString("end_date"))<=0) {
	     return true;
	    }
	    return false;
	    }
	  }catch (SQLException e) {
	    e.printStackTrace();
	   }
	 return false;
}

public static boolean update_clearance_sale(String start_date,String end_date,String discount) {
	 try {
	  Connection conn = DatabaseConnection.getConnection();

	  PreparedStatement ps = conn.prepareStatement(
	         "UPDATE deals SET start_date = ?, end_date = ?,discount=? where deal_id=3");

	  
	  ps.setString(1, start_date);
	  ps.setString(2, end_date);
	  
	 
	  float dis = Float.valueOf(discount);
	  dis = (float)dis/(float)(100);
	  ps.setFloat(3, dis);
	 
	  int rs = ps.executeUpdate();
	  System.out.println("update status rs :"+rs);
	  if(rs==0) return false;
	  else return true;

	 } catch (SQLException e) {
	  System.out.println(e.getMessage());
	 }
	 //return item.toString();
	 return false;
	}

public static float get_clearance_sale_discount() {
	 Connection conn=DatabaseConnection.getConnection();
	 PreparedStatement preparedStatement = null;
	 try {
	   String query = "select discount from deals where deal_id=3";
	   preparedStatement = conn.prepareStatement(query);
	   ResultSet rs = preparedStatement.executeQuery();
	   if(rs.next()) {
		   float value = rs.getFloat("discount");
		   return value;
	   }
	 }catch (SQLException e) {
	   e.printStackTrace();
	 }
	 
	 return 0;
}

public static String get_All_Clearance_Sale_Items() throws JSONException  {
	  Connection conn=DatabaseConnection.getConnection();
	  PreparedStatement preparedStatement = null;  
	//   System.out.println("In get_Seller_Items_Details");
	  JSONArray item_json_array = null;
	  try {
	   String query1="select item_seller_id from deals_item_seller where deal_id=3";
	   String query2 = "select item_id from item_seller where id=?";
	   preparedStatement = conn.prepareStatement(query1);
	   ResultSet rs1 = preparedStatement.executeQuery();
	   
	   item_json_array = new JSONArray();
	 //   for(int i=0;i<5;i++) {
	   while(rs1.next()) {
	//      if(rs.next()) {
	     System.out.println("In get_All_Buyer_Details, in loop");
	     int item_seller_id = rs1.getInt("item_seller_id");
	     preparedStatement = conn.prepareStatement(query2);
	     preparedStatement.setInt(1, item_seller_id);
	    ResultSet rs2 = preparedStatement.executeQuery();
	   
	     
	   if(rs2.next()) {
	    int item_id = rs2.getInt("item_id");
	    Item item2 = DAO_Item.Get_Item(item_id);
	   
	    JSONObject item_json = new JSONObject();
	     item_json.put("description",item2.getDescription());
	     item_json.put("item_id", item_id);
	  item_json.put("name",item2.getName());
	  item_json.put("pic_location",item2.getPic_location());
	  item_json.put("category",item2.getCategory());
	  item_json.put("sub_category",item2.getSub_category());
	  item_json.put("barcode",item2.getBarcode());
	  item_json.put("price",item2.getPrice());
	  item_json.put("discount",item2.getDiscount());
	     
	     
	     System.out.println(item_json);
	     item_json_array.put(item_json);
	     
	   }
	     }
	   
	   return item_json_array.toString();
   
  }catch (SQLException e) {
   e.printStackTrace();
  }
 
  return item_json_array.toString();
 }


public static boolean item_exist_in_clearance_sale(int item_id) throws JSONException  {
	if(check_deal_date()) {  
	Connection conn=DatabaseConnection.getConnection();
	  PreparedStatement preparedStatement = null;  
	//   System.out.println("In get_Seller_Items_Details");
	  try {
	   String query1 = "select id from item_seller where item_id=?";
	   String query2="select id from deals_item_seller where deal_id=3 and item_seller_id=?";
	   preparedStatement = conn.prepareStatement(query1);
	   preparedStatement.setInt(1, item_id);
	   ResultSet rs1 = preparedStatement.executeQuery();
	   
	 //   for(int i=0;i<5;i++) {
	   while(rs1.next()) {
	//      if(rs.next()) {
	     int item_seller_id = rs1.getInt("id");
	     System.out.println("item_seller_id is ---> "+item_seller_id);
	     
	     preparedStatement = conn.prepareStatement(query2);
	     preparedStatement.setInt(1, item_seller_id);
	    ResultSet rs2 = preparedStatement.executeQuery();
	   
	     boolean ans = false;
	   if(rs2.next()) {
		   	ans = true;
		   	return ans;
	     
	   }
	     }
	   
	   return false;
 
}catch (SQLException e) {
 e.printStackTrace();
}

return false;
}
else {
	return false;
}
}


}