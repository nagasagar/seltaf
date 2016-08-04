package com.seltaf.dataobjects;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group {
	private String name;
	private List<User> users=new ArrayList<User>();
	
	@XmlElement 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement(name="user")
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public String toString() {
        return ("Group: [ Name: " + name +", Users:["+users+"]"+ " ]");
    }

}
