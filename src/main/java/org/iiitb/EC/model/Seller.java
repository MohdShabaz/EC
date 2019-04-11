package org.iiitb.EC.model;

public class Seller {
	private int seller_id;
	private String name,mobile,email,password,address_1,address_2;
	public String getPassword() {
		return password;
	}
	public int getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress_1() {
		return address_1;
	}
	public void setAddress_1(String address_1) {
		this.address_1 = address_1;
	}
	public String getAddress_2() {
		return address_2;
	}
	public void setAddress_2(String addres_2) {
		this.address_2 = addres_2;
	}
	public void setPassword(String pass) {
		// TODO Auto-generated method stub
		this.password = pass;
		
	}
	
	

}