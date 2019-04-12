package org.iiitb.EC.rest_services;

import java.sql.Connection;

import org.iiitb.EC.dao.DAO_Buyer;
import org.iiitb.EC.dao.DAO_Seller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.iiitb.EC.dbcon.DatabaseConnection;
import org.iiitb.EC.model.Buyer;
import org.iiitb.EC.model.Seller;

import org.json.JSONObject;


@Path("sellerService")

public class Seller_Service {
	
	static int get_seller_id(HttpHeaders httpheaders) {
		String seller_id= httpheaders.getHeaderString("username");
		return DAO_Seller.get_seller_id(seller_id);
	}
	
	private static final String SUCCESS_RESULT="Success";
	private static final String FAILURE_RESULT="Failure";	

	@Path("sellerInfo")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
	 public static String getSellerInfo(@Context HttpHeaders httpheaders ) throws Exception{
        
		System.out.println("entered REST");
		
		int seller_id = get_seller_id(httpheaders);
		
		
		
        Seller seller=DAO_Seller.get_seller_details(seller_id);     
        JSONObject sell = new JSONObject();
        sell.put("name", seller.getName());
        sell.put("email", seller.getEmail());
        sell.put("password", seller.getPassword());
        sell.put("mobile", seller.getMobile());
        //buy.put("dob", seller.getDob());
        sell.put("address_1", seller.getAddress_1());
        sell.put("address_2", seller.getAddress_2());
        return sell.toString();
                
     }
	
	
	@Path("addSeller")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String addSeller(String input) throws Exception{
		JSONObject input_json = new JSONObject(input);
		boolean result = DAO_Seller.add_Seller(input_json.getString("Name"),  input_json.getString("Mobile"),input_json.getString("Email"),input_json.getString("Password"),input_json.getString("Address1"),input_json.getString("Address2"));
		System.out.println("Seller Service "+ result);
		return result ? "{ \"Response\" : \"" + SUCCESS_RESULT + "\" }" : "{ \"Response\" : \"" + FAILURE_RESULT + "\" }";
	}
	
	

	@Path("updateSeller")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public String UpdateSellerInfo(String seller) throws Exception {
        JSONObject seller_json = new JSONObject(seller);
        boolean result = DAO_Seller.update_Seller(seller_json.getInt("seller_id"),
        seller_json.getString("name"),
        seller_json.getString("email"),
        seller_json.getString("password"),
        seller_json.getString("mobile"),
        seller_json.getString("address_1"),
        seller_json.getString("address_2"));
        return result ? SUCCESS_RESULT : FAILURE_RESULT;
        
     }
	
	@Path("updateSeller")
    @PUT
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String UpdateSellerInfo(
    		@FormDataParam("seller_name") String name,
    		@FormDataParam("mobile") String mobile,
			@FormDataParam("email") String email,
			@FormDataParam("password") String password,
			@FormDataParam("address_1") String address_1,
			@FormDataParam("address_2") String address_2,
			@Context HttpHeaders httpheaders) throws Exception {
		
		int seller_id = get_seller_id(httpheaders);
        boolean result = DAO_Seller.update_Seller(seller_id,
        name,
        email,
        password,
        mobile,
        address_1,
        address_2);
        return result ? SUCCESS_RESULT : FAILURE_RESULT;
        
     }
	
	
}