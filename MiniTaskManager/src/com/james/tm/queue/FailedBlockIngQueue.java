package com.james.tm.queue;


import com.james.tm.queue.interfaces.IBaseBlockingQueue;
import com.james.tm.task.Task;

/**
 * 任务失败队列
 * 
 * @author james
 * 
 */
public class FailedBlockIngQueue implements IBaseBlockingQueue {

	private static FailedBlockIngQueue failedBlockIngQueue;

//	private static BlockingQueue<Task> failTasks = new LinkedBlockingQueue<>();

	private FailedBlockIngQueue() {

	}

	public static FailedBlockIngQueue getInstance() {
		synchronized (FailedBlockIngQueue.class) {
			if (failedBlockIngQueue == null) {
				failedBlockIngQueue = new FailedBlockIngQueue();
			}
			return failedBlockIngQueue;
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loop() {
		// TODO Auto-generated method stub

	}

	@Override
	public int add(Task mytask) {
		// TODO Auto-generated method stub
		TaskBlockingQueue.getInstance().add(mytask);
		return 0;
	}

	@Override
	public int cancel(Task mytask) {
		// TODO Auto-generated method stub
		TaskBlockingQueue.getInstance().cancel(mytask);
		return 0;
	}

	@Override
	public Task get(Task mytask) {
		// TODO Auto-generated method stub
		return null;
	}

}
