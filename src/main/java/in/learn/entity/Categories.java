package in.learn.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Categories {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long category_id;
	private String cname;
	private String description;

	public long getId() {
		return category_id;
	}

	public void setId(long id) {
		this.category_id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
