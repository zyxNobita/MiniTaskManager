package com.james.tm.queue;

public abstract class AbstractBlockingQueueFactory {

	/** 任务队列 */
	abstract void taskBlockingQueueInit();

	/** 失败队列 */
	abstract void failedBlcokingQueueInit();

	/** 消息队列 */
	abstract void messageBlckingQueueInit();

}
