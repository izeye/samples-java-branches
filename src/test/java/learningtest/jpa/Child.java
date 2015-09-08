package learningtest.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

/**
 * Created by izeye on 15. 9. 7..
 */
// tag::docs[]
@Entity
@Data
public class Child {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "PARENT_ID1"),
			@JoinColumn(name = "PARENT_ID2")
	})
	private Parent parent;
	
}
// end::docs[]
