package learningtest.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by izeye on 15. 9. 1..
 */
// tag::docs[]
@Entity
@Data
public class Locker {

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	
	@OneToOne(mappedBy = "locker")
	private User owner;
	
	public Locker() {
	}
	
	public Locker(String name) {
		this.name = name;
	}

}
// end::docs[]
