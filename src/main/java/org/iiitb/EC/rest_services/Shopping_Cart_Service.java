package org.iiitb.EC.rest_services;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.iiitb.EC.dao.DAO_Buyer;
import org.iiitb.EC.dao.DAO_ClearanceSale;
import org.iiitb.EC.dao.DAO_Item;
import org.iiitb.EC.dao.DAO_Item_Seller;
import org.iiitb.EC.dao.DAO_Seller;
import org.iiitb.EC.dao.DAO_Shopping_cart;
import org.iiitb.EC.model.Buyer;
import org.iiitb.EC.model.Item;
import org.iiitb.EC.model.Shopping_Cart;
import org.json.JSONArray;
import org.json.JSONObject;

@Path("ShoppingCartService")
public class Shopping_Cart_Service {
	private static final String SUCCESS_RESULT="Success";
	private static final String FAILURE_RESULT="Failure";
	
	public static int get_buyer_id(HttpHeaders httpheaders) {
		String buyer_id= httpheaders.getHeaderString("username");
		return DAO_Buyer.get_buyer_id(buyer_id);
	}
	
	@Path("CartDetails")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
	 public String get_Cart_details(@Context HttpHeaders httpheaders) throws Exception{
        
		int buyer_id = get_buyer_id(httpheaders);
		ArrayList<Shopping_Cart> carts = new ArrayList<Shopping_Cart>();
		carts = DAO_Shopping_cart.get_Shopping_Cart_details(buyer_id);
		JSONArray arr = new JSONArray();
		for(Shopping_Cart cart : carts){
			JSONObject item_json = new JSONObject();
			item_json.put("id",cart.getId());
			item_json.put("buyer_id",cart.getBuyer_id());
			item_json.put("item_id",cart.getItem_id());
			item_json.put("quantity",cart.getQuantity());
			arr.put(item_json);
		}
		//JSONObject result = new JSONObject();
		//result.put("Cart", arr);
		return arr.toString();        
     }
	
