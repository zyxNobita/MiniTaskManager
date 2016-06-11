package com.james.tm.executor;

import java.util.concurrent.Callable;

import com.james.tm.task.Task;

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
