package org.iiitb.EC.model;

public class BirthdayDeal {
	private String name;
	private float price, discount,bdaydiscount;
	private String pic_location;
	private int item_id;
	public String getPic_location() {
		return pic_location;
	}
	public void setPic_location(String pic_location) {
		this.pic_location = pic_location;
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
	public float getBdayDiscount()
	{
		return bdaydiscount;
	}
	public void setBdayDiscount(float bdaydiscount)
	{
		this.bdaydiscount = bdaydiscount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getID()
	{
		return item_id;
	}
	public void setID(int item_id)
	{
		this.item_id = item_id;
	}
	
}
