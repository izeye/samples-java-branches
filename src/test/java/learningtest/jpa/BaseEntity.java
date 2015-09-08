package learningtest.jpa;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by izeye on 15. 9. 7..
 */
// tag::docs[]
@MappedSuperclass
@Data
public abstract class BaseEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
}
// end::docs[]
