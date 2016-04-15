package learningtest.com.fasterxml.jackson.databind.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by izeye on 16. 4. 15..
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class LombokAllArgsConstructorDomainSuppressConstructorProperties {

	private String someProperty;
	
}
