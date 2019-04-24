package org.iiitb.EC.rest_services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.iiitb.EC.dao.DAO_Buyer;
import org.iiitb.EC.dao.DAO_ClearanceSale;
import org.iiitb.EC.dao.DAO_Seller;
import org.iiitb.EC.model.Buyer;
import org.json.JSONArray;
import org.json.JSONObject;



@Path("clearanceSale")
public class Clearance_Sale_Service {
	private static final String SUCCESS_RESULT="Success";
	private static final String FAILURE_RESULT="Failure";	
	
	public int get_seller_id(HttpHeaders httpheaders) {
		String username = httpheaders.getHeaderString("username");
		return DAO_Seller.get_seller_id(username);
	}
	
	@Path("getSaleItems")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	 public String get_Sale_Items() throws Exception{
		String items = DAO_ClearanceSale.get_All_Clearance_Sale_Items();

		JSONArray item_json_array = new JSONArray(items);
		
		
		
		if(DAO_ClearanceSale.check_deal_date()) {
			float sale_discount = DAO_ClearanceSale.get_clearance_sale_discount();
			JSONObject item_json = new JSONObject();
			item_json.put("clearance_sale_discount", sale_discount);
			item_json_array.put(item_json);
			return item_json_array.toString();
		}
		else {
			return "";
		}
	}
	
	
	
	@Path("updateClearanceSale/{start_date}/{end_date}/{discount}")
    @POST
    
    @Produces(MediaType.TEXT_PLAIN)
    public String Update_Clearance_Sale(@PathParam("start_date") String start_date,@PathParam("end_date") String end_date,@PathParam("discount") String discount
    		) throws Exception {
		
		System.out.println("in updateClearanceSale service java");
		System.out.println("start_date "+start_date);
		System.out.println("end_date "+end_date);
		System.out.println("discount "+discount);

		boolean output = DAO_ClearanceSale.update_clearance_sale(start_date, end_date, discount);
		
        //return output ? "{ \"Response\" : \"" + SUCCESS_RESULT + "\" }" : "{ \"Response\" : \"" + FAILURE_RESULT + "\" }";
        
		if(output==true) return SUCCESS_RESULT;
		else return FAILURE_RESULT;
     }
	
}
