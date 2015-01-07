package dbtest;

import java.util.ArrayList;

public class Part {
	
	private String category, name, uid, description, addInformation;	
	private double price;
	private ArrayList<String> stores;	
	
	public Part(String category, String name, String uid, double price) {
		this.category = category;
		this.name = name;
		this.uid = uid;
		this.price = price;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getAddInformation() {
		return addInformation;
	}


	public void setAddInformation(String addInformation) {
		this.addInformation = addInformation;
	}


	public String getCategory() {
		return category;
	}


	public String getName() {
		return name;
	}


	public String getUid() {
		return uid;
	}


	public double getPrice() {
		return price;
	}


	public ArrayList<String> getStores() {
		return stores;
	}


	public void setStores(ArrayList<String> stores) {
		this.stores = stores;
	}
}
