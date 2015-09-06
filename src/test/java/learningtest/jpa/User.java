package learningtest.jpa;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by izeye on 15. 8. 12..
 */
// tag::docs[]
@Entity
@Data
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	private int age;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedTime;
	
	@Lob
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "GROUP_ID")
	private UserGroup group;
	
	@OneToOne
	@JoinColumn(name = "LOCKER_ID")
	private Locker locker;

	@OneToMany(mappedBy = "user")
	private List<Order> orders = new ArrayList<>();
	
	public User() {
	}
	
	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
}
// end::docs[]
