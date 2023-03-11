package com.design.patterns.singleton;

public class ThreadSafeSingletonII {

//	private static ThreadSafeSingletonII instance = null;

	private ThreadSafeSingletonII() {
	}

	public static ThreadSafeSingletonII getInstance() {
		return ThreadSafeSingletonII.Holder.instance;
	}

	private static class Holder {
		public static ThreadSafeSingletonII instance = new ThreadSafeSingletonII();
	}
}
