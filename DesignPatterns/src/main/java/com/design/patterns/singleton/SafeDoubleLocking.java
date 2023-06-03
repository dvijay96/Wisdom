package com.design.patterns.singleton;

public class SafeDoubleLocking {

//	Problems with double locking in singleton

//	1. CPU's reordering of instructions.
//			CPU does reorder the instructions to improve performance. 
//			Let say, during the line 
//				instance = new Singleton();
//				
//				there are 3 instructions to be executed in background i.e 
//					- allocating memory
//					- providing memory reference
//					- initializing member variables
//				
//				CPU can reorder these the last 2 instructions.
//				Suppose in multi-threaded env, 1st thread comes in, acquires lock on object and till second instruction it
//				executes and at that same time 2nd thread comes in checks that object reference is not null and returns with 
//				the uninitialized member variables object references and uses it.
//		
//	2. L1 caching
//			CPU stores the computations values in L1 caches before dumping into the main memory.
//			Each CPU core has its own L1 cache and is in sync with other L1 caches.
//			Suppose, thread t1 executing in core 1 L1 cache and has created the object and all
//			at the same time thread t2 executing in core 2 L1 cache checks in for object instance initialization in its 
//			L1 cache, but due to some reason both L1 caches have not sync with each other , so t2 creates another instance.
//		
//	Solution is:
//		Using volatile keyword for the resource.
//		Which makes sure the member with volatile keyword are directly dumped into main memory and also 
//		all the instructions before the volatile are dumped into main memory.
//		both threads now have to read from main memory now for reference.
//		
//		Volatile members read/write happens directly in main memory.
	
	public static void main(String[] args) {

	}

}

class Singleton {

	private static volatile Singleton instance;

	private Singleton() {
	}

	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (ThreadSafeSingleton.class) {
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
}
