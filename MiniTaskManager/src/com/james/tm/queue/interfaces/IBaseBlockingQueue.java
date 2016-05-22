package com.james.tm.queue.interfaces;

import com.james.tm.task.Task;

public interface IBaseBlockingQueue {

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

}
