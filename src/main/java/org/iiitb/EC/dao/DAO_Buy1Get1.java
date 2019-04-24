package org.iiitb.EC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.iiitb.EC.dbcon.DatabaseConnection;
import org.iiitb.EC.model.Item;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DAO_Buy1Get1 {
	//Function to check if the Deal is still valid based on dates.

	public static String get_All_Buy1Get1_Items() throws JSONException  {
		  Connection conn=DatabaseConnection.getConnection();
		  PreparedStatement preparedStatement = null;  
		//   System.out.println("In get_Seller_Items_Details");
		  JSONArray item_json_array = null;
		  try {
		   String query1="select item_seller_id from deals_item_seller where deal_id=1";
		   String query2 = "select item_id from item_seller where id=?";
		   preparedStatement = conn.prepareStatement(query1);
		   ResultSet rs1 = preparedStatement.executeQuery();
		   
		   item_json_array = new JSONArray();
		 //   for(int i=0;i<5;i++) {
		   while(rs1.next()) {
		//      if(rs.next()) {
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

}
