package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;

import asystem.Database;
import asystem.Sale;

public class Item {
	private int itemID;
	private int retailerId;
	private String description;
	private String itemName;
	private String category;
	private int itemPrice;
	private int inStock;
	private Sale promo;
	private ImageIcon icon;// = new ImageIcon(Item.class.getResource("/picture/item/unavailable.png"));
	private List<Customer> b = new ArrayList<>();
	private List<Review> reviewList = new ArrayList<Review>();
	private HashMap<Customer,String> buyer_list = new HashMap<>(); // list of customers purchasing this item and time

	public Item() {

	}

	public Item(int itemID, int retailerId, String description, String itemName, String category, int itemPrice, int inStock) {
		this.description = description;
		this.itemID = itemID;
		this.retailerId = retailerId;
		this.itemName = itemName;
		this.category = category;
		this.itemPrice = itemPrice;
		this.inStock = inStock;
	}

	public int getRating() {
		int rating = 0;
		if (!reviewList.isEmpty()) {
			for (Review review : reviewList) {
				rating += review.getRate();
			}
			return (rating / reviewList.size());
		}
		return 0;
	}
	

	//getters, setters
	public int getItemID() {
		return itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public String getCategory() {
		return category;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public int getInStock() {
		return inStock;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public void setInStock(int inStock) {
		this.inStock = inStock;
	}

	public List<Review> getReviewList() {
		return reviewList;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRetailerId() {
		return retailerId;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public HashMap<Customer, String> getBuyer_list() {
		return buyer_list;
	}


	public Sale getPromo() {
		return promo;
	}

	public void setPromo(Sale promo) {
		this.promo = promo;
	}

	

	public List<Customer> getB() {
		return b;
	}

	public void setRetailerId(int retailerId) {
		this.retailerId = retailerId;
	}



	public void setB(List<Customer> b) {
		this.b = b;
	}

	public void setReviewList(List<Review> reviewList) {
		this.reviewList = reviewList;
	}

	public void setBuyer_list(HashMap<Customer, String> buyer_list) {
		this.buyer_list = buyer_list;
	}
	
	
}
