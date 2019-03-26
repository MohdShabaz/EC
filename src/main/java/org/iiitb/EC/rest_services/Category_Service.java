package org.iiitb.EC.rest_services;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.iiitb.EC.dao.DAO_Buyer;
import org.iiitb.EC.dao.DAO_Category;
import org.iiitb.EC.dao.DAO_Sub_Category;
import org.iiitb.EC.model.Category;
import org.json.JSONArray;
import org.json.JSONObject;

@Path("category")


public class Category_Service {
	private static final String SUCCESS_RESULT="Success";
	private static final String FAILURE_RESULT="Failure";
	
	@Path("/addCategory")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String addCategory (
			String name)
			 throws Exception{
		System.out.println(name);
		boolean result = DAO_Category.addCategory(name);
		return result ? SUCCESS_RESULT : FAILURE_RESULT;
	}	
	
	@Path("/renameCategory")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String renameCategory (String data) throws Exception{
		JSONObject json = new JSONObject(data);
		String oldName = json.getString("category");
		String newName = json.getString("name");
		boolean result = DAO_Category.renameCategory(oldName, newName);
		System.out.println(oldName);
		return result ? SUCCESS_RESULT : FAILURE_RESULT;
	}
	
	@Path("/deleteCategory")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCategory (String name) throws Exception{
		boolean result = DAO_Category.deleteCategory(name);
		return result ? SUCCESS_RESULT : FAILURE_RESULT;
	}
	
	@GET
	@Path("/getAllCategory")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Category> getAllCategories() {
		DAO_Category obj = new DAO_Category();
		System.out.println("its is coming here");
		ArrayList<Category> list = obj.get_All_Category();
		if (list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}
	
	@GET
	@Path("/getAllCategoryAdmin")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllCategoriesAdmin() throws Exception{
		DAO_Category obj = new DAO_Category();
		System.out.println("its is coming here");
		ArrayList<Category> list = obj.get_All_Category();
		JSONObject resultList = new JSONObject();
		ArrayList<String> categoryNames = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getCategory_name());
			categoryNames.add(list.get(i).getCategory_name());
		}
		JSONArray jsonList = new JSONArray(categoryNames);
		resultList.put("CategoryNames", jsonList);
		return resultList.toString();
		
	}
}
