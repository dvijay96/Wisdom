package learnings.java.secret;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class EncryptionWithArray {

	public static void main(String[] args) {

		String str = "Hello World!!!";

		int[] arr = new int[str.length()];

		fillRandom(arr);

		System.out.println(Arrays.toString(arr));

		StringBuilder encrypted = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {
			char ch = (char) (str.charAt(i) + arr[i]);
			encrypted.append(ch);
		}

		System.out.println("Encrypted:- " + encrypted);

		StringBuilder decrypted = new StringBuilder();

		for (int i = 0; i < encrypted.length(); i++) {
			char ch = (char) (encrypted.charAt(i) - arr[i]);
			decrypted.append(ch);
		}

		System.out.println("Decrypted:- " + decrypted);
	}

	private static void fillRandom(int[] arr) {
		ThreadLocalRandom random = ThreadLocalRandom.current();

		int range = random.nextInt(100);

		for (int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt(range + 1);
		}
	}

}
