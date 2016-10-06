package learningtest.com.github.javaparser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.junit.Test;

/**
 * Tests for {@link JavaParser}.
 *
 * @author Johnny Lim
 */
public class JavaParserTests {

	@Test
	public void test() throws FileNotFoundException, ParseException {
		String filename = "src/test/java/learningtest/com/github/javaparser/JavaParserTests.java";
		CompilationUnit compilationUnit = JavaParser.parse(new FileInputStream(filename));
		System.out.println(compilationUnit);
	}

	@Test
	public void testVisit() throws FileNotFoundException, ParseException {
		String filename = "src/test/java/learningtest/com/github/javaparser/JavaParserTests.java";
		CompilationUnit compilationUnit = JavaParser.parse(new FileInputStream(filename));
		new MethodVisitor().visit(compilationUnit, null);
	}

	private static class MethodVisitor extends VoidVisitorAdapter<Object> {
		@Override
		public void visit(MethodDeclaration n, Object arg) {
			System.out.println(n.getName());
			super.visit(n, arg);
		}
	}

}
