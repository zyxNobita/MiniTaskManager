package com.james.tm.task;

import com.james.tm.blockingqueue.FailedBlockIngQueue;
import com.james.tm.handler.interfaces.IHandler;
import com.james.tm.log.Log;

public class MiniRunnable implements Runnable {

	private Task task;
	public IHandler handler;

	public static final String TAG = MiniRunnable.class.getSimpleName();

	public MiniRunnable() {

	}

	public MiniRunnable(Builder builder) {
		this.handler = builder.handler;
		this.task = builder.task;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.task.run();
		int count = 0;
		try {
			while (count != 10) {
				count++;

				Thread.sleep(1000);

			}
			this.handler.back("hello world!!!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			Log.d(TAG, e.getLocalizedMessage());
			this.handler.back("e.getLocalizedMessage()");
			FailedBlockIngQueue.getInstance().add(task);
		}

	}

	public static class Builder {

		private IHandler handler;
		private Task task;

		public Builder setHandler(IHandler handler) {
			this.handler = handler;
			return this;
		}

		public Builder setTask(Task mytask) {
			this.task = mytask;
			return this;
		}

		public MiniRunnable build() {
			return new MiniRunnable(this);
		}
	}
}
