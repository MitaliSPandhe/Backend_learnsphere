package in.learn.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import in.learn.constants.CoursePopularity;
import in.learn.constants.Course_Type;
import in.learn.constants.Level;
import in.learn.constants.Price;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Courses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long course_Id;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Categories category;
	private String title;
	private String description;
	private String teacherName;
	
	@Enumerated(EnumType.STRING)
	private Price price;

	@Enumerated(EnumType.STRING)
	private Level level;

	@CreationTimestamp
	@Column(name = "createdAt", updatable = false)
	private LocalDateTime createdAt;
	
	@Enumerated(EnumType.STRING)
	private Course_Type coursetype;

	@Enumerated(EnumType.STRING)
	private CoursePopularity popularity;
	private String thumbnail_url;
	

	public long getId() {
		return course_Id;
	}

	public void setId(long id) {
		this.course_Id = id;
	}

	public long getCourse_Id() {
		return course_Id;
	}

	public void setCourse_Id(long course_Id) {
		this.course_Id = course_Id;
	}

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return description;
	}

	public void setDesc(String desc) {
		this.description = desc;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Course_Type getCoursetype() {
		return coursetype;
	}

	public void setCoursetype(Course_Type coursetype) {
		this.coursetype = coursetype;
	}


	public CoursePopularity getPopularity() {
		return popularity;
	}

	public void setPopularity(CoursePopularity popularity) {
		this.popularity = popularity;
	}

	public String getThumbnail_url() {
		return thumbnail_url;
	}

	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
	}

}
