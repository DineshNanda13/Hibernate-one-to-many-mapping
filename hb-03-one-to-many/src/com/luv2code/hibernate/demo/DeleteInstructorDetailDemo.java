package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

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
			
			//get the instructor detail object
			int theID = 3;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theID);
			
			//print the instructor detail
			System.out.println("tempInstructorDetail: "+tempInstructorDetail);
			
			//print the associated instructor
			System.out.println("The associated instructor: "+ tempInstructorDetail.getInstructor());
			
			//REMOVE the associated object reference
			//break BI-directional link
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			//now let's delete the instructor detail
			System.out.println("Deleting tempInstructorDetail: "+tempInstructorDetail);
			session.delete(tempInstructorDetail);
			
			//commit the transaction
			session.getTransaction().commit();

			System.out.println("Done!");

		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
			//handle connection leak issue
			session.close();
			
			factory.close();
		}

	}

}
