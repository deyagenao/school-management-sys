package jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;


/**
 * The 'StudentService' class implements the methods declared in the StudentDAO interface. These methods are used to query and modify data in the Student table, as well as the data in the student_course join table. 
 * @author deyaniragenao
 *
 */
public class StudentService implements StudentDAO {

	/**
	 * getAllStudents retrieves all records in the Student table and returns a list of Student objects. 
	 */
	public List<Student> getAllStudents() {

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		TypedQuery<Student> query = session.getNamedQuery("findAllStudents");
		List<Student> students = query.getResultList();

		session.close();
		factory.close();

		return students;
	}

	/**
	 * getStudentByEmail accepts a String sEmail as a parameter. It then queries the database for the student record with the matching email. If no record is found, an error message is printed. Otherwise, a Student object is returned. 
	 */
	public Student getStudentByEmail(String sEmail) {
		
		Student student = new Student();
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		TypedQuery<Student> query = session.getNamedQuery("findStudentByEmail").setParameter("email", sEmail);
		
		try {
			student = query.getSingleResult();
		} catch(NoResultException e) {
			System.out.println("Not a valid email. Goodbye!");
		}
		

		session.close();
		factory.close();

		return student;
	}

	/**
	 * validateStudent accepts 2 strings (sEmail, sPassword) as arguments. It then invokes the getStudentByEmail method. If a student is found, it checks if the password values match. 
	 */
	public boolean validateStudent(String sEmail, String sPassword) {
		Student student = getStudentByEmail(sEmail);
		
		if (student.getsPass().equals(sPassword)) {
			return true;
		} else {
			System.out.println("Wrong Credentials. Goodbye!");
			return false;
		}
		
	}

	/**
	 * registerStudentToCourse retrieves a student by invoking the getStudentByEmail method. It then retrieves the student's current courses. If the current course list does not include the course with the cId, this course is retrieved and added to the course list. Otherwise, an error message is printed. 
	 */
	public void registerStudentToCourse(String sEmail, int cId) {
		Student student = getStudentByEmail(sEmail);
		List<Course> studentCourses = student.getsCourses();
		CourseService cs = new CourseService();

		Course c = cs.getCourseById(cId);

		if (studentCourses.contains(c)) {
			System.out.println("You are already registered for this course.");
		} else {
			
			studentCourses.add(c);
			student.setsCourses(studentCourses);

			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			
			session.merge(student);

			System.out.println("Successfully registered for course.");
			t.commit();
			session.close();
			factory.close();
		}

	}

	/**
	 * getStudentCourses retrieves a student record and then returns a list of courses. 
	 */
	public List<Course> getStudentCourses(String sEmail) {
		
		List<Course> studentCourses = new ArrayList<Course>();

		Student student = getStudentByEmail(sEmail);
		if(student.getsCourses() != null) {
			studentCourses = student.getsCourses();
		}
		return studentCourses;
	}

	
	/**
	 * setUpStudentTable creates the student table in the database and inserts initial data. 
	 */
	public void setUpStudentTable() {

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		List<Course> courses = new ArrayList<Course>();

		Student s1 = new Student("hluckham0@google.ru", "Hazel Luckham", "X1uZcoIh0dj", courses);
		Student s2 = new Student("sbowden1@yellowbook.com", "Sonnnie Bowden", "SJc4aWSU", courses);
		Student s3 = new Student("qllorens2@howstuffworks.com", "Quillan Llorens", "W6rJuxd", courses);
		Student s4 = new Student("cstartin3@flickr.com", "Clem Startin", "XYHzJ1S", courses);
		Student s5 = new Student("tattwool4@biglobe.ne.jp", "Thornie Attwool", "Hjt0SoVmuBz", courses);
		Student s6 = new Student("hguerre5@deviantart.com", "Harcourt Guerre", "OzcxzD1PGs", courses);
		Student s7 = new Student("htaffley6@columbia.edu", "Holmes Taffley", "xowtOQ", courses);
		Student s8 = new Student("aiannitti7@is.gd", "Alexandra Iannitti", "TWP4hf5j", courses);
		Student s9 = new Student("ljiroudek8@sitemeter.com", "Laryssa Jiroudek", "bXRoLUP", courses);
		Student s10 = new Student("cjaulme9@bing.com", "Cahra Jaulme", "FnVklVgC6r6", courses);
		Student s11 = new Student("d@g.com", "Deya Genao", "123abc", courses);

		session.persist(s1);
		session.persist(s2);
		session.persist(s3);
		session.persist(s4);
		session.persist(s5);
		session.persist(s6);
		session.persist(s7);
		session.persist(s8);
		session.persist(s9);
		session.persist(s10);
		session.persist(s11);

		t.commit();
		session.close();
		factory.close();

	}
}
