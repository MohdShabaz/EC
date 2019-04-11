package org.iiitb.EC.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.iiitb.EC.dbcon.DatabaseConnection;
import org.iiitb.EC.model.label_table;
import org.iiitb.EC.model.label_table;
/*Sankeerth*/

public class DAO_Label_Table {
	public static boolean add_Item_Label(String barcode,String key,String attr)
	{
		System.out.println("IN_DAO_LABEL_TABLE_ADD_LABEL_ITEM");
		int item_id=DAO_Item.get_item_id(barcode);
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;
		String query=null;
		query = "insert into label_table(item_id,label,attr) VALUES " +
				 "(?,?,?);";
		
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, item_id);
			preparedStatement.setString(2, key);
			preparedStatement.setString(3, attr);
			System.out.println(preparedStatement);
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
	public static ArrayList<label_table> get_all_label_value(int item_id){
		ArrayList<label_table> list = new ArrayList<label_table>();
		Connection conn=DatabaseConnection.getConnection();
		PreparedStatement preparedStatement = null;		
		
		try {
			String query = "select * from label_table where item_id=?";
			
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, item_id);
			System.out.println("prep"+preparedStatement);
			label_table label_table_object = new label_table();
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
					label_table_object.setItem_id(rs.getInt("item_id"));;
					label_table_object.setLabel(rs.getString("label"));
					label_table_object.setAttr(rs.getString("attr"));
					label_table_object.setId(rs.getInt("id"));
					System.out.println("edffefewef"+rs.getString("label"));
					list.add(label_table_object);
					label_table_object = new label_table();
					
			}
			System.out.println("edffefewef"+list);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;		
	}

}
