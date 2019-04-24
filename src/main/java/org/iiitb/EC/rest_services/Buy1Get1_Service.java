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
import org.iiitb.EC.dao.DAO_Buy1Get1;
import org.iiitb.EC.dao.DAO_Seller;
import org.iiitb.EC.model.Buyer;
import org.json.JSONArray;
import org.json.JSONObject;

@Path("buy1Get1Service")
public class Buy1Get1_Service {
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
		String items = DAO_Buy1Get1.get_All_Buy1Get1_Items();

		JSONArray item_json_array = new JSONArray(items);
		
		return item_json_array.toString();
	}
}
