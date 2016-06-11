package com.james.tm.queue;


import java.util.concurrent.ExecutorService;

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

	public ExecutorService singleExecutors;

	public ExecutorService cacheExecutors;
	
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
	

	public FailedBlockIngQueue initExecutors(ExecutorService singleExecutors,
			ExecutorService cacheExecutors) {
		this.singleExecutors = singleExecutors;
		this.cacheExecutors = cacheExecutors;
		return this;
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

	@Override
	public void shutdownNow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}

}
