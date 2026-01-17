package com.arya;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//singleton utility class, reads config, builds sessionfactory thread safe just one
//which is heavy ,, unlike session for all and light ..exposes safely, and shuts it
public class HibernateUtil {
	private static final SessionFactory sessionFactory  = buildSessionFactory();
			
	private static SessionFactory buildSessionFactory() {
		try {
			//read hibernate.cfg.xml and try to build factory
			return new Configuration().configure().buildSessionFactory();
		}catch(Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void shutdown() {
		getSessionFactory().close();
	}
}
