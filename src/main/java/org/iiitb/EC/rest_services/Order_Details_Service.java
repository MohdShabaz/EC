package org.iiitb.EC.rest_services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.iiitb.EC.dao.DAO_Buyer;
import org.iiitb.EC.dao.DAO_Order_Details;
import org.iiitb.EC.dao.DAO_Seller;
import org.json.JSONObject;

@Path("orderService")
public class Order_Details_Service {
	private static final String SUCCESS_RESULT="Success";
	private static final String FAILURE_RESULT="Failure";
	
	
	
	public int get_buyer_id(HttpHeaders httpheaders) {
		String username = httpheaders.getHeaderString("username");
		return DAO_Buyer.get_buyer_id(username);
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


	
}
