package learnings.java.maps;

import java.util.Hashtable;
import java.util.Map;

public class HashTableDemo {

	public static void main(String[] args) {

		Map<Integer, Character> tab = new Hashtable<>();

		tab.put(1, 'a');
		tab.put(3, 'b');

		System.out.println(tab);
		
	}

}
