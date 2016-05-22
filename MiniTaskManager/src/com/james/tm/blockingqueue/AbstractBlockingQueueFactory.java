package com.james.tm.blockingqueue;

public abstract class AbstractBlockingQueueFactory {

	/** 任务队列 */
	public abstract void taskBlockingQueueInit();

	/** 失败队列 */
	public abstract void failedBlcokingQueueInit();

	/** 消息队列 */
	public abstract void messageBlckingQueueInit();

}
