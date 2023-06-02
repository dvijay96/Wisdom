package com.design.patterns.singleton;

public class ThreadSafeSingletonII {

//	private static ThreadSafeSingletonII instance = null;

	private ThreadSafeSingletonII() {
	}

	public static ThreadSafeSingletonII getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder {
		public static final ThreadSafeSingletonII INSTANCE = new ThreadSafeSingletonII();
	}
}
