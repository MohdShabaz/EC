
package org.iiitb.EC.rest_services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.iiitb.EC.dao.DAO_Buyer;
import org.iiitb.EC.dao.DAO_Sub_Category;
import org.iiitb.EC.model.Item;
import org.iiitb.EC.model.Sub_Category;
import org.json.JSONObject;

@Path("subcategory")


public class Sub_Category_Service {

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


@GET
@Path("/{category_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Sub_Category> getAllSubCategories(@PathParam("category_id") String category_id) throws Exception {
     System.out.println(category_id);
        ArrayList<Sub_Category> result = DAO_Sub_Category.get_All_Sub_Category(Integer.parseInt(category_id));
        return result;
     }
    
    
}