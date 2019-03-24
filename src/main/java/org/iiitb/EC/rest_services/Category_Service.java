package org.iiitb.EC.rest_services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.iiitb.EC.dao.DAO_Category;
import org.iiitb.EC.dao.DAO_Sub_Category;
import org.iiitb.EC.model.Category;

@Path("category")


public class Category_Service {

	
	@GET
	@Path("/getAllCategory")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Category> getAllCategories() {
		DAO_Category obj = new DAO_Category();
		ArrayList<Category> list = obj.get_All_Category();
		if (list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}
	
}
