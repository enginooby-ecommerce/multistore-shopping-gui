package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Purchase {
	private int purchaseId;
	private int customerId;
	private String time;
	private int amount;
	private List<ItemInCart> itemList = new ArrayList<ItemInCart>();

	public Purchase() {

	}

	public Purchase(int purchaseId, int customerId, int amount) {
		this.purchaseId = purchaseId;
		this.customerId = customerId;
		this.amount = amount;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		this.time = dtf.format(now);
	}
	
	
	//getters, setters
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getAmount() {
		return amount;
	}

	public List<ItemInCart> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemInCart> itemList) {
		this.itemList = itemList;
	}

	public int getCustomerId() {
		return customerId;
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
