package in.learn.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Enrollments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long enr_Id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;

	@ManyToOne
	@JoinColumn(name = "course_id")
	private Courses course;

	@CreationTimestamp
	@Column(name = "enrollment_Date", updatable = false)
	private LocalDateTime enrollment_Date;

	public long getEnr_Id() {
		return enr_Id;
	}

	public void setEnr_Id(long enr_Id) {
		this.enr_Id = enr_Id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Courses getCourse() {
		return course;
	}

	public void setCourse(Courses course) {
		this.course = course;
	}

	public LocalDateTime getEnrollment_Date() {
		return enrollment_Date;
	}

	public void setEnrollment_Date(LocalDateTime enrollment_Date) {
		this.enrollment_Date = enrollment_Date;
	}

}
