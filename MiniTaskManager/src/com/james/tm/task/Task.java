package com.james.tm.task;

import com.james.tm.handler.interfaces.IHandler;
import com.james.tm.task.interfaces.ITask;

public class Task implements ITask {

	public IHandler handler;

	public Task() {

	}

	public Task(Builder builder) {
		this.handler = builder.handler;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("mytask");
	}

	public static class Builder {
		private IHandler handler;

		public Builder handler(IHandler handler) {
			this.handler = handler;
			return this;
		}

		public Task build() {
			return new Task(this);
		}

	}

}
