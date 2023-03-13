package com.jlw.bank;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jlw.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Bank {
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private int abcd=23;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int accountNo;
	
	
	
	
	private  String branchName;
	
	
	public Bank(int accountNo, String branchname) {
		super();
		this.accountNo = accountNo;
		this.branchName = branchname;
		
	}
	public Bank( String branchname) {
		super();
		//this.accountNo = accountNo;
		this.branchName = branchname;
		
	}
    
	public Bank() {
		super();
	}

	

	
	
	

}
