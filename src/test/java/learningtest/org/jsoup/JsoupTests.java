package learningtest.org.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by izeye on 16. 1. 27..
 */
public class JsoupTests {
	
	@Test
	public void test() {
		try {
			Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
			Elements newsHeadlines = doc.select("#mp-itn b a");
			System.out.println(newsHeadlines);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
}
