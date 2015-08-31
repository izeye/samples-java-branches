package learningtest.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by izeye on 15. 8. 31..
 */
// tag::docs[]
@Entity
@Data
public class UserGroup {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	public UserGroup() {
	}
	
	public UserGroup(String name) {
		this.name = name;
	}
	
}
// end::docs[]
