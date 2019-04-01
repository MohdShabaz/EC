package org.iiitb.EC.rest_services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.iiitb.EC.dao.DAO_BankAccount;
import org.iiitb.EC.dao.DAO_Order_Details;
import org.iiitb.EC.model.Order_Details;

@Path("/bankService")
public class BankAccountService {
	
	private static final String SUCCESS_RESULT="Success";
	private static final String FAILURE_RESULT="Failure";

	@Path("/transaction")
	@PUT
    @Produces(MediaType.TEXT_PLAIN)
	public String PerformTransaction(@Context HttpHeaders httpheaders) {
		String order_id_http = httpheaders.getHeaderString("order_id");
		int order_id = Integer.parseInt(order_id_http);
		Order_Details order = DAO_Order_Details.getOrder(order_id);
		
		int buyer_id = order.getBuyer_id();
		int seller_id = order.getSeller_id();
		int transactionAmount = order.getTotal_amount();
		
		boolean result = DAO_BankAccount.performTransaction(transactionAmount, order_id, buyer_id, seller_id);
		
		return result ? SUCCESS_RESULT : FAILURE_RESULT;
	}
	
}
