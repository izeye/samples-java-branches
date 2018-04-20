package learningtest.org.openqa.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link WebDriver}.
 *
 * NOTE: Generally you need to install ChromeDriver first
 * although this test uses a self-contained executable.
 * See https://sites.google.com/a/chromium.org/chromedriver/downloads
 *
 * @author Johnny Lim
 */
public class WebDriverTests {

	@Test
	public void test() {
		String currentWorkingDirectory = System.getProperty("user.dir");

		// NOTE: Handle Intellij.
		currentWorkingDirectory = currentWorkingDirectory.replace("/.idea/modules", "");

		System.setProperty(
				"webdriver.chrome.driver",
				currentWorkingDirectory + "/bin/chrome_driver/chromedriver");

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.google.com/");

		WebElement element = driver.findElement(By.name("q"));

		String query = "Cheese!";

		element.sendKeys(query);
		element.submit();

		assertThat(driver.getTitle()).contains(query);
	}

}
