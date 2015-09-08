package learningtest.jpa;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by izeye on 15. 9. 7..
 */
// tag::docs[]
@Entity
@DiscriminatorValue("B")
@Data
public class Book extends Item {
	
	private String author;
	private String isbn;
	
}
// end::docs[]
