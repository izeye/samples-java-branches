package learningtest.jpa;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by izeye on 15. 9. 7..
 */
// tag::docs[]
@Entity
@DiscriminatorValue("M")
@Data
public class Movie extends Item {
	
	private String director;
	private String actor;
	
}
// end::docs[]
