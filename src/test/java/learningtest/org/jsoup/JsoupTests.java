package learningtest.org.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Tests for jsoup.
 *
 * @author Johnny Lim
 */
class JsoupTests {
	
	@Test
	void connect() {
		try {
			Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
			Elements newsHeadlines = doc.select("#mp-itn b a");
			System.out.println(newsHeadlines);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Test
	void selectWithAttribute() {
		String html = "<head>"
				+ "<link rel=\"stylesheet\" href=\"https://www.izeye.com/test1.css\">"
				+ "<link rel=\"stylesheet\" href=\"https://www.izeye.com/test2.css\">"
				+ "</head/>";
		Document document = Jsoup.parse(html);
		Elements elements = document.select("[href]");
		for (Element element : elements) {
			String href = element.attr("href");
			System.out.println(href);
		}
	}
	
}
