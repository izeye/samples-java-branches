package learningtest.jpa;

import lombok.Data;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Created by izeye on 15. 9. 7..
 */
// tag::docs[]
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn
@Data
public abstract class Item {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private int price;
	
}
// end::docs[]
