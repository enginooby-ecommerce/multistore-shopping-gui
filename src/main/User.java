package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import asystem.Database;

public class User extends Account {
	private int id;
	private String timeCreated;
	private List<Inbox> my_inbox = new ArrayList<Inbox>();
	private ImageIcon avt;
	private String avatar;

	public User() {

	}

	public User(String username, String password, int id) {
		super(username, password);
		this.id = id;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		this.timeCreated = dtf.format(now);
	}
	
	public User(String username, String password, int id, String avatar) {
		super(username, password);
		this.id = id;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		this.timeCreated = dtf.format(now);
		this.avatar=avatar;
		
	}


	// check if inbox already exists
	public boolean inboxExist(String label) {
		boolean isExist = false;
		if (!my_inbox.isEmpty()) {
			for (int i = 0; i < my_inbox.size(); i++) {
				if (label.equals(my_inbox.get(i).getLabel())) {
					isExist = true;
				}
			}
		}
		return isExist;
	}

	// get inbox by label
	public Inbox getInboxByLabel(String label) {
		Inbox inbox = new Inbox();
		//if (!my_inbox.isEmpty()) {
			for (int i = 0; i < my_inbox.size(); i++) {
				if (label.equals(my_inbox.get(i).getLabel())) {
					inbox= my_inbox.get(i);
				}
			}
			return inbox;
		//}
		//return new Inbox();
	}

	public int getId() {
		return id;
	}

	public String getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(String timeCreated) {
		this.timeCreated = timeCreated;
	}

	public List<Inbox> getMy_inbox() {
		return my_inbox;
	}

	public void setMy_inbox(List<Inbox> my_inbox) {
		this.my_inbox = my_inbox;
	}

	public ImageIcon getAvt() {
		return avt;
	}

	public void setAvt(ImageIcon avt) {
		this.avt = avt;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
