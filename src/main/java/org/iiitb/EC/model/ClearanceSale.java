package org.iiitb.EC.model;

public class ClearanceSale {
	private String item_name;
	private float price, discount,clearance_discount;
	private String pic_location;
	private String start_date;
	private String end_date;

	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public float getClearance_discount() {
		return clearance_discount;
	}
	public void setClearance_discount(float clearancediscount) {
		this.clearance_discount = clearancediscount;
	}
	public String getPic_location() {
		return pic_location;
	}
	public void setPic_location(String pic_location) {
		this.pic_location = pic_location;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
}
