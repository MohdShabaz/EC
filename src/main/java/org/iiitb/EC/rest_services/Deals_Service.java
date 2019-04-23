package org.iiitb.EC.rest_services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.iiitb.EC.dao.DAO_Deals;

import org.iiitb.EC.model.BirthdayDeal;


import org.json.JSONArray;
import org.json.JSONObject;


@Path("dealsService")
public class Deals_Service {
	
	@Path("getbdaydeals")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getBdayDeals() throws Exception 
	{
		System.out.println("Deals Service DFLGSDSDglksdfjgnkldng");
		ArrayList<BirthdayDeal> b_d=DAO_Deals.get_all_bday_deals();
        JSONArray json_arr = new JSONArray();
        for(BirthdayDeal l:b_d) {
        JSONObject jo = new JSONObject();
        jo.put("name",l.getName());
        jo.put("discount", l.getDiscount());
        jo.put("bdaydiscount",l.getBdayDiscount());
        jo.put("pic_location", l.getPic_location());
        jo.put("price", l.getPrice());
        jo.put("item_id", l.getID());
        json_arr.put(jo);
        }
        return json_arr.toString();
	}
	
	@Path("getAllDealTypes")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllDealTypes() throws Exception {
		ArrayList<String> allDealTypes = DAO_Deals.getAllDealTypes();
        JSONArray json_arr = new JSONArray();
        for(String deal : allDealTypes) {
        	JSONObject jsonObject = new JSONObject();
        	jsonObject.put("deal_name",	deal);
        	
        	json_arr.put(jsonObject);
        }
        
        return json_arr.toString();
	}
	@Path("/isItemInBirDeal/{item_id_str}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
 public static String getItem(
  @PathParam("item_id_str") String item_id_str ) throws Exception{
		String b="failure";
		ArrayList<BirthdayDeal> b_d=DAO_Deals.get_all_bday_deals();
		JSONObject jo = new JSONObject();
		int itemId=Integer.parseInt(item_id_str);
		for(BirthdayDeal l:b_d) {
			if(l.getID()==itemId)
			{
				b="success";
				jo.put("bDiscount", l.getBdayDiscount());
			}
		}
		jo.put("result", b);
		return jo.toString();
	}
}
