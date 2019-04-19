package org.iiitb.EC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.iiitb.EC.dbcon.DatabaseConnection;
import org.iiitb.EC.model.BirthdayDeal;
import org.iiitb.EC.model.Item;
import org.iiitb.EC.model.Item_Seller;

public class DAO_Deals {
	 public static ArrayList<BirthdayDeal>  get_all_bday_deals()
	 {
		 ArrayList<BirthdayDeal> is = new ArrayList<BirthdayDeal>();
			
			BirthdayDeal bday_deal = new BirthdayDeal();

			ResultSet rs;
			try {
				Connection conn = DatabaseConnection.getConnection();
				java.sql.PreparedStatement preparedStatement = null;			
				String query = "select item_table.item_id, item_table.name, item_table.price, item_table.discount, item_table.pic_location, deals.discount As 'birthdayDiscount' from deals, deals_item_seller, item_table, item_seller where deals.deal_id=2 and deals_item_seller.deal_id=deals.deal_id \n" + 
						"and deals_item_seller.item_seller_id=item_seller.id and item_seller.item_id=item_table.item_id";
				preparedStatement = conn.prepareStatement(query);
				rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					
					bday_deal.setName(rs.getString("name"));
					bday_deal.setPrice(rs.getFloat("price"));
					bday_deal.setDiscount(rs.getFloat("discount"));
					bday_deal.setBdayDiscount(rs.getFloat("birthdayDiscount"));
					bday_deal.setPic_location(rs.getString("pic_location"));
					bday_deal.setID(rs.getInt("item_id"));
					is.add(bday_deal);
					bday_deal = new BirthdayDeal();
				}
				return is;

			} catch (SQLException e) {
				e.printStackTrace();
			}
			//return item.toString();
			//return sell;
			
			
			return null;
	 }
	 
	 public static ArrayList<String>  getAllDealTypes() {
		 ArrayList<String> dealTypes = new ArrayList<String>();
		 ResultSet rs;
			try {
				Connection conn = DatabaseConnection.getConnection();
				java.sql.PreparedStatement preparedStatement = null;			
				String query = "select deal_name from deals";
				preparedStatement = conn.prepareStatement(query);
				rs = preparedStatement.executeQuery();
				
				while(rs.next()) {
					
					dealTypes.add(rs.getString("deal_name"));
				}
				return dealTypes;

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return dealTypes;
	 }
	 
	 public static boolean SetItemDeals(int item_seller_id, int deal_id) {
		 Connection conn=DatabaseConnection.getConnection();
			PreparedStatement preparedStatement = null;
			String query=null;
			System.out.println("entered DAO");
			query = "select * from deals_item_seller where item_seller_id=" + '"' + item_seller_id + '"';
			try {
				preparedStatement = conn.prepareStatement(query);
				ResultSet rs = preparedStatement.executeQuery();
				if(!rs.next()) {
					query = "insert into deals_item_seller(item_seller_id, deal_id) VALUES (" + item_seller_id + ", " + deal_id + ")";
					
				} else {
					query = "update deals_item_seller set deal_id=" + '"' + deal_id + '"' + " where item_seller_id=" + '"' + item_seller_id + '"';
					
				}
				int res;
				preparedStatement = conn.prepareStatement(query);
				res = preparedStatement.executeUpdate();
				
				if (res == 0) {
					return false;
				}
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;		
			}
	 }

}
