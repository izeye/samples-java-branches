package learningtest.java.util;

import java.util.ListResourceBundle;

/**
 * Sample {@link ListResourceBundle} for English.
 *
 * @author Johnny Lim
 */
public class MyResourceBundle_en extends ListResourceBundle {

	@Override
	protected Object[][] getContents() {
		return new Object[][] {
				{ "greeting", "Hello?" }
		};
	}

}
