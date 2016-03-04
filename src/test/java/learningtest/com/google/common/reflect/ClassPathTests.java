package learningtest.com.google.common.reflect;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by izeye on 16. 3. 4..
 */
public class ClassPathTests {
	
	@Test
	public void test() {
		try {
			ClassPath classPath = ClassPath.from(getClass().getClassLoader());
			ImmutableSet<ClassPath.ClassInfo> allClasses
					= classPath.getTopLevelClasses("com.izeye.util");
			for (ClassPath.ClassInfo classInfo : allClasses) {
				String className = classInfo.getName();
				System.out.println(className);
				
				Class<?> clazz = Class.forName(className);
				System.out.println(clazz);
			}
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException(ex);
		}
	}
	
}
