package learnings.java.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintAlphaNum {

	private boolean flag = true;

	public static void main(String[] args) {
		PrintAlphaNum obj = new PrintAlphaNum();
		ExecutorService service = Executors.newFixedThreadPool(2);

		service.submit(obj::printAlphabets);
		service.submit(obj::printNumbers);

		service.shutdown();

		while (!service.isTerminated()) {
			// wait
		}
	}

	void printAlphabets() {
		for (char c = 'a'; c <= 'z'; c++) {
			try {
				synchronized (this) {
					while (!flag) {
						wait();
					}
					System.out.print(c);
					this.flag = false;
					this.notify();

				}
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
	}

	void printNumbers() {
		for (int i = 1; i <= 26; i++) {
			try {
				synchronized (this) {
					while (this.flag) {
						wait();
					}
					System.out.print(i + " ");
					this.flag = true;
					this.notify();
				}
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
	}
}
