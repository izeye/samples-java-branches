package learningtest.jpa;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by izeye on 15. 9. 7..
 */
// tag::docs[]
@Entity
@DiscriminatorValue("A")
@Data
public class Album extends Item {
	
	private String artist;
	
}
// end::docs[]
