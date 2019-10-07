package learningtest.java.util;

import java.util.ListResourceBundle;

/**
 * Sample {@link ListResourceBundle} for Korean.
 *
 * @author Johnny Lim
 */
public class MyResourceBundle extends ListResourceBundle {

	@Override
	protected Object[][] getContents() {
		return new Object[][] {
				{ "greeting", "안녕하세요?" }
		};
	}

}
