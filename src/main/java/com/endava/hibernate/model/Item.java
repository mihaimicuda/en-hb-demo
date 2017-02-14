package com.endava.hibernate.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	
	@OneToMany(mappedBy="item", cascade=CascadeType.ALL)
	private Set<Bid> bids = new HashSet<Bid>();
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="cat_item",
			joinColumns=@JoinColumn(name="item_id"),
			inverseJoinColumns=@JoinColumn(name="cat_id")
	)
	private Set<Category> categories = new HashSet<Category>();
	
	public void addCategory(Category category) {
		categories.add(category);
		category.addItem(this);
	}
	
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public void addBid(Bid bid) {
		bids.add(bid);
		bid.setItem(this);
	}
	
	public Set<Bid> getBids() {
		return bids;
	}
	public void setBids(Set<Bid> bids) {
		this.bids = bids;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", bids=" + bids + ", categories="
				+ categories + "]";
	}
	
	
}
