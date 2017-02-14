package com.endava.hibernate.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CC")
public class CreditCard extends BillingDetails {
	private String expMonth;
	private String expYear;
	
	public String getExpMonth() {
		return expMonth;
	}
	public void setExpMonth(String expMonth) {
		this.expMonth = expMonth;
	}
	public String getExpYear() {
		return expYear;
	}
	public void setExpYear(String expYear) {
		this.expYear = expYear;
	}
	@Override
	public String toString() {
		return "CreditCard [expMonth=" + expMonth + ", expYear=" + expYear + ", toString()=" + super.toString() + "]";
	}
	
	
}
