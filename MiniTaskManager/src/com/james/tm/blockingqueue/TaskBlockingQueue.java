package com.james.tm.blockingqueue;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;

import com.james.tm.blockingqueue.interfaces.IBaseBlockingQueue;
import com.james.tm.handler.interfaces.IHandler;
import com.james.tm.log.Log;
import com.james.tm.task.MiniRunnable;
import com.james.tm.task.Task;
import com.james.tm.taskmanage.MiniExecutorService;

/**
 * 任务队列
 * 
 * @author james
 * 
 */
public class TaskBlockingQueue implements IBaseBlockingQueue {

	/** 创建任务队列 */
	private static BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<Task>();

	private static Map<Integer, FutureTask<?>> futures = new LinkedHashMap<>();

	private static TaskBlockingQueue taskBlockingQueue;

	private static final int SUCCESS = 1;

	private static final int FAIL = 0;

	private static int count = 0;

	private ExecutorService singleExecutors;

	private ExecutorService cacheExeccutors;

	protected static final String TAG = IBaseBlockingQueue.class
			.getSimpleName();

	private TaskBlockingQueue() {

	}

	/** 获取当前实例 */
	public static TaskBlockingQueue getInstance() {
		if (taskBlockingQueue == null) {
			taskBlockingQueue = new TaskBlockingQueue();
		}
		return taskBlockingQueue;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		Log.d(TAG, "init");
		singleExecutors = MiniExecutorService.getSingleThreadExecutor();
		cacheExeccutors = MiniExecutorService.getCachedThreadPool();
		loop();
	}

	@Override
	public void loop() {
		// TODO Auto-generated method stub
		singleExecutors.submit(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						if (taskQueue != null && taskQueue.size() != 0) {
							Log.d(TAG, "loop");
							Task myTask = taskQueue.take();
							if (myTask != null) {
								pushExecutor(myTask);
							}
						}
					} catch (InterruptedException e) {// 线程中断 任务中断
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});

	}

	public void pushExecutor(final Task mytask) {
		MiniRunnable runnable = new MiniRunnable.Builder().setTask(mytask)
				.setHandler(new IHandler() {

					@Override
					public <T> void back(T t) {
						// TODO Auto-generated method stub
						if (mytask.handler != null) {
							mytask.handler.back(t);
						}
					}
				}).build();
		count++;
		Log.d(TAG, "count == " + count);
		FutureTask<?> task = new FutureTask<>(runnable, null);
		futures.put(mytask.hashCode(), task);
		cacheExeccutors.submit(task);

	}

	@Override
	public int add(Task mytask) {
		if (taskQueue != null) {
			if (taskQueue.offer(mytask)) {
				return SUCCESS;
			}
		}
		return FAIL;
	}

	@Override
	public int cancel(Task mytask) {
		// TODO Auto-generated method stub
		Log.d(TAG, "11111 == " + count);
		FutureTask<?> futuretask = futures.get(mytask.hashCode());
		if (futuretask != null) {
			futuretask.cancel(true);
		}
		return FAIL;
	}

	@Override
	public Task get(Task mytask) {
		// TODO Auto-generated method stub
		return null;
	}

}
