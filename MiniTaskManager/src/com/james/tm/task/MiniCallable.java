package com.james.tm.task;

import java.util.concurrent.Callable;

public class MiniCallable<V> implements Callable<V> {

	private Task task;

	public MiniCallable() {

	}

	public MiniCallable(Task task) {
		this.task = task;
	}

	@Override
	public V call() throws Exception {
		// TODO Auto-generated method stub
		this.task.run();
		return null;
	}

}
