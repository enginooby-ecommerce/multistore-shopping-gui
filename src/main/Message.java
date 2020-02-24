package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
	private int senderId;
	private String content;
	private String time;

	public Message() {

	}

	public Message(int senderId, String content) {
		this.senderId = senderId;
		this.content = content;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		this.time = dtf.format(now);
	}

	//getters, setters
	public int getSenderId() {
		return senderId;
	}

	public String getContent() {
		return content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}
