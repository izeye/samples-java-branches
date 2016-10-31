package learningtest.javax.crypto;

import com.izeye.util.ByteUtils;
import com.izeye.util.Stopwatch;

import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Cipher}.
 *
 * @author Johnny Lim
 */
public class CipherTests {
	
	@Test
	public void test() {
		String keyAlgorithm = "AES";
//		String cipherAlgorithm = "AES/ECB/PKCS5PADDING";
//		String cipherAlgorithm = "AES/CBC/PKCS5PADDING";
		String cipherAlgorithm = "AES/GCM/PKCS5PADDING";
		int keySize = 128;
		int authenticationTagLength = 128;
		
		String message = "This is a test message.";
		
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(keyAlgorithm);
			keyGenerator.init(keySize);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] encodedSecretKey = secretKey.getEncoded();
			System.out.println(encodedSecretKey.length);
			
			byte[] iv = new byte[keySize / 8];
			SecureRandom random = new SecureRandom();
			random.nextBytes(iv);
			
			String hexSecretKey = ByteUtils.bytes2HexString(encodedSecretKey);
			System.out.println(hexSecretKey);
			assertThat(ByteUtils.hexString2Bytes(hexSecretKey)).isEqualTo(encodedSecretKey);
			
			Cipher encryptor = Cipher.getInstance(cipherAlgorithm);

			// For ECB
//			encryptor.init(Cipher.ENCRYPT_MODE, secretKey);

			// For CBC
//			encryptor.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));

			// For GCM
			encryptor.init(Cipher.ENCRYPT_MODE, secretKey,
					new GCMParameterSpec(authenticationTagLength, iv));

			byte[] encrypted = encryptor.doFinal(message.getBytes());
			String encoded = Base64.getEncoder().encodeToString(encrypted);
			System.out.println(encoded);
			
			Cipher decryptor = Cipher.getInstance(cipherAlgorithm);
			
			// For ECB
//			decryptor.init(Cipher.DECRYPT_MODE, secretKey);
			
			// For CBC
//			decryptor.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
			
			// For GCM
			decryptor.init(Cipher.DECRYPT_MODE, secretKey,
					new GCMParameterSpec(authenticationTagLength, iv));

			byte[] decoded = Base64.getDecoder().decode(encoded);
			byte[] decrypted = decryptor.doFinal(decoded);
			System.out.println(new String(decrypted));
		} catch (NoSuchAlgorithmException ex) {
			throw new RuntimeException(ex);
		} catch (NoSuchPaddingException ex) {
			throw new RuntimeException(ex);
		} catch (InvalidKeyException ex) {
			throw new RuntimeException(ex);
		} catch (BadPaddingException ex) {
			throw new RuntimeException(ex);
		} catch (IllegalBlockSizeException ex) {
			throw new RuntimeException(ex);
		} catch (InvalidAlgorithmParameterException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Test
	public void testGetInstance() throws NoSuchPaddingException, NoSuchAlgorithmException {
		String cipherAlgorithm = "AES/GCM/PKCS5PADDING";

		Stopwatch stopwatch = new Stopwatch();
		stopwatch.start();
		Cipher.getInstance(cipherAlgorithm);
		stopwatch.stop();
		System.out.println(stopwatch.getElapsedTime());

		stopwatch = new Stopwatch();
		stopwatch.start();
		Cipher.getInstance(cipherAlgorithm);
		stopwatch.stop();
		System.out.println(stopwatch.getElapsedTime());
	}
	
}
