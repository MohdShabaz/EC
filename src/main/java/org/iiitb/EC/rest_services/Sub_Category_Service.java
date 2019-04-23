package org.iiitb.EC.rest_services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.iiitb.EC.dao.DAO_Buyer;
import org.iiitb.EC.dao.DAO_Category;
import org.iiitb.EC.dao.DAO_Sub_Category;
import org.iiitb.EC.model.Item;
import org.iiitb.EC.model.Sub_Category;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Path("subcategory")


public class Sub_Category_Service {
	
	private static final String SUCCESS_RESULT="Success";
	private static final String FAILURE_RESULT="Failure";
	/*
	@GET
	@Path("/getAllSubCategory")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Sub_Category> getAllSubCategories(int id) {
		DAO_Sub_Category obj = new DAO_Sub_Category();
		ArrayList<Sub_Category> list = obj.get_All_Sub_Category(id);
		if (list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}
	*/
	
	@Path("/addSubCategory")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String addSubCategory (String data) throws Exception {
		//JSONParser parser = new JSONParser();
		System.out.println("data");
		JSONObject json = new JSONObject(data);
		int category_id = DAO_Category.getCategoryId(json.getString("categoryName"))	;
		boolean result = DAO_Sub_Category.addSub_Category(category_id, json.getString("subCategoryName"));
		return result ? SUCCESS_RESULT : FAILURE_RESULT;
} 
	
	
	
	@GET
	@Path("/getAllSubCategories/{category_id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public ArrayList<Sub_Category> getAllSubCategories(@PathParam("category_id") String category_id) throws Exception {
	     System.out.println("yes here "+category_id);
	        ArrayList<Sub_Category> result = DAO_Sub_Category.get_All_Sub_Category(Integer.parseInt(category_id));
	        return result;
	     }
	    
	    

	@GET
	@Path("/getAllitemsSubCategory/{sub_cat_id}")
	@Produces(MediaType.APPLICATION_JSON)
	 public String getAllitemsSubCategories(@PathParam("sub_cat_id") String sub_cat_id) throws JSONException{
	 System.out.println("coming here1");
	 System.out.println("sub_category_id "+sub_cat_id);
	 //JSONObject obj = new JSONObject(sub_category_id);
	 //String sc_id = obj.getString("id");
	 System.out.println("coming here2");
	 ArrayList<Item> items = DAO_Sub_Category.get_All_Items_Sub_Category(Integer.parseInt(sub_cat_id));
	 System.out.println("coming here3");
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
	  item_json.put("brand", item.getDummy_1());
	  item_json.put("rating", item.getDummy_2());
	  item_json_array.put(item_json);
	 }
	 return item_json_array.toString();
	}
	
	
	@Path("/getSubCategories")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getSubCategoriesOfCategory(@QueryParam("categoryName") String categoryName) throws Exception {
		int category_id = DAO_Category.getCategoryId(categoryName);
		System.out.println(category_id);
		ArrayList<Sub_Category> result = DAO_Sub_Category.get_All_Sub_Category(category_id);
		System.out.println("Size: " + result.size());
		JSONObject resultList = new JSONObject();
		ArrayList<String> subcategoryNames = new ArrayList<String>();
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).getSub_category_name());
			subcategoryNames.add(result.get(i).getSub_category_name());
		}
		JSONArray jsonList = new JSONArray(subcategoryNames);
		resultList.put("subCategoryName", jsonList);
		return resultList.toString();
	    //return Response.status(200).entity(resultList).build();
	}
}
