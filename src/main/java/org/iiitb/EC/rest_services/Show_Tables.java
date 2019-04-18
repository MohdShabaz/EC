package org.iiitb.EC.rest_services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.iiitb.EC.dao.DAO_Item;
import org.iiitb.EC.dao.DAO_Show_Tables;
import org.iiitb.EC.model.BankAccount;
import org.iiitb.EC.model.Buyer;
import org.iiitb.EC.model.Item;
import org.iiitb.EC.model.Seller;
import org.json.JSONArray;
import org.json.JSONObject;


@Path("showTableService")


public class Show_Tables {

	
	@Path("itemsAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	 public String getAllItems() throws Exception{
		
		ArrayList<Item> items = DAO_Show_Tables.get_All_Items();
		JSONArray item_json_array = new JSONArray();
		for(Item item : items) {
			JSONObject item_json = new JSONObject();
			item_json.put("item_id", item.getItem_id());
			item_json.put("description", item.getDescription());
			item_json.put("name", item.getName());
			item_json.put("pic_location", item.getPic_location());
			item_json.put("category", item.getCategory());
			item_json.put("sub_category", item.getSub_category());
			item_json.put("barcode", item.getBarcode());
			item_json.put("price", item.getPrice());
			item_json.put("discount", item.getDiscount());
			item_json_array.put(item_json);
		}
		JSONObject result = new JSONObject();
		result.put("items", item_json_array);
		return item_json_array.toString();
	}
	
	@Path("buyersAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	 public String getAllBuyers() throws Exception{
		System.out.println("in java rest function");
		ArrayList<Buyer> buyers = DAO_Show_Tables.get_All_Buyers();
		JSONArray buyer_json_array = new JSONArray();
		for(Buyer buyer : buyers) {
			JSONObject buyer_json = new JSONObject();
			buyer_json.put("buyer_id", buyer.getBuyer_id());
			buyer_json.put("name", buyer.getName());
			buyer_json.put("mobile", buyer.getMobile());
			buyer_json.put("password", buyer.getPassword());
			buyer_json.put("email", buyer.getEmail());
			buyer_json.put("dob", buyer.getDob());
			buyer_json.put("address_1", buyer.getAddress_1());
			buyer_json.put("address_2", buyer.getAddress_2());
			buyer_json_array.put(buyer_json);
		}
		JSONObject result = new JSONObject();
		result.put("items", buyer_json_array);
		System.out.println("in java rest function result "+result);

		return buyer_json_array.toString();
	}
	
	@Path("sellerAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	 public String getAllSellers() throws Exception{
		System.out.println("in java rest function");
		ArrayList<Seller> sellers = DAO_Show_Tables.get_All_Sellers();
		JSONArray seller_json_array = new JSONArray();
		for(Seller seller : sellers) {
			JSONObject seller_json = new JSONObject();
			seller_json.put("seller_id", seller.getSeller_id());
			seller_json.put("name", seller.getName());
			seller_json.put("mobile", seller.getMobile());
			seller_json.put("password", seller.getPassword());
			seller_json.put("email", seller.getEmail());
			seller_json.put("address_1", seller.getAddress_1());
			seller_json.put("address_2", seller.getAddress_2());
			seller_json_array.put(seller_json);
		}
		JSONObject result = new JSONObject();
		result.put("sellers", seller_json_array);
		System.out.println("in java rest function result "+result);

		return seller_json_array.toString();
	}
	
	@Path("sellerAllAccount")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	 public String getAllSellersAccounts() throws Exception{
		System.out.println("in java rest function seller account");
		ArrayList<BankAccount> sellers = DAO_Show_Tables.get_All_Sellers_Accounts();
		JSONArray seller_json_array = new JSONArray();
		for(BankAccount seller : sellers) {
			JSONObject seller_json = new JSONObject();
			seller_json.put("holderID", seller.getHolderID());
			seller_json.put("accNumber", seller.getAccountNumber());
			seller_json.put("currBalance", seller.getCurrentBalance());
			seller_json_array.put(seller_json);
		}
		JSONObject result = new JSONObject();
		result.put("sellers", seller_json_array);
		System.out.println("in java rest function  seller account result "+result);

		return seller_json_array.toString();
	}
	
	
	@Path("buyerAllAccount")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	 public String getAllBuyersAccounts() throws Exception{
		System.out.println("in java rest function");
		ArrayList<BankAccount> buyers = DAO_Show_Tables.get_All_Buyer_Accounts();
		JSONArray buyer_json_array = new JSONArray();
		for(BankAccount buyer : buyers) {
			JSONObject buyer_json = new JSONObject();
			buyer_json.put("holderID", buyer.getHolderID());
			buyer_json.put("accNumber", buyer.getAccountNumber());
			buyer_json.put("currBalance", buyer.getCurrentBalance());
			buyer_json_array.put(buyer_json);
		}
		JSONObject result = new JSONObject();
		result.put("buyers", buyer_json_array);
		System.out.println("in java rest function result "+result);

		return buyer_json_array.toString();
	}
	
	
}