	@Path("AllCartDetails")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
	 public String get_All_Cart_details(@Context HttpHeaders httpheaders) throws Exception{
        
		int buyer_id = get_buyer_id(httpheaders);
		ArrayList<Shopping_Cart> carts = new ArrayList<Shopping_Cart>();
		carts = DAO_Shopping_cart.get_Shopping_Cart_details(buyer_id);
		
		float total_sum=0;
		
		JSONArray arr = new JSONArray();
		for(Shopping_Cart cart : carts){
			JSONObject item_json = new JSONObject();
		
			
			item_json.put("id",cart.getId());
			item_json.put("buyer_id",cart.getBuyer_id());
			item_json.put("item_id",cart.getItem_id());
			item_json.put("quantity",cart.getQuantity());
			
			Item item = new Item();
			item = DAO_Item.Get_Item(cart.getItem_id());
			int item_id = cart.getItem_id();
			
			
			//added shabaz
	        boolean check_item_in_clearance_Sale = DAO_ClearanceSale.item_exist_in_clearance_sale(item_id);

	        float clearance_discount=-1;
	        if(check_item_in_clearance_Sale==true) {
	        	clearance_discount = DAO_ClearanceSale.get_clearance_sale_discount();
	        	item_json.put("clearance_discount", clearance_discount);
	        }
	        else {
	        	item_json.put("clearance_discount", 0);        	
	        }
			
			
			
			int seller_id = DAO_Item_Seller.get_seller_id(item_id);
			
			String name=item.getName();
			String pic=item.getPic_location();
			String barcode = item.getBarcode();
			
			float price = item.getPrice();
			float discount = item.getDiscount();
			float quan = (float)cart.getQuantity();
			float available_quantity = (float)DAO_Item.get_quantity(item_id,seller_id);
			
			item_json.put("name",name);
			item_json.put("price",price*(1-discount)*(1-clearance_discount));
		
			
			//added shabaz
			item_json.put("discount", discount);
			item_json.put("old_price",price);
			item_json.put("pic",pic);
			item_json.put("barcode",barcode);
			item_json.put("available_quantity", available_quantity);
			
			
			//System.out.println("price "+price+" ,discount "+discount);
			
			total_sum += ((price)-(price*discount))*(quan);
			
			arr.put(item_json);
		}
		
		
		return arr.toString();   
     }
	
	
	@Path("getPrice")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
	 public float getTotalPrice(@Context HttpHeaders httpheaders) throws Exception{
        
		int buyer_id = get_buyer_id(httpheaders);
		ArrayList<Shopping_Cart> carts = new ArrayList<Shopping_Cart>();
		carts = DAO_Shopping_cart.get_Shopping_Cart_details(buyer_id);
		
		float total_sum=0;
		
		JSONArray arr = new JSONArray();
		for(Shopping_Cart cart : carts){
			JSONObject item_json = new JSONObject();
			item_json.put("id",cart.getId());
			item_json.put("buyer_id",cart.getBuyer_id());
			item_json.put("item_id",cart.getItem_id());
			item_json.put("quantity",cart.getQuantity());
			
			Item item = new Item();
			item = DAO_Item.Get_Item(cart.getItem_id());
			
			int item_id = cart.getItem_id();
			
			
			//added shabaz
			
	        System.out.println("item_id "+item_id);

	        boolean check_item_in_clearance_Sale = DAO_ClearanceSale.item_exist_in_clearance_sale(item_id);
	        
	        System.out.println("check_item_in_clearance_Sale "+check_item_in_clearance_Sale);

	        float clearance_discount=0;
	        if(check_item_in_clearance_Sale==true) {
	        	clearance_discount = DAO_ClearanceSale.get_clearance_sale_discount();
	        	item_json.put("clearance_discount", clearance_discount);
	        }
	        else {
	        	item_json.put("clearance_discount", 0);        	
	        }
			
			
			String name=item.getName();
			String pic=item.getPic_location();
			
			float price = item.getPrice();
			float discount = item.getDiscount();
			float quan = (float)cart.getQuantity();
			
			
			System.out.println("price is "+price);
			System.out.println("discount is "+discount);
			System.out.println("clearance discount is "+clearance_discount);
			System.out.println("quan is "+quan);
			
			item_json.put("name",name);
			item_json.put("price",price);
			item_json.put("pic",pic);
			
			
			//System.out.println("price "+price+" ,discount "+discount);
			
			total_sum += ((price*(1-discount)*(1-clearance_discount)))*(quan);
		}
		
		return total_sum;
	}
	
	
	
//	@Path("addCart")
//	@POST
//	@Consumes(MediaType.MULTIPART_FORM_DATA)
//	@Produces(MediaType.TEXT_PLAIN)
//	public String addCart(
//			@FormDataParam("item_id") String item_id_str,
//			@FormDataParam("quantity") String quantity_str,
//			@Context HttpHeaders httpheaders) throws Exception{
//		int buyer_id = get_buyer_id(httpheaders);
//		int item_id = Integer.parseInt(item_id_str);
//		int quantity = Integer.parseInt(quantity_str);
//		boolean result = DAO_Shopping_cart.add_To_Shopping_Cart(buyer_id, item_id, quantity);
//		return result ? SUCCESS_RESULT : FAILURE_RESULT;
//		
//	}
	
	/*@Path("addCart")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public String addCart(@Context HttpHeaders httpheaders, String cart) throws Exception{
		System.out.println("Add cart function");
		int buyer_id = get_buyer_id(httpheaders);
		//JSONObject buyer_json = new JSONObject(cart);
		//boolean result = DAO_Shopping_cart.add_To_Shopping_Cart(buyer_id, buyer_json.getInt("item_id"), buyer_json.getInt("quantity"));
		boolean result = DAO_Shopping_cart.add_To_Shopping_Cart(buyer_id, Integer.parseInt(cart), 1);
		return result ? SUCCESS_RESULT : FAILURE_RESULT;
		
	}*/
	
