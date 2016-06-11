package com.james.tm.queue;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;

import com.james.tm.executor.MiniRunnable;
import com.james.tm.handler.interfaces.IHandler;
import com.james.tm.log.Log;
import com.james.tm.queue.interfaces.IBaseBlockingQueue;
import com.james.tm.task.Task;

import static com.james.tm.configuration.MiniTaskManagerConfiguration.SUCCESS;
import static com.james.tm.configuration.MiniTaskManagerConfiguration.FAIL;

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

	private static int count = 0;

	public ExecutorService singleExecutors;

	public ExecutorService cacheExecutors;

	public boolean isloop = true;

	private TaskBlockingQueue() {

	}

	/** 获取当前实例 */
	public static TaskBlockingQueue getInstance() {
		if (taskBlockingQueue == null) {
			taskBlockingQueue = new TaskBlockingQueue();
		}
		return taskBlockingQueue;
	}

	public TaskBlockingQueue initExecutors(ExecutorService singleExecutors,
			ExecutorService cacheExecutors) {
		this.singleExecutors = singleExecutors;
		this.cacheExecutors = cacheExecutors;
		return this;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		Log.d(TAG, "init");
		loop();
	}

	@Override
	public void loop() {
		// TODO Auto-generated method stub
		singleExecutors.submit(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (isloop) {
					try {
						if (taskQueue != null && taskQueue.size() != 0) {
							Log.d(TAG, "loop");
							Task myTask = taskQueue.take();
							if (myTask != null) {
								pushTaskToExecutor(myTask);
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

	/**
	 * 推进任务到线程池中
	 * 
	 * @param mytask
	 */
	public void pushTaskToExecutor(final Task mytask) {
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
		cacheExecutors.submit(task);

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
		if (mytask == null)
			throw new NullPointerException("mytask is null");
		FutureTask<?> futuretask = futures.get(mytask.hashCode());
		if (futuretask == null)
			throw new NullPointerException("task not found");
		if (futuretask.isDone())
			return SUCCESS;
		if (!futuretask.isCancelled()) {
			futuretask.cancel(true);
			return SUCCESS;
		}
		return FAIL;
	}

	@Override
	public Task get(Task mytask) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void shutdownNow() {
		// TODO Auto-generated method stub
		shutdown();
		if (!futures.isEmpty()) {
			Set<Entry<Integer, FutureTask<?>>> sets = futures.entrySet();
			Iterator<Entry<Integer, FutureTask<?>>> iterator = sets.iterator();
			while (iterator.hasNext()) {
				Entry<Integer, FutureTask<?>> entry = iterator.next();
				Integer key = entry.getKey();
				FutureTask<?> futureTask = entry.getValue();
				Log.d(TAG, "task hashCode :" + key);
				if (futureTask == null)
					throw new NullPointerException("futureTask is null");
				if (futureTask.isDone()) {
					Log.d(TAG,
							"task hashCode :" + key + ":state"
									+ futureTask.isDone());
				} else {
					if (!futureTask.isCancelled()) {
						boolean iscancel = futureTask.cancel(true);
						if (iscancel) {
							Log.d(TAG, "task cancel success");
						} else {
							Log.d(TAG, "task cancel fail");
						}
					}
				}
			}
		}
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		if (isloop)
			isloop = false;
		Log.d(TAG, " task Queue Unfinished Business count :" + taskQueue.size());
		if (taskQueue != null)
			taskQueue.clear();

	}

}
