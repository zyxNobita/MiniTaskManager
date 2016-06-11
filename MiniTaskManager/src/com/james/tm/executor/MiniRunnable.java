package com.james.tm.executor;

import com.james.tm.handler.interfaces.IHandler;
import com.james.tm.log.Log;
import com.james.tm.queue.FailedBlockIngQueue;
import com.james.tm.task.Task;

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
				Log.d(TAG, count+"");
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e);
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
