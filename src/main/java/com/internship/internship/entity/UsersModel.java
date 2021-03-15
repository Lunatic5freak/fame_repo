package com.internship.internship.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UsersModel {
	
	@Id
	@Column(name="username")
	private String email;
	
	@Column(name="fname")
	private String fname;
	
	@Column(name="lname")
	private String lname;
	
	@Column(name="contact")
	private String contact;
	
	@Column(name="password")
	private String password;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="fileid")
	private ProfileImage image;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public ProfileImage getImage() {
		return image;
	}

	public void setImage(ProfileImage image) {
		this.image = image;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UsersModel(String email, String fname, String lname, String contact, ProfileImage image,String password) {
		super();
		this.email = email;
		this.fname = fname;
		this.lname = lname;
		this.contact = contact;
		this.image = image;
		this.password=password;
	}
	public UsersModel() {
		
	}

}
