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
	
}
