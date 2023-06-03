package learnings.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public interface InputUtil {

	static int[] getArray(int size) {
		int[] arr = new int[size];

		ThreadLocalRandom random = ThreadLocalRandom.current();

		for (int i = 0; i < size; i++) {
			arr[i] = random.nextInt(1, 21);
		}
		return arr;
	}

	static List<Integer> getIntList(int size) {
		List<Integer> list = new ArrayList<>(size);

		ThreadLocalRandom random = ThreadLocalRandom.current();

		for (int i = 0; i < size; i++) {
			list.add(random.nextInt(1, 21));
		}
		return list;
	}
}
