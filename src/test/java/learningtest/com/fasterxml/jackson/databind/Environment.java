package learningtest.com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by izeye on 16. 6. 27..
 */
@Data
public class Environment {
	
	private String name;

	@JsonProperty("test_name")
	public void setTestName(String name) {
		this.name = name;
	}

	@JsonProperty("production_name")
	public void setProductionName(String name) {
		this.name = name;
	}
	
}
