package learningtest.javax.xml.parsers;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Tests for DocumentBuilderFactory.
 *
 * @author Johnny Lim
 */
public class DocumentBuilderFactoryTests {

	@Test
	public void test() throws ParserConfigurationException, IOException, SAXException {
		File file = new File("src/test/resources/samples/xml/skorea_provinces_simple.kml");

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(file);

		NodeList placemarks = document.getElementsByTagName("Placemark");
		for (int i = 0; i < placemarks.getLength(); i++) {
			Element placemark = (Element) placemarks.item(i);

			String name = placemark.getElementsByTagName("name").item(0).getTextContent();
			System.out.println("Name: " + name);
			System.out.println("Polygons:");
			NodeList coordinatesList = placemark.getElementsByTagName("coordinates");
			for (int j = 0; j < coordinatesList.getLength(); j++) {
				System.out.println("Coordinates:");
				String coordinatesString = coordinatesList.item(j).getTextContent();
				String[] coordinates = coordinatesString.split(" ");
				for (String coordinateString : coordinates) {
					String[] coordinate = coordinateString.split(",");
					double latitude = Double.parseDouble(coordinate[0]);
					double longitude = Double.parseDouble(coordinate[1]);
					System.out.println("\tlatitude: " + latitude);
					System.out.println("\tlongitude: " + longitude);
				}
			}
		}
	}

}
