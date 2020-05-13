package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		//create session
		Session session = factory.getCurrentSession();

		try {

			//create the objects
			Instructor tempInstructor = new Instructor("Narges", "Salehi", "Narges06@gmail.com");

			InstructorDetail tempInstructorDetail = new InstructorDetail
					("http://www.Nargesyoutube.com/youtube", "Singer");

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
			session.close();
			factory.close();
		}

	}

}
