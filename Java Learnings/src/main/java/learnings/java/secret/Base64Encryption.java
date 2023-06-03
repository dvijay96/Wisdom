package learnings.java.secret;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Base64Encryption {

	public static void main(String[] args) throws Exception {
		String str = "Super Admin";

		byte[] encoded = Base64.getEncoder().encode(str.getBytes(StandardCharsets.UTF_8));

		System.out.println(new String(encoded));

		byte[] decoded = Base64.getDecoder().decode(encoded);

		System.out.println(new String(decoded));

		String encrypted = encrypt(str);
		System.out.println(encrypted);
		System.out.println(decrypt(encrypted));
	}

	private static final String ALGO = "Rijndael";
	private static final byte[] keyValue = "dsp_smt_mobile_c".getBytes();

	public static String encrypt(String data) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(data.getBytes());
		byte[] encryptedValue = Base64.getEncoder().encode(encVal);
		System.err.println("encVal: " + new String(encryptedValue));

		return new String(encryptedValue);
	}

	public static String decrypt(String encryptedData) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = Base64.getDecoder().decode(encryptedData);// .decodeBuffer(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		// byte[] decValue = c.doFinal(encryptedData.getBytes());
		String decryptedValue = new String(decValue);
		System.err.println("decVal: " + decryptedValue);

		return decryptedValue;
	}

	private static Key generateKey() throws Exception {
		return new SecretKeySpec(keyValue, ALGO);
	}
}
