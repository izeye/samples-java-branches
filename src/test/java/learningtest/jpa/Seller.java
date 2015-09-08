package learningtest.jpa;

import lombok.Data;

import javax.persistence.Entity;

/**
 * Created by izeye on 15. 9. 7..
 */
// tag::docs[]
@Entity
@Data
public class Seller extends BaseEntity {
	
	private String shopName;
	
}
// end::docs[]
