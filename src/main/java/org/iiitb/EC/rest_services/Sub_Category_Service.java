package org.iiitb.EC.rest_services;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.iiitb.EC.dao.DAO_Category;
import org.iiitb.EC.dao.DAO_Sub_Category;
import org.iiitb.EC.model.Sub_Category;

@Path("subcategory")


public class Sub_Category_Service {
	private static final String SUCCESS_RESULT="Success";
	private static final String FAILURE_RESULT="Failure";
	
	@Path("/addSubCategory")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public String addSubCategory (
			@FormDataParam("category_id") int category_id,
			@FormDataParam("sub_category_name") String name)
			 throws Exception{
		boolean result = DAO_Sub_Category.addSub_Category(category_id, name);
		return result ? SUCCESS_RESULT : FAILURE_RESULT;
	}	
	
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
}
