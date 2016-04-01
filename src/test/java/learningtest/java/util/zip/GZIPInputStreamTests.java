package learningtest.java.util.zip;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Before;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by izeye on 16. 3. 31..
 */
public class GZIPInputStreamTests {

	// EOFException occurs with ZeroBytePadding.
//	private static final String CIPHER_ALGORITHM = "AES/ECB/ZeroBytePadding";
	private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
	private static final String KEY_ALGORITHM = "AES";
	private static final int KEY_SIZE_IN_BITS = 128;
	private static final int KEY_SIZE_IN_BYTES = KEY_SIZE_IN_BITS / 8;
	
	private static final int BUFFER_SIZE = 1024;

	private static final byte[] KEY = new byte[KEY_SIZE_IN_BYTES];

	Cipher encryptor;
	Cipher decryptor;
	
	@Before
	public void setUp() {
		if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
			Security.addProvider(new BouncyCastleProvider());
		}
		
		encryptor = getCipher(Cipher.ENCRYPT_MODE);
		decryptor = getCipher(Cipher.DECRYPT_MODE);
	}

	private Cipher getCipher(int mode)  {
		SecretKeySpec secretKeySpec = new SecretKeySpec(KEY, KEY_ALGORITHM);
		try {
			Cipher cipher = Cipher.getInstance(
					CIPHER_ALGORITHM, BouncyCastleProvider.PROVIDER_NAME);
			cipher.init(mode, secretKeySpec);
			return cipher;
		} catch (NoSuchAlgorithmException ex) {
			throw new RuntimeException(ex);
		} catch (NoSuchProviderException ex) {
			throw new RuntimeException(ex);
		} catch (NoSuchPaddingException ex) {
			throw new RuntimeException(ex);
		} catch (InvalidKeyException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@Test
	public void test() {
		String message = "Hello, world!";
		int repeat = 100;

		StringBuffer sbText = new StringBuffer();
		for (int i = 0; i < repeat; i++) {
			sbText.append(i);
			sbText.append(": ");
			sbText.append(message);
			sbText.append("\n");
		}
		String text = sbText.toString();
		
		try {
			byte[] original = text.getBytes();
			print("original", original);
			
			byte[] compressed = compress(original);
			print("compressed", compressed);

			byte[] encrypted = encrypt(compressed);
			print("encrypted", encrypted);

			byte[] decrypted = decrypt(encrypted);
			print("decrypted", decrypted);

			byte[] uncompressed = uncompress(decrypted);
			print("uncompressed", uncompressed);
			
			String result = new String(uncompressed);
			System.out.println(result);
			assertThat(result).isEqualTo(text);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		} 
	}

	private byte[] compress(byte[] original) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		GZIPOutputStream gzos = new GZIPOutputStream(baos);
		gzos.write(original);
		gzos.close();
		return baos.toByteArray();
	}

	private byte[] uncompress(byte[] compressed) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(compressed);
		GZIPInputStream gzis = new GZIPInputStream(bais, BUFFER_SIZE);
		byte[] buffer = new byte[BUFFER_SIZE];
		int read;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while ((read = gzis.read(buffer)) != -1) {
			baos.write(buffer, 0, read);
		}
		return baos.toByteArray();
	}

	private byte[] encrypt(byte[] plain) {
		try {
			return encryptor.doFinal(plain);
		} catch (IllegalBlockSizeException ex) {
			throw new RuntimeException(ex);
		} catch (BadPaddingException ex) {
			throw new RuntimeException(ex);
		}
	}

	private byte[] decrypt(byte[] encrypted) {
		try {
			return decryptor.doFinal(encrypted);
		} catch (IllegalBlockSizeException ex) {
			throw new RuntimeException(ex);
		} catch (BadPaddingException ex) {
			throw new RuntimeException(ex);
		}
	}

	private void print(String header, byte[] bytes) {
		System.out.println(header + ": " + new String(Base64.getEncoder().encode(bytes)));
	}
	
}
