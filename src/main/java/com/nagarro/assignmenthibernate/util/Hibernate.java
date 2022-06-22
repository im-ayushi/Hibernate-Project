package com.nagarro.assignmenthibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nagarro.assignmenthibernate.domain.Tshirt;

public class Hibernate {
	private static SessionFactory sessionFactoryObj;

	public static SessionFactory buildSessionFactory() {

		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");

		if (sessionFactoryObj == null)
			sessionFactoryObj = configObj.addAnnotatedClass(Tshirt.class).buildSessionFactory();

		return sessionFactoryObj;
	}

}
