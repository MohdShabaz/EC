
package org.iiitb.EC.rest_services;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.iiitb.EC.dao.DAO_Buyer;
import org.iiitb.EC.dao.DAO_Category;
import org.iiitb.EC.dao.DAO_Sub_Category;
import org.iiitb.EC.model.Item;
import org.iiitb.EC.model.Sub_Category;
import org.json.*;
import org.json.simple.parser.JSONParser;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
@Path("subcategory")


public class Sub_Category_Service {
	private static final String SUCCESS_RESULT="Success";
	private static final String FAILURE_RESULT="Failure";
	
	@Path("/addSubCategory")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String addSubCategory (String data) throws Exception {
		//JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject(data);
		int category_id = DAO_Category.getCategoryId(json.getString("categoryName"))	;
		boolean result = DAO_Sub_Category.addSub_Category(category_id, json.getString("subCategoryName"));
		return result ? SUCCESS_RESULT : FAILURE_RESULT;
	}	
	

//
//@GET
//@Path("/getAllSubCategory")
//@Produces(MediaType.APPLICATION_JSON)
//public ArrayList<Sub_Category> getAllSubCategories(int id) {
// DAO_Sub_Category obj = new DAO_Sub_Category();
// ArrayList<Sub_Category> list = obj.get_All_Sub_Category(id);
// if (list.isEmpty()) {
//  return null;
// } else {
//  return list;
// }
//}

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

@GET
@Path("/{category_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Sub_Category> getAllSubCategories(@PathParam("category_id") String category_id) throws Exception {
     System.out.println(category_id);
        ArrayList<Sub_Category> result = DAO_Sub_Category.get_All_Sub_Category(Integer.parseInt(category_id));
        return result;
     }
    
    
}