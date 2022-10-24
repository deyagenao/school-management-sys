package jpa.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;


/**
 * The 'CourseService' class implements the methods declared in the CourseDAO interface. These methods are used to query and modify data in the Course table. 
 * @author deyaniragenao
 *
 */
public class CourseService implements CourseDAO {

	/**
	 * getAllCourses retrieves all records in the Course table and returns a list of Course objects. 
	 */
	public List<Course> getAllCourses() {
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		TypedQuery<Course> query = session.getNamedQuery("findAllCourses");
		List<Course> courses = query.getResultList();
		session.close();
		factory.close();
		return courses;
	}
	
	
	/**
	 * getCourseById accepts an int cId as a parameter. It then queries the database for the course record with the matching id. If no record is found, an error message is printed. Otherwise, a Course object is returned. 
	 */
	public Course getCourseById(int cId) {
		
		Course course = new Course();
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		
		TypedQuery<Course> query = session.getNamedQuery("findCourseById").setParameter("id", cId);
		
		
		try {
			course = query.getSingleResult();	
		} catch (NoResultException e) {
			System.out.println("No courses with the given ID.");
		}
		
		
		session.close();
		factory.close();
		
		return course;
	}
	
	
	/**
	 * setUpCourseTable creates the course table in the database and inserts initial data. 
	 */
	public void setUpCourseTable() {
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		Course c1 = new Course("English", "Anderea Scamaden");
		Course c2 = new Course("Mathematics", "Eustace Niemetz");
		Course c3 = new Course("Anatomy", "Reynolds Pastor");
		Course c4 = new Course("Organic Chemistry", "Odessa Belcher");
		Course c5 = new Course("Physics", "Dani Swallow");
		Course c6 = new Course("Digital Logic", "Glenden Reilingen");
		Course c7 = new Course("OOP", "Giselle Ardy");
		Course c8 = new Course("Data Structures", "Carolan Stoller");
		Course c9 = new Course("Politics", "Carmita De Maine");
		Course c10 = new Course("Art", "Kingsly Doxsey");
		
		session.persist(c1);
		session.persist(c2);
		session.persist(c3);
		session.persist(c4);
		session.persist(c5);
		session.persist(c6);
		session.persist(c7);
		session.persist(c8);
		session.persist(c9);
		session.persist(c10);
		
		t.commit();
		session.close();
		factory.close();
		
	}

}
