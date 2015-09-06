package learningtest.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by izeye on 15. 9. 2..
 */
// tag::docs[]
@Entity
@Data
public class Product {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
}
// end::docs[]
