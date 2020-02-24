package main;

public class ItemInCart {
	private int id;
	private int itemID;
	private int quantity;
	private int customerId;
	
	public ItemInCart() {
		
	}
	
	public ItemInCart(int id, int itemID, int quantity, int customerId) {
		this.id = id;
		this.itemID = itemID;
		this.quantity = quantity;
		this.customerId=customerId;
	}
	
	
	public ItemInCart(int itemID, int quantity) {
		this.itemID = itemID;
		this.quantity = quantity;
	}

	//getters, setters
	public int getItemID() {
		return itemID;
	}

	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getCustomerId() {
		return customerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	
	
	
}
