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
import org.iiitb.EC.dao.DAO_Item;
import org.iiitb.EC.dao.DAO_Item_Seller;
import org.iiitb.EC.dao.DAO_Seller;
import org.iiitb.EC.dao.DAO_Shopping_cart;
import org.iiitb.EC.dao.DAO_wishlist;
import org.iiitb.EC.model.Buyer;
import org.iiitb.EC.model.Item;
import org.iiitb.EC.model.wishlist;
import org.json.JSONArray;
import org.json.JSONObject;

@Path("wishlistService")
public class wishlist_Service {
	private static final String SUCCESS_RESULT="Success";
	private static final String FAILURE_RESULT="Failure";
	
	public static int get_buyer_id(HttpHeaders httpheaders) {
		String buyer_id= httpheaders.getHeaderString("username");
		return DAO_Buyer.get_buyer_id(buyer_id);
	}
	
//	@Path("CartDetails")
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//	 public String get_Cart_details(@Context HttpHeaders httpheaders) throws Exception{
//        
//		int buyer_id = get_buyer_id(httpheaders);
//		ArrayList<wishlist> carts = new ArrayList<wishlist>();
//		carts = DAO_Shopping_cart.get_Shopping_Cart_details(buyer_id);
//		JSONArray arr = new JSONArray();
//		for(wishlist cart : carts){
//			JSONObject item_json = new JSONObject();
//			item_json.put("id",cart.getId());
//			item_json.put("buyer_id",cart.getBuyer_id());
//			item_json.put("item_id",cart.getItem_id());
//		//	item_json.put("quantity",cart.getQuantity());
//			arr.put(item_json);
//		}
//		//JSONObject result = new JSONObject();
//		//result.put("Cart", arr);
//		return arr.toString();        
//     }
	
	@Path("AllWishlistDetails")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
	 public String get_All_wishlist_details(@Context HttpHeaders httpheaders) throws Exception{
        
		int buyer_id = get_buyer_id(httpheaders);
		ArrayList<wishlist> carts = new ArrayList<wishlist>();
		carts = DAO_wishlist.get_wishlist_details(buyer_id);
		
		float total_sum=0;
		
		JSONArray arr = new JSONArray();
		for(wishlist cart : carts){
			JSONObject item_json = new JSONObject();
			item_json.put("id",cart.getId());
			item_json.put("buyer_id",cart.getBuyer_id());
			item_json.put("item_id",cart.getItem_id());
		//	item_json.put("quantity",cart.getQuantity());
			
			Item item = new Item();
			item = DAO_Item.Get_Item(cart.getItem_id());
			int item_id = cart.getItem_id();
			int seller_id = DAO_Item_Seller.get_seller_id(item_id);
			
			String name=item.getName();
			String pic=item.getPic_location();
			String barcode = item.getBarcode();
			
			float price = item.getPrice();
			float discount = item.getDiscount();
//			float quan = (float)cart.getQuantity();
//			float available_quantity = (float)DAO_Item.get_quantity(item_id,seller_id);
			
			item_json.put("name",name);
			item_json.put("price",price*(1-discount));
			item_json.put("old_price",price);
			item_json.put("pic",pic);
			item_json.put("barcode",barcode);
	//		item_json.put("available_quantity", available_quantity);
			
			
			//System.out.println("price "+price+" ,discount "+discount);
			
		//	total_sum += ((price)-(price*discount))*(quan);
			
			arr.put(item_json);
		}
		return arr.toString();   
	}
	
	
	@Path("deletewishlistItem/{item_id}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public String DeleteWishlisttItem(
    		@PathParam("item_id") String item_id,
    		@Context HttpHeaders httpheaders) throws Exception {
        
		int buyer_id = get_buyer_id(httpheaders);
        boolean result = DAO_wishlist.delete_Itemof_wishlist(buyer_id, Integer.parseInt(item_id));
        return result ? SUCCESS_RESULT : FAILURE_RESULT;
     }
	
	@Path("movewishlistItem/{item_id}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public String MoveWishlistItem(
    		@PathParam("item_id") String item_id,
    		@Context HttpHeaders httpheaders) throws Exception {
        
		int buyer_id = get_buyer_id(httpheaders);
        boolean result = DAO_wishlist.move_Item_to_cart(buyer_id, Integer.parseInt(item_id));
        return result ? SUCCESS_RESULT : FAILURE_RESULT;
     }
	
	@Path("addToWishlist")
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
	 boolean result = DAO_wishlist.add_To_wishlist(buyer_id, item_id);
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
	
}