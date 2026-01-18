package com.arya;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.arya.entity.Department;
import com.arya.entity.Employee;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("-- App start--");
		
//		createData();
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
				System.out.println("department: "+d.getId()+": "+d.getName()+": "+d.getEmployees());
			}
			
			tx.commit();
		}catch(Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	private static void createData() {
		System.out.println("Creating employees and departments....");
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
//			create transaction disable autocommit
			tx = session.beginTransaction();
			
//			make objects transient, - exist in java not db
			Department dept = new Department("Engineering");
			Employee emp1 = new Employee("Rohan", 560000.9);
			Employee emp2 = new Employee("Rony", 560000.9);
			
//			link them
			dept.addEmployee(emp1);
			dept.addEmployee(emp2);
			
//			save, persist state, only dept, other will be handles by cascadee
			session.persist(dept);
			
//			commit: this flushes
			tx.commit();
			System.out.println("Done saving");
		}catch(Exception e) {
			if(tx != null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
}


