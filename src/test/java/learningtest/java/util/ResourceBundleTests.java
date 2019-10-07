package learningtest.java.util;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ResourceBundle}.
 *
 * @author Johnny Lim
 */
public class ResourceBundleTests {

	private Locale defaultLocale;

	@Before
	public void setUp() {
		this.defaultLocale = Locale.getDefault();
		Locale.setDefault(Locale.KOREAN);
	}

	@After
	public void cleanUp() {
		Locale.setDefault(this.defaultLocale);
	}

	@Test
	public void getBundleWithPropertiesShouldUseDefaultLocale() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("learningtest/resource_bundles/my_resource_bundle");
		assertThat(resourceBundle.getString("greeting")).isEqualTo("안녕하세요!");
	}

	@Test
	public void getBundleWithPropertiesWhenLocaleIsEnglishShouldUseEnglish() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle(
				"learningtest/resource_bundles/my_resource_bundle", Locale.ENGLISH);
		assertThat(resourceBundle.getString("greeting")).isEqualTo("Hello!");
	}

	@Test
	public void getBundleWithJavaShouldUseDefaultLocale() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("learningtest.java.util.MyResourceBundle");
		assertThat(resourceBundle.getString("greeting")).isEqualTo("안녕하세요?");
	}

	@Test
	public void getBundleWithJavaWhenLocaleIsEnglishShouldUseEnglish() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle(
				"learningtest.java.util.MyResourceBundle", Locale.ENGLISH);
		assertThat(resourceBundle.getString("greeting")).isEqualTo("Hello?");
	}

}
