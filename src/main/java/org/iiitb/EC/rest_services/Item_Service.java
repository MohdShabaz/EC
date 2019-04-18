package org.iiitb.EC.rest_services;

import java.sql.Connection;
import java.util.ArrayList;

import org.iiitb.EC.dao.DAO_Buyer;
import org.iiitb.EC.dao.DAO_Item;
import org.iiitb.EC.dao.DAO_Item_Seller;
import org.iiitb.EC.dao.DAO_Label_Table;
import org.iiitb.EC.dao.DAO_Order_Details;
import org.iiitb.EC.dao.DAO_Seller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

//import org.ebay_project.ebaytester.service.ProductService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.iiitb.EC.dbcon.DatabaseConnection;
import org.iiitb.EC.model.Buyer;
import org.iiitb.EC.model.Item;
import org.iiitb.EC.model.Seller;
import org.iiitb.EC.model.label_table;
import org.json.JSONArray;
import org.json.JSONObject;

@Path("itemService")
public class Item_Service {
	private static final String SUCCESS_RESULT="Success";
	private static final String FAILURE_RESULT="Failure";	
	
	public int get_seller_id(HttpHeaders httpheaders) {
		String username = httpheaders.getHeaderString("username");
		return DAO_Seller.get_seller_id(username);
	}
	
	
	
