package com.endava.hibernate.tests;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.endava.hibernate.model.*;

public class MappingTests {
	
	private SessionFactory sessionFactory;
	private Session session;

	@Before
	public void setUp() throws Exception {
		Configuration config = new Configuration();
		config.configure();
		sessionFactory = config.buildSessionFactory();
		session = sessionFactory.openSession();
	}

	@After
	public void tearDown() throws Exception {
		session.close();
		sessionFactory.close();
	}

	@Test
	public void addSimpleUser() {
		addUser("Mihai");
		
		User u = session.get(User.class, 1);
		assertEquals("Mihai", u.getName());
	}
	
	public void addUser(String name) {
		User u = new User();
		u.setName(name);
		session.save(u);
	}
}
