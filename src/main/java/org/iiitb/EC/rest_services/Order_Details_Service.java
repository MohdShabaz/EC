package org.iiitb.EC.rest_services;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.iiitb.EC.dao.DAO_Buyer;
import org.iiitb.EC.dao.DAO_Item;
import org.iiitb.EC.dao.DAO_Order_Details;
import org.iiitb.EC.dao.DAO_Seller;
import org.iiitb.EC.model.Item;
import org.iiitb.EC.model.Order_Details;
import org.iiitb.EC.model.Seller;
import org.json.JSONArray;
import org.json.JSONObject;

@Path("orderService")
public class Order_Details_Service {
	private static final String SUCCESS_RESULT="Success";
	private static final String FAILURE_RESULT="Failure";
	
	
	
	public int get_buyer_id(HttpHeaders httpheaders) {
		String username = httpheaders.getHeaderString("username");
		return DAO_Buyer.get_buyer_id(username);
	}
	
	static int get_seller_id(HttpHeaders httpheaders) {
		String seller_id= httpheaders.getHeaderString("username");
		return DAO_Seller.get_seller_id(seller_id);
	}
	
	@Path("addOrder")
	@POST
	//@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	//@PUT
	//@Produces(MediaType.TEXT_PLAIN)
	public String addOrder(String order,
			@Context HttpHeaders httpheaders) throws Exception {
	    JSONObject order_json = new JSONObject(order);
	    
	    int buyer_id=get_buyer_id(httpheaders);
	    
	    boolean result = DAO_Order_Details.Add_Order(buyer_id, 
	    		order_json.getString("shipping_address"),
	    		"In Transit",
	    		"2019-02-23",
	    		order_json.getInt("payment_type"));

	    return result ? SUCCESS_RESULT : FAILURE_RESULT;
	    
	 }


@Path("addOrder")
@POST
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.TEXT_PLAIN)
//@PUT
//@Produces(MediaType.TEXT_PLAIN)
public String addOrder(
		@FormDataParam("shipping_address") String shipping_address,
		@FormDataParam("status") String status,
		@FormDataParam("order_date") String order_date,
		@FormDataParam("payment_type") String payment_type,
		@Context HttpHeaders httpheaders) throws Exception {
    
	int buyer_id = get_buyer_id(httpheaders);
    
    boolean result = DAO_Order_Details.Add_Order(buyer_id,
    		shipping_address,
    		status,
    		order_date,
    		Integer.parseInt(payment_type));

    return result ? SUCCESS_RESULT : FAILURE_RESULT;
    
 }
@Path("sellerOrderDetails")
@GET
@Produces(MediaType.APPLICATION_JSON)
 public static String getSellerInfo(@Context HttpHeaders httpheaders ) throws Exception{
    
	
	int seller_id = get_seller_id(httpheaders);
	
	
	
	ArrayList<Order_Details> items = DAO_Order_Details.get_Seller_Orders(seller_id);
	
	JSONArray order_details_json_array = new JSONArray();
	for(Order_Details order_details : items) {
		Item item=DAO_Item.Get_Item(order_details.getItem_id());
		
		JSONObject order_details_json = new JSONObject();
		order_details_json.put("item_id", order_details.getItem_id());
		order_details_json.put("pic_location", item.getPic_location());
		order_details_json.put("name", item.getName());
		order_details_json.put("seller_id", order_details.getSeller_id());
		order_details_json.put("buyer_id", order_details.getBuyer_id());
		order_details_json.put("id", order_details.getId());
		order_details_json.put("shipping_address", order_details.getShipping_address());
		order_details_json.put("status", order_details.getStatus());
		order_details_json.put("order_date", order_details.getOrder_date());
		order_details_json.put("total_amount", order_details.getTotal_amount());
		order_details_json.put("payment_type", order_details.getPayment_type());
		order_details_json.put("quantity", order_details.getQuantity());
		order_details_json_array.put(order_details_json);
	}
	return order_details_json_array.toString();
            
 }


	
}
