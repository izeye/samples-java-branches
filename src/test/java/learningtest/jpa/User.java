package learningtest.jpa;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by izeye on 15. 8. 12..
 */
// tag::docs[]
@Entity
@SequenceGenerator(
		name = "USER_SEQ_GENERATOR",
		sequenceName = "USER_SEQ",
		initialValue = 1, allocationSize = 1)
@Data
public class User {
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "USER_SEQ_GENERATOR")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
}
// end::docs[]
