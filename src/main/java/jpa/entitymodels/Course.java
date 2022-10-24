package jpa.entitymodels;


import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 'Course' is the model class for creating Course objects. This class also contains the structure for creating the Course Entity in the database, as well as the named queries to be used in the CourseService methods.
 * @author deyaniragenao
 *
 */
@Entity
@Table(name = "course")
@NamedQuery(name="findAllCourses", query="select c from Course c")
@NamedQuery(name="findCourseById", query="from Course c where cId = :id")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int cId;
	@Column(name = "name", nullable = false, length = 50)
	private String cName;
	@Column(name = "instructor", nullable = false, length = 50)
	private String cInstructorName;
	
	public Course() {
		this.cName = "";
		this.cInstructorName = "";
		
	}

	public Course(String cName, String cInstructorName) {
		this.cName = cName;
		this.cInstructorName = cInstructorName;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcInstructorName() {
		return cInstructorName;
	}

	public void setcInstructorName(String cInstructorName) {
		this.cInstructorName = cInstructorName;
	}
	
	@Override 
	public boolean equals(Object obj) {
		if(obj instanceof Course) {
			Course other = (Course) obj;
			boolean sameId = (this.cId == other.getcId());
			boolean sameName = this.cName.equals(other.getcName());
			boolean sameInstructor = this.cInstructorName.equals(other.getcInstructorName());
			if(sameId && sameName && sameInstructor) {
				return true;
			}
		}
		return false;
	}
	
}
