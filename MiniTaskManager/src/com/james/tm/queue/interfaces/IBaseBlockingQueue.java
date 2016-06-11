package com.james.tm.queue.interfaces;

import java.util.concurrent.ExecutorService;

import com.james.tm.task.Task;

public interface IBaseBlockingQueue {

	static final String TAG = IBaseBlockingQueue.class.getSimpleName();

	/**
	 * 初始化线程池
	 * 
	 * @param singleExecutors
	 * @param cacheExecutors
	 * @return
	 */
	IBaseBlockingQueue initExecutors(ExecutorService singleExecutors,
			ExecutorService cacheExecutors);

	/**
	 * 初始化 队列
	 */
	void init();

	/**
	 * 添加任务
	 * 
	 * @return
	 */
	int add(Task mytask);

	/**
	 * 删除任务
	 * 
	 * @return
	 */
	int cancel(Task mytask);

	/**
	 * 获取任务
	 * 
	 * @return
	 */
	Task get(Task mytask);

	/**
	 * 循环获取队列任务
	 */
	void loop();

	/**
	 * 强制关闭队列和任务
	 */
	void shutdownNow();

	/**
	 * 关闭队列和任务
	 */
	void shutdown();

}
