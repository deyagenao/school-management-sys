package jpa.mainrunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentService;

/**
 * The 'SMS' class contains all of the methods necessary to run the application in the console. 
 * @author deyaniragenao
 *
 */
public class SMS {

	private Scanner input; 
	private StudentService ss;
	private CourseService cs;
	private Student student;
	
	/**
	 * The SMS constructor initializes the values of the private fields, Scanner 'input' and Service objects for the Student and Course models. 
	 */
	public SMS() {
		input = new Scanner(System.in);
		ss = new StudentService();
		cs = new CourseService();
	}
	
	
	/**
	 * The 'run' method displays the initial menu for the application. If the user selects "Student" as their role, the validateStudentEmail method is invoked. 
	 */
	public void run() {
		System.out.println("Welcome to the School Management System \nPlease choose your role: \n"+
	"1. Student \n2. Quit");
		int selection = input.nextInt();
		if(selection == 2) {
			System.out.println("Goodbye!");
			input.close();
		} else if (selection == 1) {
			input.nextLine();
			validateStudentEmail();
		} else {
			System.out.println("Please only enter 1 or 2.");
		}
	}
	
	
	/**
	 * The validateStudentEmail method prompts users to enter their email and password. The validateStudent method from the StudentService object (ss) is invoked with the user email and password. If validated, 'runStudentMenu' is invoked. Otherwise, users will see an error message in the console.
	 */
	public void validateStudentEmail() {
		
		System.out.println("Enter Your Email: ");
		String email = input.nextLine();
		System.out.println("Enter Your Password: ");
		String password = input.nextLine();
		boolean isValidated = ss.validateStudent(email, password);
		if(isValidated) {
			student = ss.getStudentByEmail(email);
			runStudentMenu();
		} 
	}
	
	
	/** 
	 * The runStudentMenu method displays the validated student's current courses. Then it prompts them to register for a class or to logout and end the program. 
	 */
	public void runStudentMenu() {
		
		List<Course> studentCourses = new ArrayList<Course>();
		studentCourses = ss.getStudentCourses(student.getsEmail());
		System.out.println("My Classes: \n");
		displayCourses(studentCourses);
		System.out.println("1. Register for Class \n2. Logout");
		int selection = input.nextInt();
		switch(selection) {
			case 1: 
				registerToClass();
				break;
			case 2: 
				System.out.println("Goodbye! ");
				break;
			default: 
				System.out.println("Please only enter 1 or 2.");
				runStudentMenu();
		}
	}
	
	
	/**
	 * displayCourses method accepts a list of course objects. If the list is empty, a message is printed to the console. Otherwise, the course details are printed. 
	 * @param courses
	 */
	public void displayCourses(List<Course> courses) {
		if(courses.isEmpty()) {
			System.out.println("No courses to display \n");
		} else {
			System.out.printf("%-10s%-20s%-20s%n", "COURSE ID", "COURSE NAME", "INSTRUCTOR NAME");
			for(Course course: courses) {
				System.out.printf("%-10d%-20s%-20s%n", course.getcId(), course.getcName(), course.getcInstructorName());
			}
		}
	}
	
	/**
	 * registerToClass displays all available courses and then prompts users to enter a course id. It then invokes the Student Service object's registerStudentToCourse method. The updatedCourses are displayed and then the program is terminated.
	 */
	public void registerToClass() {
		List<Course> allCourses = cs.getAllCourses();
		displayCourses(allCourses);
		System.out.println("Which course would you like to register for? (Enter the ID Number)");
		int courseId = input.nextInt();
		ss.registerStudentToCourse(student.getsEmail(), courseId);
		System.out.println("My Classes \n");
		List<Course> updatedCourses = ss.getStudentCourses(student.getsEmail());
		displayCourses(updatedCourses);
		System.out.println("All done. You have been signed out.");
		
	}
	
	/**
	 * setUpTables method runs the respective methods to set up the Student and Course Tables, as well as fill these tables with initial data. 
	 */
	public void setUpTables() {
		ss.setUpStudentTable(); 
		cs.setUpCourseTable();
	}
}