	@Path("addCart")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String addCart(
	  String cart, @Context HttpHeaders httpheaders) throws Exception{
	 System.out.println("Test jsdbhcjsdckn");
	 System.out.println(cart);
	 JSONObject cart_json = new JSONObject(cart);
	 int buyer_id = get_buyer_id(httpheaders);// 
	 int item_id = Integer.parseInt(cart_json.getString("item_id"));
	 int quantity = Integer.parseInt(cart_json.getString("quantity"));
	 boolean result = DAO_Shopping_cart.add_To_Shopping_Cart(buyer_id, item_id, quantity);
	 System.out.println("result"+result);
	 
	 JSONObject json = new JSONObject();
		
		if(result)
		{
			json.put("result","success");
		}
		else
		{
			json.put("result","failure");
		}
	 
		return json.toString();
	 
	}
	
	
	/*@Path("deleteCartItem")
    @DELETE
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String DeleteCartItem(
    		@FormDataParam("item_id") String item_id,
    		@Context HttpHeaders httpheaders) throws Exception {
        
		int buyer_id = get_buyer_id(httpheaders);
        boolean result = DAO_Shopping_cart.delete_Itemof_Shopping_Cart(buyer_id, Integer.parseInt(item_id));
        return result ? SUCCESS_RESULT : FAILURE_RESULT;
     }*/
	
	@Path("deleteCartItem/{item_id}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public String DeleteCartItem(
    		@PathParam("item_id") String item_id,
    		@Context HttpHeaders httpheaders) throws Exception {
        
		int buyer_id = get_buyer_id(httpheaders);
        boolean result = DAO_Shopping_cart.delete_Itemof_Shopping_Cart(buyer_id, Integer.parseInt(item_id));
        return result ? SUCCESS_RESULT : FAILURE_RESULT;
     }
	
	@Path("clearCart")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
	public String ClearCart(@Context HttpHeaders httpheaders) throws Exception{
		
		int buyer_id = get_buyer_id(httpheaders);
		boolean result = DAO_Shopping_cart.clear_cart(buyer_id);
		
		
		return result ? SUCCESS_RESULT : FAILURE_RESULT;
	}
	
	@Path("updateCart")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
	public String Update_Cart_Item(String item) throws Exception{
		
		JSONObject buyer_json = new JSONObject(item);
        boolean result = DAO_Shopping_cart.update_Itemof_Shopping_Cart(buyer_json.getInt("buyer_id"),
        buyer_json.getInt("item_id"),
        buyer_json.getInt("quantity"));
        return result ? SUCCESS_RESULT : FAILURE_RESULT;
	}
	
	
	/*@Path("updateCart")
    @PUT
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
	public String Update_Cart_Item(
			@FormDataParam("item_id") String item_id,
			@FormDataParam("quantity") String quantity,
			@Context HttpHeaders httpheaders) throws Exception{
		
		int buyer_id = get_buyer_id(httpheaders);
        boolean result = DAO_Shopping_cart.update_Itemof_Shopping_Cart(buyer_id,
        Integer.parseInt(item_id),
        Integer.parseInt(quantity));
        return result ? SUCCESS_RESULT : FAILURE_RESULT;
	}*/
	
	@Path("updateCart/{item_id}/{quantity}")
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
	public String Update_Cart_Item(
			@PathParam("item_id") String item_id,
			@PathParam("quantity") String quantity,
			@Context HttpHeaders httpheaders) throws Exception{
		
		int buyer_id = get_buyer_id(httpheaders);
        boolean result = DAO_Shopping_cart.update_Itemof_Shopping_Cart(buyer_id,
        Integer.parseInt(item_id),
        Integer.parseInt(quantity));
        return result ? SUCCESS_RESULT : FAILURE_RESULT;
	}
	
}
