package learningtest.com.fasterxml.jackson.databind.lombok;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by izeye on 16. 4. 15..
 */
@Data
@NoArgsConstructor
public class ManualAllArgsConstructorDomain {

	private String someProperty;

	public ManualAllArgsConstructorDomain(String someProperty) {
		this.someProperty = someProperty;
	}
	
}
