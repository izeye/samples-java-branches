package learningtest.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Tests for {@link User}.
 *
 * Run the following command to recreate a {@link User} class:
 *
 * java -jar ./bin/avro_tools/avro-tools-1.8.2.jar compile schema src/test/avro/user.avsc src/test/java
 *
 * @author Johnny Lim
 */
public class UserTests {

	@Test
	public void testWithCodeGeneration() {
		User user1 = new User();
		user1.setName("Alyssa");
		user1.setFavoriteNumber(256);

		User user2 = new User("Ben", 7, "red");

		User user3 = User.newBuilder()
				.setName("Charlie")
				.setFavoriteColor("blue")
				.setFavoriteNumber(null)
				.build();

		// See .idea/modules/users.avro for IntelliJ
		File file = new File("users.avro");

		DatumWriter<User> userDataWriter = new SpecificDatumWriter<>(User.class);
		try (DataFileWriter<User> dataFileWriter = new DataFileWriter<>(userDataWriter)) {
			dataFileWriter.create(user1.getSchema(), file);
			dataFileWriter.append(user1);
			dataFileWriter.append(user2);
			dataFileWriter.append(user3);
		}
		catch (IOException ex) {
			throw new RuntimeException(ex);
		}

		DatumReader<User> userDatumReader = new SpecificDatumReader<>(User.class);
		try (DataFileReader<User> dataFileReader = new DataFileReader<>(file, userDatumReader)) {
			User user = null;
			while (dataFileReader.hasNext()) {
				user = dataFileReader.next(user);
				System.out.println(user);
			}
		}
		catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Test
	public void testWithoutCodeGeneration() {
		String currentWorkingDirectory = System.getProperty("user.dir");

		// NOTE: Handle Intellij.
		currentWorkingDirectory = currentWorkingDirectory.replace("/.idea/modules", "");

		File schemaFile = new File(currentWorkingDirectory + "/src/test/avro/user.avsc");

		try {
			Schema schema = new Schema.Parser().parse(schemaFile);

			GenericRecord user1 = new GenericData.Record(schema);
			user1.put("name", "Alyssa");
			user1.put("favorite_number", 256);

			GenericRecord user2 = new GenericData.Record(schema);
			user2.put("name", "Alyssa");
			user2.put("favorite_number", 7);
			user2.put("favorite_color", "red");

			File file = new File("users.avro");

			DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(schema);
			try (DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter)) {
				dataFileWriter.create(schema, file);
				dataFileWriter.append(user1);
				dataFileWriter.append(user2);
			}

			DatumReader<GenericRecord> datumReader = new GenericDatumReader<>(schema);
			try (DataFileReader<GenericRecord> dataFileReader = new DataFileReader<>(file, datumReader)) {
				GenericRecord user = null;
				while (dataFileReader.hasNext()) {
					user = dataFileReader.next(user);
					System.out.println(user);
				}
			}
		}
		catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Test
	public void testWithoutSchema() {
		User user1 = new User();
		user1.setName("Alyssa");
		user1.setFavoriteNumber(256);

		User user2 = new User("Ben", 7, "red");

		User user3 = User.newBuilder()
				.setName("Charlie")
				.setFavoriteColor("blue")
				.setFavoriteNumber(null)
				.build();

		byte[] encoded;
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(baos, null);

			DatumWriter<User> userDataWriter = new SpecificDatumWriter<>(User.class);
			userDataWriter.write(user1, encoder);
			userDataWriter.write(user2, encoder);
			userDataWriter.write(user3, encoder);

			encoder.flush();

			encoded = baos.toByteArray();
			System.out.println("Encoded bytes: " + encoded.length);
		}
		catch (IOException ex) {
			throw new RuntimeException(ex);
		}

		User user = null;
		try {
			BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(encoded, null);

			DatumReader<User> userDatumReader = new SpecificDatumReader<>(User.class);
			user = userDatumReader.read(user, decoder);
			System.out.println(user);

			user = userDatumReader.read(user, decoder);
			System.out.println(user);

			user = userDatumReader.read(user, decoder);
			System.out.println(user);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
