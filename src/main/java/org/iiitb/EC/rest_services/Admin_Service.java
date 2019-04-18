package org.iiitb.EC.rest_services;

import java.sql.Connection;

import org.iiitb.EC.dao.DAO_Admin;
import org.iiitb.EC.dao.DAO_Buyer;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.iiitb.EC.dbcon.DatabaseConnection;
import org.iiitb.EC.model.Buyer;
import org.json.JSONException;
import org.json.JSONObject;

@Path("adminService")

public class Admin_Service {
	private static final String SUCCESS_RESULT="{\"Response\":\"Success\"}";
	private static final String FAILURE_RESULT="Failure";

	
	@Path("authenticateAdmin")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
	 public String authenticateAdmin(@Context HttpHeaders httpheaders) throws Exception {
		String username = httpheaders.getHeaderString("username");
		String password = httpheaders.getHeaderString("password");
		
        
		int validation = DAO_Admin.Authenticate(username, password);
		
		if (validation != -1) {
			return SUCCESS_RESULT;
		} else {
			return FAILURE_RESULT;
		}
        
     }

}
