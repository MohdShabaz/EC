package org.iiitb.EC.rest_services;

import java.sql.Connection;

import org.iiitb.EC.dao.DAO_BankAccount;
import org.iiitb.EC.dao.DAO_Buyer;
import org.iiitb.EC.dao.DAO_Seller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.iiitb.EC.dbcon.DatabaseConnection;
import org.iiitb.EC.model.Buyer;
import org.json.JSONException;
import org.json.JSONObject;

@Path("buyerService")

public class Buyer_Service {
	private static final String SUCCESS_RESULT="Success";
	private static final String FAILURE_RESULT="Failure";
	
	public int get_userid(HttpHeaders httpheaders) {
		String username = httpheaders.getHeaderString("username");
		return DAO_Buyer.get_buyer_id(username);
	}
	

	
	@Path("buyerInfo")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
	 public String getBuyerInfo(@Context HttpHeaders httpheaders) throws Exception{
        System.out.println("Reached");
        int buyer_id = get_userid(httpheaders);
        Buyer buyer=DAO_Buyer.get_buyer_details(buyer_id);
        JSONObject buy = new JSONObject();
        buy.put("name", buyer.getName());
        buy.put("email", buyer.getEmail());
        buy.put("mobile", buyer.getMobile());
        buy.put("dob", buyer.getDob());
        buy.put("address_1", buyer.getAddress_1());
        buy.put("address_2", buyer.getAddress_2());
        return buy.toString();
        
        
//        return "abc";
        
     }
//	@Path("addBuyer")
//	@POST
//	@Consumes(MediaType.MULTIPART_FORM_DATA)
//	@Produces(MediaType.TEXT_PLAIN)
//	public String addBuyer(
//			@FormDataParam("buyer_name") String name,
//			@FormDataParam("buyer_dob") String dob,
//			@FormDataParam("buyer_mobile") String mobile,
//			@FormDataParam("buyer_email") String email,
//			@FormDataParam("buyer_address_1") String address_1,
//			@FormDataParam("buyer_address_2") String address_2,
//			@FormDataParam("buyer_address_2") String password) throws Exception{
//		boolean result = DAO_Buyer.add_Buyer(name, dob, mobile, email, address_1, address_2,password);
//		return result ? "{ \"Response\" : \"" + SUCCESS_RESULT + "\" }" : "{ \"Response\" : \"" + FAILURE_RESULT + "\" }";
//		
//	}
	@Path("addBuyer")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String addBuyer(String input) throws Exception{
		JSONObject input_json = new JSONObject(input);
		System.out.println(input_json);
		boolean result = DAO_Buyer.add_Buyer(input_json.getString("Name"),input_json.getString("DoB"),input_json.getString("Mobile"),input_json.getString("Email"),input_json.getString("Address1"),input_json.getString("Address2"),input_json.getString("Password"));
		System.out.println("Seller Service "+ result);
		
		int buyer_id = DAO_Buyer.get_buyer_id(input_json.getString("Mobile"));
		System.out.println("Buyer_id is ------>"+ buyer_id);
		
		boolean add_acc = DAO_BankAccount.add_Buyer_Account(buyer_id, Integer.parseInt(input_json.getString("Acc_No")), Integer.parseInt(input_json.getString("Balance")));
		
		System.out.println("add_acc "+add_acc);
		
		return (result && add_acc) ? "{ \"Response\" : \"" + SUCCESS_RESULT + "\" }" : "{ \"Response\" : \"" + FAILURE_RESULT + "\" }";
	}
	
	@Path("deleteBuyer")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public String DeleteUser(@Context HttpHeaders httpheaders) throws Exception {
//        JSONObject user_json = new JSONObject(user);
        int user_id = get_userid(httpheaders);
        
        boolean result = DAO_Buyer.Delete_Buyer(user_id);
        return result ? SUCCESS_RESULT : FAILURE_RESULT;
     }
	/*@Path("updateBuyer")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    public String UpdateUserInfo(String buyer) throws Exception {
        JSONObject buyer_json = new JSONObject(buyer);
        boolean result = DAO_Buyer.update_Buyer(buyer_json.getInt("buyer_id"),
        buyer_json.getString("name"),
        buyer_json.getString("email"),
        buyer_json.getString("mobile"),
        buyer_json.getString("dob"),
        buyer_json.getString("address_1"),
        buyer_json.getString("address_2"));
        return result ? SUCCESS_RESULT : FAILURE_RESULT;
        
     }*/
	
	
	@Path("updateBuyer")
    @PUT
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String UpdateUserInfo(
    		@FormDataParam("name") String name,
    		@FormDataParam("dob") String dob,
    		@FormDataParam("email") String email,
    		@FormDataParam("mobile") String mobile,
    		@FormDataParam("address_1") String address_1,
    		@FormDataParam("address_2") String address_2,
    		@Context HttpHeaders httpheaders) throws Exception {
        int buyer_id = get_userid(httpheaders);
        boolean result = DAO_Buyer.update_Buyer(buyer_id,
        name,
        email,
        mobile,
        dob,
        address_1,
        address_2);
        return result ? "{ \"Response\" : \"" + SUCCESS_RESULT + "\" }" : "{ \"Response\" : \"" + FAILURE_RESULT + "\" }";
        
     }
		
	@Path("updateBuyerAddress/{address_2}")
    @PUT
    
    @Produces(MediaType.TEXT_PLAIN)
    public String UpdateUserAddress(@PathParam("address_2") String address_2, 
    		@Context HttpHeaders httpheaders) throws Exception {
        int buyer_id = get_userid(httpheaders);
        System.out.println("Buyer ADDress"+ buyer_id);
        Buyer b = DAO_Buyer.get_buyer_details(buyer_id);
        boolean result = DAO_Buyer.update_Buyer_Address(buyer_id, address_2);
        return result ? "{ \"Response\" : \"" + SUCCESS_RESULT + "\" }" : "{ \"Res;ponse\" : \"" + FAILURE_RESULT + "\" }";
        
     }
	
}
