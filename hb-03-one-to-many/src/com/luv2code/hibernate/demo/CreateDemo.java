package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

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

			//create the objects
			/*Instructor tempInstructor = new Instructor("Dinesh", "Nanda", "Danny76@gmail.com");

			InstructorDetail tempInstructorDetail = new InstructorDetail
					                         ("http://www.dinesh@youtube.com/youtube", "Cricket");*/

			Instructor tempInstructor = new Instructor("Daniel", "Cristea", "Daniel76@gmail.com");

			InstructorDetail tempInstructorDetail = new InstructorDetail
					("http://www.danielyoutube.com/youtube", "Guitar");

			//associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);

			//start transaction
			session.beginTransaction();

			//save the instructor
			System.out.println("Saving Instructor: "+tempInstructor);
			session.save(tempInstructor);

			//commit the transaction
			session.getTransaction().commit();

			System.out.println("Done!");

		}finally {
			factory.close();
		}

	}

}
