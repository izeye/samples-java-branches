package learningtest.jpa;

import lombok.Data;

import javax.persistence.Entity;

/**
 * Created by izeye on 15. 9. 7..
 */
// tag::docs[]
@Entity
@Data
public class Buyer extends BaseEntity {
	
	private String email;
	
}
// end::docs[]
