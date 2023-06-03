package learnings.java.secret;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryption {

	private static String key = "1234567890123456";
	private static String encryptedStr = "Hello World!!!";
	private static String padding = "AES/CBC/PKCS5Padding";
	private static int iterationCount = 65536;
	private static int keyLength = 128;
	private static String secretKeyAlg = "PBEWithHmacSHA1AndAES_128";

	public static void main(String[] args) throws Exception {
		String finalStrEnc = null;
		SecretKeyFactory factory = SecretKeyFactory.getInstance(secretKeyAlg);
		PBEKeySpec spec = new PBEKeySpec(key.toCharArray());//, generateSalt(), iterationCount, keyLength);
		SecretKey secretKey = factory.generateSecret(spec);
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");

		SecureRandom random = new SecureRandom();
		byte[] bytesIV = new byte[16];
	    random.nextBytes(bytesIV);
		IvParameterSpec ivSpec = new IvParameterSpec(bytesIV);
		Cipher cipherDec = Cipher.getInstance(padding);
		cipherDec.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
		byte[] encrypted = cipherDec.doFinal(encryptedStr.getBytes());
		finalStrEnc = new String(encrypted);
		System.out.println(finalStrEnc);
		
		cipherDec.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
		byte[] decrypted = cipherDec.doFinal(finalStrEnc.getBytes());
		finalStrEnc = new String(decrypted);
		System.out.println(finalStrEnc);
		
	}

	public static byte[] generateSalt() throws UnsupportedEncodingException {
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[20];
		random.nextBytes(bytes);
		String salt = new String(bytes);
		return salt.getBytes("UTF-8");
	}

}
