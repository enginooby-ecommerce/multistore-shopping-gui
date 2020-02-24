package main;

import java.util.ArrayList;
import java.util.List;

import asystem.Database;

public class Retailer extends User {
	List<Integer> my_subscriber_id_list = new ArrayList<>();
	List<Item> my_stock = new ArrayList<Item>();

	public Retailer() {

	}

	public Retailer(String username, String password, int id) {
		super(username, password, id);

	}

	public List<Customer> getMySubsriberList() {
		List<Customer> list = new ArrayList<>();
		for (int i = 0; i < my_subscriber_id_list.size(); i++) {
			list.add(Database.getCustomerByID(my_subscriber_id_list.get(i)));
		}
		return list;
	}

	
	//getters, setters
	public List<Item> getMy_stock() {
		return my_stock;
	}


	public List<Integer> getMy_subscriber_id_list() {
		return my_subscriber_id_list;
	}

	public void setMy_subscriber_id_list(List<Integer> my_subscriber_id_list) {
		this.my_subscriber_id_list = my_subscriber_id_list;
	}

	public void setMy_stock(List<Item> my_stock) {
		this.my_stock = my_stock;
	}

	
	
}
