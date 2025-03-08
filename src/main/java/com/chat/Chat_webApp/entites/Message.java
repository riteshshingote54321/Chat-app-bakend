package com.chat.Chat_webApp.entites;

import java.time.LocalDateTime;

public class Message {
	private String sender;
	
	private String content;
	
	private LocalDateTime timeStamp;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(String sender, String content) {
		super();
		this.sender = sender;
		this.content = content;
		this.timeStamp=LocalDateTime.now();
	}
	
	

}
