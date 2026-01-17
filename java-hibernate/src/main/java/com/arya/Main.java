package com.arya;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.arya.entity.Department;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("-- App start--");
		
		//scenario-1
		demo();
//		System.out.println(System.getenv(null));
		
		//app shut down
		HibernateUtil.shutdown();
		System.out.println("--- byyy ---");
	}
	private static void demo() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			List<Department> departments = session.createNativeQuery("SELECT * FROM departments",
					Department.class).getResultList();
			
			for(Department d: departments) {
				System.out.println("department: "+d.getId()+": "+d.getName());
			}
			
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
}
