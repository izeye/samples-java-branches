package learningtest.com.fasterxml.jackson.databind.lombok;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.junit.Test;

/**
 * Created by izeye on 16. 4. 14..
 */
public class ObjectMapperLombokAllArgsConstructorTests {

	@Test
	public void test() throws JsonProcessingException {
		LombokAllArgsConstructorDomain lombokDomain = new LombokAllArgsConstructorDomain();
		lombokDomain.setSomeProperty("test");
		String lombokJson = new ObjectMapper()
				.setPropertyNamingStrategy(
						PropertyNamingStrategy.SNAKE_CASE)
				.writeValueAsString(lombokDomain);
		System.out.println(lombokJson);

		ManualAllArgsConstructorDomain manualDomain = new ManualAllArgsConstructorDomain();
		manualDomain.setSomeProperty("test");
		String manualJson = new ObjectMapper()
				.setPropertyNamingStrategy(
						PropertyNamingStrategy.SNAKE_CASE)
				.writeValueAsString(manualDomain);
		System.out.println(manualJson);

		ManualAllArgsConstructorDomainHavingConstructorProperties manualDomainHavingConstructorProperties
				= new ManualAllArgsConstructorDomainHavingConstructorProperties();
		manualDomainHavingConstructorProperties.setSomeProperty("test");
		String manualJsonHavingConstructorProperties = new ObjectMapper()
				.setPropertyNamingStrategy(
						PropertyNamingStrategy.SNAKE_CASE)
				.writeValueAsString(manualDomainHavingConstructorProperties);
		System.out.println(manualJsonHavingConstructorProperties);
		
		String workaroundAppliedLombokJson = new ObjectMapper()
				.enable(MapperFeature.ALLOW_EXPLICIT_PROPERTY_RENAMING)
				.setPropertyNamingStrategy(
						PropertyNamingStrategy.SNAKE_CASE)
				.writeValueAsString(lombokDomain);
		System.out.println(workaroundAppliedLombokJson);
	}
	
}
