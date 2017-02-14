package com.endava.hibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static final SessionFactory sessionFactory;
	
	static {
		Configuration config = new Configuration();
		config.configure();
		sessionFactory = config.buildSessionFactory();
	}

	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}
}
