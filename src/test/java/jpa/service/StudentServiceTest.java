package jpa.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jpa.entitymodels.Student;
import jpa.service.StudentService;

/**
 * The 'StudentServiceTest' class tests the 'getStudentByEmail' method from the StudentService class.
 * @author deyaniragenao
 *
 */
public class StudentServiceTest {

	private static StudentService studentService;
	
	@BeforeAll
	public static void setUp() {
		studentService = new StudentService();
	}
	
	@Test 
	public void testGetStudentByEmail() {
		
		// Given
		Student expected = new Student();
		expected.setsEmail("d@g.com"); 
		expected.setsName("Deya Genao");
		expected.setsPass("123abc");
		
		// When 
		Student actual = studentService.getStudentByEmail("d@g.com");
		
		// Then
		Assertions.assertEquals(expected, actual); 
		
	}
	
	
	
	
}
