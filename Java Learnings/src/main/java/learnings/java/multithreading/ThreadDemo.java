package learnings.java.multithreading;

public class ThreadDemo {

	public static void main(String[] args) {
		
		DemoThread t = new DemoThread();
//		t.setDaemon(true);
		t.start();
		
		for(int i=0;i<=20;i++)
			System.out.println("I'm main thread "+Thread.currentThread().getName());
	}
}

class DemoThread extends Thread{

	@Override
	public void run() {
		for(int i=1;i<=20;i++)
			System.out.println("I'm child thread "+Thread.currentThread().getName());
	}
	
	
}
