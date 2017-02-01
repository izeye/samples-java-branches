package learningtest.org.junit.rules;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Clone from {@code org.springframework.boot.test.rule.OutputCapture}.
 *
 * @author Johnny Lim
 */
public class OutputCapture implements TestRule {

	private CaptureOutputStream captureOut;

	private CaptureOutputStream captureErr;

	private ByteArrayOutputStream copy;

	@Override
	public Statement apply(Statement base, Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				captureOutput();
				try {
					base.evaluate();
				}
				finally {
					releaseOutput();
				}
			}
		};
	}

	private void captureOutput() {
		this.copy = new ByteArrayOutputStream();
		this.captureOut = new CaptureOutputStream(System.out, this.copy);
		this.captureErr = new CaptureOutputStream(System.err, this.copy);
		System.setOut(new PrintStream(this.captureOut));
		System.setErr(new PrintStream(this.captureErr));
	}

	private void releaseOutput() {
		System.setOut(this.captureOut.getOriginal());
		System.setErr(this.captureErr.getOriginal());
		this.copy = null;
	}

	@Override
	public String toString() {
		return this.copy.toString();
	}

	private static class CaptureOutputStream extends OutputStream {

		private final PrintStream original;

		private final OutputStream copy;

		CaptureOutputStream(PrintStream original, OutputStream copy) {
			this.original = original;
			this.copy = copy;
		}

		@Override
		public void write(int b) throws IOException {
			this.copy.write(b);

			this.original.write(b);
			this.original.flush();
		}

		public PrintStream getOriginal() {
			return this.original;
		}

	}

}
