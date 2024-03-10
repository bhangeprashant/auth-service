package org.dnyanyog.encryption;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class EncryptionService {

	private static final String SECRET_KEY = "5F270B070EF2F0BAB8123A810368B0E4"; // Encryption key, should be store at
																					// secure location
	private static final String ALGORITHM = "AES"; // Encryption algorithm, should be store at secure location

	private static SecretKey secretKey;
	private static Cipher cipher;

	static {
		secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM); // Create secret key
																								// object from the key
		try {
			cipher = Cipher.getInstance(ALGORITHM); // Cipher instance responsible to encrypt and decrypt
			cipher.init(Cipher.ENCRYPT_MODE, secretKey); // Initialize cipher with secret key
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	public String encrypt(String data) throws Exception {
		byte[] encryptedData = cipher.doFinal(data.getBytes()); // Encrypt data in to bytes
		return Base64.getEncoder().encodeToString(encryptedData);// Encode byte data in to String
	}

	public static String decrypt(String encryptedData) throws Exception {
		byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData)); // Decrypt data in to bytes
		return new String(decryptedData, StandardCharsets.UTF_8);// Convert decrypted data in to String
	}

	public static SecretKey generateAesKey() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(256); // Use 256-bit key size
			return keyGenerator.generateKey();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error generating AES key", e);
		}
	}
}
