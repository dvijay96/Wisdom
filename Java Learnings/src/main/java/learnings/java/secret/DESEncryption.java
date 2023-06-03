package learnings.java.secret;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class DESEncryption {

	public static void main(String[] args) {

		try {
			KeyGenerator kg = KeyGenerator.getInstance("DES");
			SecretKey desKey = kg.generateKey();
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, desKey);

			String str = "Hello World!!!";

			byte[] text = str.getBytes();

			byte[] textEnc = cipher.doFinal(text);

			System.out.println("Encrypted Text:- " + new String(textEnc));

			cipher.init(Cipher.DECRYPT_MODE, desKey);

			byte[] textDec = cipher.doFinal(textEnc);

			System.out.println("Decrypted Text:- " + new String(textDec));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
