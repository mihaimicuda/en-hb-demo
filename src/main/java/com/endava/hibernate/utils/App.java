package com.endava.hibernate.utils;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.endava.hibernate.model.Address;
import com.endava.hibernate.model.BankAccount;
import com.endava.hibernate.model.Bid;
import com.endava.hibernate.model.BillingDetails;
import com.endava.hibernate.model.Category;
import com.endava.hibernate.model.CreditCard;
import com.endava.hibernate.model.Item;
import com.endava.hibernate.model.User;

public class App {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtils.getSessionfactory();
		Session s1 = sessionFactory.openSession();
		s1.beginTransaction();
		
		Address a1 = new Address();
		a1.setCity("B");
		a1.setStreet("Calea Crangasi");
		a1.setZipCode("000");
		
		User u1 = new User();
		u1.setName("geo");
		u1.setAddress(a1);
		
		s1.getTransaction().commit();
		s1.save(u1);
		
		User lu1 = s1.get(User.class, 1);
		System.out.println(lu1.getName());
		
		s1.close();
		
		Session s2 = sessionFactory.openSession();
		
		User retrieved = s2.get(User.class, 1);
		System.out.println(retrieved.getAddress().getUser());
		
		s2.close();
		///////////////////////////////////////////////
		Session s3 = sessionFactory.openSession();
		s3.beginTransaction();
		
		CreditCard card = new CreditCard();
		card.setCreated(new Date());
		card.setExpMonth("Jul");
		card.setExpYear("2021");
		card.setOwner("geo");
		BankAccount ba = new BankAccount();
		ba.setBankName("ING");
		ba.setBankSwift("456");
		ba.setCreated(new Date());
		ba.setOwner("geo");
		
		s3.save(card);
		s3.save(ba);
		s3.getTransaction().commit();
		s3.close();
		/////////////////////////////////////////////
		Session s4 = sessionFactory.openSession();
		s4.beginTransaction();
		
		Bid b1 = new Bid();
		b1.setAmount(10);
		Bid b2 = new Bid();
		b2.setAmount(5);
		
		Item i1 = new Item();
		i1.setName("aspirator");
		
		i1.addBid(b1);
		i1.addBid(b2);
		
		s4.save(i1);
		
		s4.getTransaction().commit();
		s4.close();
		
		/////////////////////////////////////////////
		Session s5 = sessionFactory.openSession();
		s5.beginTransaction();
		
		System.out.println(i1);
		Category cat = new Category();
		cat.setName("electrocasnice");
		
		Category cat2 = new Category();
		cat2.setName("fun");
		
		Item i2 = new Item();
		i2.setName("ps4");
		i2.addCategory(cat);
		i2.addCategory(cat2);
		
		
		i1.addCategory(cat);
		
		s5.saveOrUpdate(i1);
		s5.save(i2);
		
		s5.getTransaction().commit();
		s5.close();
		
		/////////////////////////////////////////////
		Session s6 = sessionFactory.openSession();
		s6.beginTransaction();
		
		User usr1 = s6.get(User.class, 1);
		CreditCard cc1 = (CreditCard)s6.get(BillingDetails.class, 1);
		
		usr1.addBillingDetails(cc1);
		
		Item itm = new Item();
		itm.setName("lego");
		itm.setDescription("aaaaaa");
		
		Bid b3 = new Bid();
		b3.setAmount(1);
		itm.addBid(b3);
		
		usr1.addBid(b3);
		
		s6.save(usr1);
		s6.save(itm);
		

		
		s6.getTransaction().commit();
		s6.close();
		
		//////////////////////////////////////
		
		Session s7 = sessionFactory.openSession();
		s7.beginTransaction();
		
		User queried_user = (User) s7.createQuery("from User u where u.name= ?")
				.setParameter(0, "geo")
				.setMaxResults(1)
				.getSingleResult();
		
		System.out.println(queried_user);
		
		s7.getTransaction().commit();
		s7.close();
		
		//////////////////////////////////////
		
		Session s8 = sessionFactory.openSession();
		s8.beginTransaction();
		
		Category cat81 = new Category();
		cat81.setName("jocuri");
		
		Category cat82 = new Category();
		cat82.setName("jocuri pc");
		
		cat81.addChildCategory(cat82);
		
		s8.save(cat81);
		
		List<User> users = s8.createNamedQuery("getAllUsers", User.class)
		.getResultList();
		for(User u:users) {
			System.out.println(u.getName());
		}
		
		
		s8.getTransaction().commit();
		s8.close();
		
		sessionFactory.close();
	}

}