	@Path("itemsTop")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	 public String getTopItems() throws Exception{
		
		ArrayList<Item> items = DAO_Item.get_Top5_Items();
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
	
	@Path("itemsDiscounted")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
	 public String getDiscountedItems() throws Exception{
		
		ArrayList<Item> items = DAO_Item.get_Top5_Discounted();
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
		return result.toString();
	}
	
	@Path("checkitem")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
 public static String checkItemExist(@FormDataParam("Barcode") String barcode) throws Exception{
        
 
        int exist = DAO_Item.Check_Item(barcode);
        
        JSONObject obj = new JSONObject();
        obj.put("item_id", exist);
        return obj.toString();
                
     }
	
//	@Path("getitem")
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
// public static String getItem(String item_j) throws Exception{
//        
// 		JSONObject item_json = new JSONObject(item_j);
//        int item_id = Integer.parseInt(item_json.getString("item_id"));
//        Item item=DAO_Item.Get_Item(item_id);
//        
//        JSONObject obj = new JSONObject();
//        obj.put("name", item.getName());
//        obj.put("description", item.getDescription());
//        obj.put("price", item.getPrice());
//        obj.put("discount", item.getDiscount());
//        obj.put("pic_location", item.getPic_location());
//        obj.put("category", item.getCategory());
//        obj.put("sub_category", item.getSub_category());
//        obj.put("dummy_1", item.getDummy_1());
//        obj.put("dummy_2", item.getDummy_2());
//        obj.put("dummy_3", item.getDummy_3());
//        obj.put("dummy_4", item.getDummy_4());
//        return obj.toString();
//                
//     }
	
	@Path("/getitem/{item_id_str}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
 public static String getItem(
  @PathParam("item_id_str") String item_id_str ) throws Exception{
        
        int item_id = Integer.parseInt(item_id_str);
        System.out.println("Iashdckjsnd "+ item_id_str);
        Item item=DAO_Item.Get_Item(item_id);
        Seller seller=DAO_Item_Seller.get_seller_details(item_id);
        System.out.println("Item Name is :  "+ item.getName());
        System.out.println("seller Name is :  "+ seller.getName());
        int total_stars = DAO_Order_Details.get_total_stars(item_id);//DAO_Item.get_total_stars(item_id);
        int total_users_rated = DAO_Order_Details.get_total_users_rated(item_id);//DAO_Item.get_total_users_rated(item_id);
        //Adding Quantity
        int quan = DAO_Item.get_quantity(item_id, seller.getSeller_id());
        System.out.println("Item Quantity , seller_id : "+ quan + " " + seller.getSeller_id());
        
        JSONObject obj = new JSONObject();
        obj.put("name", item.getName());
        obj.put("description", item.getDescription());
        obj.put("price", item.getPrice());
        obj.put("discount", item.getDiscount());
        obj.put("pic_location", item.getPic_location());
        obj.put("category", item.getCategory());
        obj.put("sub_category", item.getSub_category());
        obj.put("barcode", item.getBarcode());
        obj.put("seller_name", seller.getName());
        obj.put("total_stars", total_stars);
        obj.put("total_users_rated", total_users_rated);
        //adding quan
        obj.put("quantity", quan);
        obj.put("dummy_1", item.getDummy_1());
        obj.put("dummy_2", item.getDummy_2());
        obj.put("dummy_3", item.getDummy_3());
        obj.put("dummy_4", item.getDummy_4());
        
        ArrayList<label_table> l_t=DAO_Label_Table.get_all_label_value(item_id);
        JSONArray json_arr = new JSONArray();
        for(label_table l:l_t) {
        JSONObject jo = new JSONObject();
        jo.put("id", l.getId());
        jo.put("item_id",l.getItem_id());
        jo.put("label",l.getLabel());
        jo.put("attr",l.getAttr());
        json_arr.put(jo);
        }
        System.out.println("fwfwfrffrrefrrrrrrrrrrrrrrrrrrrrrrrrrr");
        System.out.println("json arr is5 :  "+l_t.size());
        obj.put("key_value", json_arr);
        System.out.println("json is5 :  "+ obj);
        return obj.toString();
                
     }
	
	@Path("/getitemnew/{item_id_str}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public static String getItemnew(
			  @PathParam("item_str") String item_str ) throws Exception{
			        
			        int barcode = Integer.parseInt(item_str);
			        System.out.println("Iashdckjsnd "+ item_str);
			        Item item=DAO_Item.Get_Item(barcode);
			       
			        JSONObject obj = new JSONObject();
			        obj.put("name", item.getName());
			        obj.put("description", item.getDescription());
			        obj.put("price", item.getPrice());
			        obj.put("discount", item.getDiscount());
			        obj.put("pic_location", item.getPic_location());
			        obj.put("category", item.getCategory());
			        obj.put("sub_category", item.getSub_category());
			        obj.put("barcode", item.getBarcode());
			        
			        return obj.toString();
			                
			     }
		
	
	/*@Path("addItem")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public String addItem(
			@FormDataParam("item_name") String name,
			@FormDataParam("item_barcode") String barcode,
			@FormDataParam("item_description") String description,
			@FormDataParam("item_price") String price,
			@FormDataParam("item_discount") String discount,
			@FormDataParam("item_pic_location") String pic_location,
			@FormDataParam("item_category") String category,
			@FormDataParam("item_sub_category") String sub_category,
			@FormDataParam("item_quantity") String quantity,
			@FormDataParam("item_address") String address,
			@FormDataParam("item_dummy_1") String dummy_1,
			@FormDataParam("item_dummy_2") String dummy_2,
			@FormDataParam("item_dummy_3") String dummy_3,
			@FormDataParam("item_dummy_4") String dummy_4,
			@Context HttpHeaders httpheaders) throws Exception{
		System.out.println("Item Servie"+name);	
		int seller_id = get_seller_id(httpheaders);
		System.out.println("Item Servie"+name);
		pic_location = null;
		dummy_1 = null;
		dummy_2 = null;
		dummy_3 = null;
		dummy_4 = null;
		System.out.println("Item Servie"+name);
		boolean result = DAO_Seller.add_item(seller_id,name, barcode, description,Float.parseFloat(price),Float.parseFloat(discount),pic_location,Integer.parseInt(category),Integer.parseInt(sub_category),Integer.parseInt(quantity),Integer.parseInt(address),dummy_1,dummy_2,dummy_3,dummy_4);
		return result ? SUCCESS_RESULT : FAILURE_RESULT;
	}*/
	@Path("addItem")
    @POST
	@Produces(MediaType.TEXT_PLAIN)  
	public String addItem(String input, @Context HttpHeaders httpheaders) throws Exception{
		
		JSONObject input_json = new JSONObject(input);
		int seller_id = get_seller_id(httpheaders);
		System.out.println(seller_id);
		String s = "images/"+input_json.getString("Barcode")+".png";
		System.out.println(input_json.getString("k_v"));
		boolean result = DAO_Seller.add_item(seller_id, 
		input_json.getString("Product_Name"),
		input_json.getString("Barcode"),
		input_json.getString("Description"), 
		Float.parseFloat(input_json.getString("Price")),
		Float.parseFloat(input_json.getString("Discount")),
		s ,
   		Integer.parseInt(input_json.getString("Category")),
   		Integer.parseInt(input_json.getString("Subcategory")),
   		Integer.parseInt(input_json.getString("Quantity")), 
   		Integer.parseInt(input_json.getString("Address")), 
   "","", "","");
		System.out.println("key_values");
		String key_values=input_json.getString("k_v");
		System.out.println(key_values);
		JSONObject ke_va = new JSONObject(key_values);
		String key_val=ke_va.getString("key_values");
		System.out.println(key_val);
		JSONArray item_json_array = new JSONArray(key_val);
		for(int i=0;i<item_json_array.length();i++)
		{
			System.out.println("int json array");
			System.out.println(i);
			DAO_Label_Table.add_Item_Label(input_json.getString("Barcode"),item_json_array.getJSONObject(i).getString("key"), item_json_array.getJSONObject(i).getString("value"));
		}
		
		
		return result ? "{ \"Response\" : \"" + SUCCESS_RESULT + "\" }" : "{ \"Response\" : \"" + FAILURE_RESULT + "\" }";

}
//	@POST
//	@Path("uploadProductPic")
//	@Consumes(MediaType.MULTIPART_FORM_DATA)
//	public String ProductPicUpload(@FormDataParam("file") InputStream fileInputStream,
//			@FormDataParam("file") FormDataContentDisposition fileFormDataContentDisposition,
//			@FormDataParam("barcode") String barcode ) {
//		String s = "images/" + barcode + ".png";
//		System.out.println("Item Service 1" );
//		String fileName = null;
//		String uploadFilePath = null;
//		fileName = fileFormDataContentDisposition.getFileName();
////		uploadFilePath = new ProductService().uploadProductPic(fileInputStream, fileName, product_id);
//		DAO_Item.writeToFile(fileInputStream, s);
//		return "success";
//	}
	@Path("addQuantity")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public String addQuantity(
	  @FormDataParam("item_barcode") String barcode,
	  @FormDataParam("item_quantity") int quantity,
	  @Context HttpHeaders httpheaders) throws Exception{
		
		int seller_id = get_seller_id(httpheaders);
		
	 boolean result = DAO_Seller.add_quantity_to_existing_item(seller_id,barcode, quantity);
	 return result ? SUCCESS_RESULT : FAILURE_RESULT;
	}
	
	@POST
	@Path("uploadProductPic")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String ProductPicUpload(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileFormDataContentDisposition,
			@FormDataParam("product_id") String product_id) {
		System.out.println("In UPLOADPRODUCTPIC");
		System.out.println(product_id);
		String uploadedFileLocation="/home/skirmish/ooad/EC/src/main/webapp/images/"+product_id+".png";
		writeToFile(fileInputStream,uploadedFileLocation);
		
		

		String fileName = null;
		String uploadFilePath = null;
		fileName = fileFormDataContentDisposition.getFileName();
		
		
//		uploadFilePath = new ProductService().uploadProductPic(fileInputStream, fileName, product_id);
		if (uploadFilePath == null)
			return null;

		return "in abc";
	}
	
	private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation)
	 {
	  try
	  {
		  System.out.println("In WRITETOFILE");  
		  
	   OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
	   int read = 0;
	   byte[] bytes = new byte[1024];

	   out = new FileOutputStream(new File(uploadedFileLocation));
	   while ((read = uploadedInputStream.read(bytes)) != -1)
	   {
	    out.write(bytes, 0, read);
	   }
	   out.flush();
	   out.close();
	  }catch (IOException e)
	  {

	   e.printStackTrace();
	  }

	     /*
	      * Code to store image in path
	      */
	     //return null;
	    }
	
}