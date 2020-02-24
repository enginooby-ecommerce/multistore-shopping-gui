package main;

import java.util.ArrayList;
import java.util.List;

public class Inbox {
	private String label;
	private int customerId;
	private int retailerId;
	private List<Message> messages = new ArrayList<Message>();

	public Inbox() {

	}

	public Inbox(int customerId, int retailerId) {
		this.label = customerId+"&"+retailerId;
		this.customerId = customerId;
		this.retailerId = retailerId;
	}

	
	//getters, setters
	public int getCustomerId() {
		return customerId;
	}

	public int getRetailerId() {
		return retailerId;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public void setRetailerId(int retailerId) {
		this.retailerId = retailerId;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	

}
