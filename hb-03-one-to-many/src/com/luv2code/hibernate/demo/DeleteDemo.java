package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		//create session
		Session session = factory.getCurrentSession();

		try {

			//start transaction
			session.beginTransaction();
			
			//Get instructor by primary key
			
			int theID = 1;
			Instructor tempInstructor = session.get(Instructor.class, theID);
			
			System.out.println("Found instructor "+tempInstructor);
			
			//Delete the instructor
			if(tempInstructor != null) {
				
				System.out.println("Deleting: "+tempInstructor);
				
				//Note: will also delete associated "details" object
				//because of of CascadeType.ALL
				session.delete(tempInstructor);
				
			}
			
			//commit the transaction
			session.getTransaction().commit();

			System.out.println("Done!");

		}finally {
			factory.close();
		}

	}

}
