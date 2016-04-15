package learningtest.com.fasterxml.jackson.databind.lombok;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.ConstructorProperties;

/**
 * Created by izeye on 16. 4. 15..
 */
@Data
@NoArgsConstructor
public class ManualAllArgsConstructorDomainHavingConstructorProperties {

	private String someProperty;

	@ConstructorProperties("someProperty")
	public ManualAllArgsConstructorDomainHavingConstructorProperties(String someProperty) {
		this.someProperty = someProperty;
	}
	
}
