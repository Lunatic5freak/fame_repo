package com.internship.internship.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="authorities")
public class UserRole {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name="id")
	    private int id;

	    @OneToOne(cascade=CascadeType.ALL)
		@JoinColumn(name="username")
		private UsersModel user;
	    
	    public UserRole() {
	    	
	    }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public UsersModel getUser() {
			return user;
		}

		public void setUser(UsersModel user) {
			this.user = user;
		}

}
