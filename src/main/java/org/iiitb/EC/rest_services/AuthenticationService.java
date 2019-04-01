package org.iiitb.EC.rest_services;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

import org.iiitb.EC.dao.DAO_Buyer;
import org.iiitb.EC.dao.DAO_Seller;

public class AuthenticationService {
	public boolean authenticate(String authCredentials) {

		if (null == authCredentials)
			return false;
		// header value format will be "Basic encodedstring" for Basic
		// authentication. Example "Basic YWRtaW46YWRtaW4="
		final String encodedUserPassword = authCredentials.replaceFirst("Basic"
				+ " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(
					encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(
				usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		// we have fixed the userid and password as admin
		// call some UserService/LDAP here
		boolean authenticationStatus = "admin".equals(username)
				&& "admin".equals(password);
		return authenticationStatus;
	}
	
	public boolean authenticate(String username, String password) {
		System.out.println(username+" "+password);
		boolean authenticationStatus = false;
		if(username.equals("admin") && password.equals("admin")){
			System.out.println("Authenticated");
			return true;
		}else if(username.equals("") && password.equals("")) {
			System.out.println("Authenticated");
			return true;
		}else if(DAO_Buyer.Authenticate(username, password) != -1) {
			System.out.println("Authenticated");
			return true;
		}
		else if(DAO_Seller.Authenticate(username, password) != -1) {
			  System.out.println("Authenticated");
			  return true;
			 }
		return authenticationStatus;
	}
}