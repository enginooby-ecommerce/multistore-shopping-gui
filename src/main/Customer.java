package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import asystem.Database;

public class Customer extends User {
	private List<ItemInCart> cart = new ArrayList<ItemInCart>();
	private List<Purchase> purchaseList = new ArrayList<Purchase>();
	private List<Review> myReview = new ArrayList<Review>();
	private List<Integer> my_subscription_id = new ArrayList<>();
	private List<Integer> my_favorite_id = new ArrayList<>();

	public Customer() {
	};

	public Customer(String username, String password, int id) {
		super(username, password, id);

	}

	public Customer(String username, String password, int id, String avatar) {
		super(username, password, id, avatar);

	}

	public List<Retailer> getMySubscriptionList() {
		List<Retailer> list = new ArrayList<>();
		for (int i = 0; i < my_subscription_id.size(); i++) {
			list.add(Database.getRetailerByID(my_subscription_id.get(i)));
		}
		return list;
	}

	public List<Item> getMyFavoriteList() {
		List<Item> list = new ArrayList<>();
		for (int i = 0; i < my_favorite_id.size(); i++) {
			list.add(Database.getItemSuperStockById(my_favorite_id.get(i)));
		}
		return list;
	}

	

	// check if an item is in favorite list
	public boolean itemInFavoriteList(int id_item) {
		for (int id : my_favorite_id) {
			if (id_item == id) {
				return true;
			}
		}
		return false;
	}

	public List<Purchase> getPurchaseList() {
		return purchaseList;
	}

	public List<ItemInCart> getCart() {
		return cart;
	}

	public List<Review> getMyReview() {
		return myReview;
	}

	public void setCart(List<ItemInCart> cart) {
		this.cart = cart;
	}

	public List<Integer> getMy_subscription_id() {
		return my_subscription_id;
	}

	public void setMy_subscription_id(List<Integer> my_subscription_id) {
		this.my_subscription_id = my_subscription_id;
	}

	public List<Integer> getMy_favorite_id() {
		return my_favorite_id;
	}

	public void setMy_favorite_id(List<Integer> my_favorite_id) {
		this.my_favorite_id = my_favorite_id;
	}

	public void setPurchaseList(List<Purchase> purchaseList) {
		this.purchaseList = purchaseList;
	}

	public void setMyReview(List<Review> myReview) {
		this.myReview = myReview;
	}

	
}
