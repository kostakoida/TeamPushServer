package ua.nure.pi.entity;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Message implements Serializable, IsSerializable {
	private static final long serialVersionUID = 1L;

	private long messageId;
	private User userSender;
	private Date dataSender;
	private String message;
	private Room room;
	public User getUserSender() {
		return userSender;
	}

	public void setUserSender(User userSender) {
		this.userSender = userSender;
	}

	public Date getDataSender() {
		return dataSender;
	}

	public void setDataSender(Date dataSender) {
		this.dataSender = dataSender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
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
