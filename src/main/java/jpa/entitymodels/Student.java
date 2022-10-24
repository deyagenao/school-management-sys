package jpa.entitymodels;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


/**
 * 'Student' is the model class for creating Student objects. This class also contains the structure for creating the Student Entity in the database, as well as the named queries to be used in the StudentService methods.
 * @author deyaniragenao
 *
 */
@Entity
@Table(name = "student")
@NamedQueries({
	@NamedQuery(name="findAllStudents", query="from Student"),
	@NamedQuery(name="findStudentByEmail", query="from Student s where s.sEmail = :email"),
	@NamedQuery(name="updateStudentCourse", query="update Student s set s.sCourses = :courses where s.sEmail = :email ")
})
public class Student {
	
	@Id
	@Column(name = "email", length= 50)
	private String sEmail;
	@Column(name = "name", nullable = false, length = 50)
	private String sName;
	@Column(name = "password", nullable = false, length = 50)
	private String sPass;
	@ManyToMany(targetEntity = Course.class, fetch= FetchType.EAGER)
	private List<Course> sCourses;
	
	public Student() {
		this.sEmail = "";
		this.sName = "";
		this.sPass = "";
		this.sCourses = new ArrayList<Course>();
	}

	public Student(String sEmail, String sName, String sPass, List<Course> sCourses) {
		this.sEmail = sEmail;
		this.sName = sName;
		this.sPass = sPass;
		this.sCourses = sCourses;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsPass() {
		return sPass;
	}

	public void setsPass(String sPass) {
		this.sPass = sPass;
	}

	public List<Course> getsCourses() {
		return sCourses;
	}

	public void setsCourses(List<Course> sCourses) {
		this.sCourses = sCourses;
	}
	
	
	@Override 
	public boolean equals(Object obj) {
		if(obj instanceof Student) {
			Student other = (Student) obj;
			boolean sameEmail = this.sEmail.equals(other.getsEmail());
			boolean sameName = this.sName.equals(other.getsName());
			boolean samePass = this.sPass.equals(other.getsPass());
			
			if(sameEmail && sameName && samePass) {
				return true;
			}
		}
		return false;
	}
}
