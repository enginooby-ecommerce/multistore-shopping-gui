package main;

import java.util.ArrayList;
import java.util.List;

public class Interaction {
	private int number;
	private List<Customer> interactorList = new ArrayList<Customer>();
	
	public Interaction() {
		
	}

	public Interaction(int number, List<Customer> interactorList) {
		this.number = number;
		this.interactorList = interactorList;
	}

	public void increase() {
		number++;
	}
	
	public void decrease() {
		number--;
	}


	public int getNumber() {
		return number;
	}

	public List<Customer> getInteractorList() {
		return interactorList;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setInteractorList(List<Customer> interactorList) {
		this.interactorList = interactorList;
	}

	
}
