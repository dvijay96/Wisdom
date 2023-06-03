package learnings.java.secret;

public class SimpleEncryption {

	public static void main(String[] args) {
		String str = "Java, a Programming language!";

		int secretKey = 99;

		StringBuilder encrypted = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {
			char ch = (char) (str.charAt(i) + secretKey);
			encrypted.append(ch);
		}

		System.out.println(encrypted);

		StringBuilder decrypted = new StringBuilder();

		for (int i = 0; i < encrypted.length(); i++) {
			char ch = (char) (encrypted.charAt(i) - secretKey);
			decrypted.append(ch);
		}

		System.out.println(decrypted);
	}

}
