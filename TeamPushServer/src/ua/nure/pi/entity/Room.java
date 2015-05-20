package ua.nure.pi.entity;

import java.io.Serializable;
import java.util.Collection;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Room implements Serializable, IsSerializable {
	private static final long serialVersionUID = 1L;
	private long id;
	
	private String name;
	private String password;
	private Collection<User> Members;
	private Collection<User> TeamLeads;
	private Collection<User> ExternalUsers;
	private Collection<User> Bans;
	
	public Room(){
		
	}
	
	public Room(String name, String password){
		setName(name);
		setPassword(password);
	}
	
	public Room(String tableName, String title, long id){
		this(tableName, title);
		setId(id);
	}
	
	public Room(long id, String name, String password,
			Collection<User> members, Collection<User> teamLeads,
			Collection<User> externalUsers, Collection<User> bans) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		Members = members;
		TeamLeads = teamLeads;
		ExternalUsers = externalUsers;
		Bans = bans;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<User> getMembers() {
		return Members;
	}

	public void setMembers(Collection<User> members) {
		Members = members;
	}

	public Collection<User> getTeamLeads() {
		return TeamLeads;
	}

	public void setTeamLeads(Collection<User> teamLeads) {
		TeamLeads = teamLeads;
	}

	public Collection<User> getExternalUsers() {
		return ExternalUsers;
	}

	public void setExternalUsers(Collection<User> externalUsers) {
		ExternalUsers = externalUsers;
	}

	public Collection<User> getBans() {
		return Bans;
	}

	public void setBans(Collection<User> bans) {
		Bans = bans;
	}
	
	
}
