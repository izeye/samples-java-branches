package learningtest.org.w3c.dom;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Tests for {@link Document}.
 *
 * @author Johnny Lim
 */
public class DocumentTests {

	@Test
	public void test() throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		Document firstDocument = documentBuilder.newDocument();
		Element rootElement = firstDocument.createElement("root");
		firstDocument.appendChild(rootElement);

		Element childElement = firstDocument.createElement("child");
		rootElement.appendChild(childElement);
		printXml(firstDocument);

		Document secondDocument = documentBuilder.newDocument();
		Node importedRootElement = secondDocument.importNode(rootElement, true);
		secondDocument.appendChild(importedRootElement);
		printXml(secondDocument);
	}

	private void printXml(Document document) throws TransformerException {
		StringWriter writer = new StringWriter();
		StreamResult streamResult = new StreamResult(writer);
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.transform(new DOMSource(document), streamResult);
		System.out.println(writer.toString());
	}

}
