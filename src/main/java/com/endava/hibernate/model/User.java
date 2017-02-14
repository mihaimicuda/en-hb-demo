package com.endava.hibernate.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	private String name;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="address_id")
	private Address address;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Set<BillingDetails> billingDetails = new HashSet<BillingDetails>();

	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@OrderBy("amount")
	private List<Bid> bids = new ArrayList<Bid>();
	
	public void addBid(Bid bid) {
		bids.add(bid);
		bid.setUser(this);
	}
	
	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	public void addBillingDetails(BillingDetails billingDetails) {
		this.billingDetails.add(billingDetails);
		billingDetails.setUser(this);
	}
	
	public Set<BillingDetails> getBillingDetails() {
		return billingDetails;
	}
	public void setBillingDetails(Set<BillingDetails> billingDetails) {
		this.billingDetails = billingDetails;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", address=" + address + ", billingDetails=" + billingDetails
				+ ", bids=" + bids + "]";
	}

	
}
