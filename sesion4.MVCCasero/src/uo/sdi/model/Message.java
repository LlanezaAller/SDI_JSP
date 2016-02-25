package uo.sdi.model;

public class Message {
	public static final String ERROR = "ERROR";
	public static final String WARNING = "WARNING";
	public static final String OK = "OK";
	
	private String type;
	private String text;
	
	
	
	public Message(String type, String text) {
		super();
		this.type = type;
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
