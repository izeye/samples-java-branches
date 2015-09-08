package learningtest.jpa;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by izeye on 15. 9. 7..
 */
// tag::docs[]
@Embeddable
@Data
public class ParentId implements Serializable {
	
	private Long id1;
	private Long id2;
	
}
// end::docs[]
