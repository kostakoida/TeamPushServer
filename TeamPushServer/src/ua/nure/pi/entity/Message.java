package ua.nure.pi.entity;

import java.io.Serializable;
import java.sql.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Message implements Serializable, IsSerializable {
	private static final long serialVersionUID = 1L;

	private long messageId;
	private String userSender;
	private String dataSender;
	private String message;
	private String room;
	
	public String getUserSender() {
		return userSender;
	}

	public void setUserSender(String userSender) {
		this.userSender = userSender;
	}

	public String getDataSender() {
		return dataSender;
	}

	public void setDataSender(String dataSender) {
		this.dataSender = dataSender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	
	
	@Override
	public int hashCode() {
		return new Long(messageId).hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(message);
		return sb.toString();
	}

	public long getMeassageId() {
		return messageId;
	}

	public void setMeassageId(long meassageId) {
		this.messageId = meassageId;
	}
}
