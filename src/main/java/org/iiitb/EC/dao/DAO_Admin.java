package org.iiitb.EC.dao;
import org.iiitb.EC.model.Buyer;

import java.sql.*;

import org.iiitb.EC.dbcon.DatabaseConnection;


public class DAO_Admin {
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
}
