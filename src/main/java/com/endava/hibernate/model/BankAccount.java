package com.endava.hibernate.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BA")
public class BankAccount extends BillingDetails{
	private String bankName;
	private String bankSwift;
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankSwift() {
		return bankSwift;
	}
	public void setBankSwift(String bankSwift) {
		this.bankSwift = bankSwift;
	}
}
