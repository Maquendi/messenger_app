package org.maquendi.TheBrain.model;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class Message {

	
	private Long id;
	private String message;
	private Date created;
	private String author;
	
	
	public Message(){
		
	}
	

	
	public Message(Long id, String message, Date created, String author) {
		super();
		this.id = id;
		this.message = message;
		this.created = created;
		this.author = author;
	}






	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	

}
