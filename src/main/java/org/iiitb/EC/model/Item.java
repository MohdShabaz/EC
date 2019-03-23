package org.iiitb.EC.model;

public class Item {
	private int item_id;
	private String description, name, pic_location, category, sub_category, barcode;
	private float price, discount;
	private String dummy_1, dummy_2, dummy_3, dummy_4;
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic_location() {
		return pic_location;
	}
	public void setPic_location(String pic_location) {
		this.pic_location = pic_location;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSub_category() {
		return sub_category;
	}
	public void setSub_category(String sub_category) {
		this.sub_category = sub_category;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
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
	public String getDummy_1() {
		return dummy_1;
	}
	public void setDummy_1(String dummy_1) {
		this.dummy_1 = dummy_1;
	}
	public String getDummy_2() {
		return dummy_2;
	}
	public void setDummy_2(String dummy_2) {
		this.dummy_2 = dummy_2;
	}
	public String getDummy_3() {
		return dummy_3;
	}
	public void setDummy_3(String dummy_3) {
		this.dummy_3 = dummy_3;
	}
	public String getDummy_4() {
		return dummy_4;
	}
	public void setDummy_4(String dummy_4) {
		this.dummy_4 = dummy_4;
	}
}
